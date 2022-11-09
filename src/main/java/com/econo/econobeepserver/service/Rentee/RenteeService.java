package com.econo.econobeepserver.service.Rentee;

import com.econo.econobeepserver.domain.Rental.RentalRepository;
import com.econo.econobeepserver.domain.Rentee.*;
import com.econo.econobeepserver.dto.Rentee.*;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.service.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RenteeService {

    private final RenteeRepository renteeRepository;
    private final RenteeThumbnailRepository thumbnailRepository;

    private final RentalRepository rentalRepository;
    private final ImageHandler imageHandler;

    @Transactional
    public Long createRentee(RenteeSaveDto renteeSaveDto) {
        Rentee rentee = renteeSaveDto.toEntity();
        RenteeThumbnail thumbnail = imageHandler.parseRenteeThumbnail(renteeSaveDto.getThumbnail());

        imageHandler.downloadImage(renteeSaveDto.getThumbnail(), thumbnail.getFilePath());
        thumbnail = thumbnailRepository.save(thumbnail);
        rentee.setRenteeThumbnail(thumbnail);

        return renteeRepository.save(rentee).getId();
    }

    public Rentee getRenteeById(Long id) {
        return renteeRepository.findById(id).orElseThrow(NotFoundRenteeException::new);
    }

    public RenteeInfoDto getRenteeInfoDtoById(Long id) {
        Rentee rentee = getRenteeById(id);
        List<RentalElementDto> rentalElementDtos = rentalRepository.findByRentee_Id(rentee.getId()).stream().map(RentalElementDto::new).collect(Collectors.toList());

        return new RenteeInfoDto(rentee, rentalElementDtos);
    }

    public List<RenteeElementDto> searchRenteeElementDtosByNameWithPaging(String name, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Rentee> rentees = renteeRepository.findRenteesByNameContaining(name, pageRequest);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> searchRenteeElementDtosByNameFromBookWithPaging(String name, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Rentee> rentees = renteeRepository.findRenteesByTypeAndNameContaining(RenteeType.BOOK, name, pageRequest);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> searchRenteeElementDtosByNameFromEquipmentWithPaging(String name, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Rentee> rentees = renteeRepository.findRenteesByTypeAndNameContaining(RenteeType.EQUIPMENT, name, pageRequest);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeManagementInfoDto> searchRenteeManagementInfoDtosByNameFromBookWithSortAndPaging(String name, RenteeSort renteeSort, int pageIndex, int pageSize) {
        List<Rentee> rentees = Collections.emptyList();
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        switch (renteeSort) {
            case NONE:
                rentees = renteeRepository.findRenteesNameContainingFromBookWithPaging(name, pageRequest);
                break;
            case CREATED_ASC:
                rentees = renteeRepository.findRenteesNameContainingFromBookOrderByCreatedAscWithPaging(name, pageRequest);
                break;
            case CREATED_DESC:
                rentees = renteeRepository.findRenteesNameContainingFromBookOrderByCreatedDescWithPaging(name, pageRequest);
                break;
            case LATEST_RENTAL:
                rentees = renteeRepository.findRenteesNameContainingFromBookOrderByLatestRentalWithPaging(name, pageRequest);
                break;
            case OUTDATED_RENTAL:
                rentees = renteeRepository.findRenteesNameContainingFromBookOrderByOutdatedRentalWithPaging(name, pageRequest);
                break;
        }

        return rentees.stream()
                .map(rentee -> new RenteeManagementInfoDto(rentee, rentalRepository.findFirstByRentee_IdOrderByCreatedDateDesc(rentee.getId()).orElse(null)))
                .collect(Collectors.toList());
    }

    public List<RenteeManagementInfoDto> searchRenteeManagementInfoDtosByNameFromEquipmentWithSortAndPaging(String name, RenteeSort renteeSort, int pageIndex, int pageSize) {
        List<Rentee> rentees = Collections.emptyList();
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        switch (renteeSort) {
            case NONE:
                rentees = renteeRepository.findRenteesNameContainingFromEquipmentWithPaging(name, pageRequest);
                break;
            case CREATED_ASC:
                rentees = renteeRepository.findRenteesNameContainingFromEquipmentOrderByCreatedAscWithPaging(name, pageRequest);
                break;
            case CREATED_DESC:
                rentees = renteeRepository.findRenteesNameContainingFromEquipmentOrderByCreatedDescWithPaging(name, pageRequest);
                break;
            case LATEST_RENTAL:
                rentees = renteeRepository.findRenteesNameContainingFromEquipmentOrderByLatestRentalWithPaging(name, pageRequest);
                break;
            case OUTDATED_RENTAL:
                rentees = renteeRepository.findRenteesNameContainingFromEquipmentOrderByOutdatedRentalWithPaging(name, pageRequest);
                break;
        }

        return rentees.stream()
                .map(rentee -> new RenteeManagementInfoDto(rentee, rentalRepository.findFirstByRentee_IdOrderByCreatedDateDesc(rentee.getId()).orElse(null)))
                .collect(Collectors.toList());
    }

    public String getThumbnailFilePathByRenteeId(long renteeId) {
        Rentee rentee = getRenteeById(renteeId);

        return rentee.getThumbnail().getFilePath();
    }

    @Transactional
    public void updateRenteeById(Long id, RenteeSaveDto renteeSaveDto) {
        Rentee rentee = getRenteeById(id);
        RenteeThumbnail oldRenteeThumbnail = rentee.getThumbnail();
        RenteeThumbnail newRenteeThumbnail = imageHandler.parseRenteeThumbnail(renteeSaveDto.getThumbnail());

        imageHandler.deleteImage(oldRenteeThumbnail.getFilePath());
        thumbnailRepository.deleteById(oldRenteeThumbnail.getId());

        imageHandler.downloadImage(renteeSaveDto.getThumbnail(), newRenteeThumbnail.getFilePath());
        newRenteeThumbnail = thumbnailRepository.save(newRenteeThumbnail);

        rentee.updateInformation(renteeSaveDto);
        rentee.setRenteeThumbnail(newRenteeThumbnail);
    }


    @Transactional
    public void deleteRenteeById(Long id) {
        try {
            Rentee rentee = getRenteeById(id);

            imageHandler.deleteImage(rentee.getThumbnail().getFilePath());
            renteeRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundRenteeException();
        }
    }
}
