package com.econo.econobeepserver.service.Rentee;

import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RenteeThumbnail;
import com.econo.econobeepserver.domain.Rentee.RenteeThumbnailRepository;
import com.econo.econobeepserver.domain.Rentee.RenteeRepository;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import com.econo.econobeepserver.dto.Rentee.*;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.service.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RenteeService {

    private final RenteeRepository renteeRepository;
    private final RenteeThumbnailRepository thumbnailRepository;

    private final ImageHandler imageHandler;

    @Transactional
    public Long createRentee(RenteeSaveDto renteeSaveDto) {
        Rentee rentee = renteeSaveDto.toEntity();
        RenteeThumbnail thumbnail = imageHandler.parseThumbnail(renteeSaveDto.getThumbnail());
        rentee.setRenteeThumbnail(thumbnail);
        thumbnail.setRentee(rentee);

        imageHandler.downloadImage(renteeSaveDto.getThumbnail(), thumbnail.getFilePath());
        long renteeId = renteeRepository.save(rentee).getId();
        thumbnailRepository.save(thumbnail);

        return renteeId;
    }


    public Rentee getRenteeById(Long id) {
        Optional<Rentee> rentee = renteeRepository.findById(id);
        if (rentee.isEmpty()) {
            throw new NotFoundRenteeException();
        }

        return rentee.get();
    }

    public RenteeInfoDto getRenteeInfoDtoById(Long id) {
        Rentee rentee = getRenteeById(id);

        return new RenteeInfoDto(rentee);
    }
    
    public List<RenteeElementDto> getRenteeElementDtosWithPaging(int pageSize, Long lastId) {
        List<Rentee> rentees = renteeRepository.getRenteesWithPaging(pageSize, lastId);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> getRenteeElementDtosByRenteeTypeEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
        List<Rentee> rentees = renteeRepository.getRenteesByTypeEqualWithPaging(renteeType, pageSize, lastId);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> getRenteeElementDtosByRenteeTypeNotEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
        List<Rentee> rentees = renteeRepository.getRenteesByTypeNotEqualWithPaging(renteeType, pageSize, lastId);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> searchRenteeElementDtosByKeyword(String keyword) {
        List<Rentee> rentees = renteeRepository.searchRentee(keyword);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> searchRenteeElementDtosByRenteeTypeEqualByKeyword(RenteeType renteeType, String keyword) {
        List<Rentee> rentees = renteeRepository.searchRenteeByRenteeTypeEqual(renteeType, keyword);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> searchRenteeElementDtosByRenteeTypeNotEqualByKeyword(RenteeType renteeType, String keyword) {
        List<Rentee> rentees = renteeRepository.searchRenteeByRenteeTypeNotEqual(renteeType, keyword);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeManagementInfoDto> getRenteeManagementInfoDtosByTypeEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
        List<Rentee> rentees = renteeRepository.getRenteesByTypeEqualWithPaging(renteeType, pageSize, lastId);

        return rentees.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
    }

    public List<RenteeManagementInfoDto> getRenteeManagementInfoDtosByTypeNotEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
        List<Rentee> rentees = renteeRepository.getRenteesByTypeNotEqualWithPaging(renteeType, pageSize, lastId);

        return rentees.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
    }

    public List<RenteeManagementInfoDto> searchRenteeManagementInfoDtosByKeyword(String keyword) {
        List<Rentee> rentees = renteeRepository.searchRentee(keyword);

        return rentees.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
    }

    public String getThumbnailFilePathByRenteeId(long renteeId) {
        Rentee rentee = getRenteeById(renteeId);

        return rentee.getThumbnail().getFilePath();
    }

    @Transactional
    public void updateRenteeById(Long id, RenteeSaveDto renteeSaveDto) {
        Rentee rentee = getRenteeById(id);
        RenteeThumbnail oldRenteeThumbnail = rentee.getThumbnail();
        RenteeThumbnail newRenteeThumbnail = imageHandler.parseThumbnail(renteeSaveDto.getThumbnail());

        imageHandler.deleteImage(oldRenteeThumbnail.getFilePath());
        thumbnailRepository.deleteById(oldRenteeThumbnail.getId());

        rentee.updateInformation(renteeSaveDto);
        rentee.setRenteeThumbnail(newRenteeThumbnail);
        newRenteeThumbnail.setRentee(rentee);

        imageHandler.downloadImage(renteeSaveDto.getThumbnail(), newRenteeThumbnail.getFilePath());
        thumbnailRepository.save(newRenteeThumbnail);
    }


    @Transactional
    public void deleteRenteeById(Long id) {
        try {
            Rentee rentee = getRenteeById(id);
            rentee.clearAttributes();

            renteeRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundRenteeException();
        }
    }
}