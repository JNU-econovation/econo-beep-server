package com.econo.econobeepserver.service.Rentee;

import com.econo.econobeepserver.domain.Bookmark.Bookmark;
import com.econo.econobeepserver.domain.Bookmark.BookmarkRepository;
import com.econo.econobeepserver.domain.Rental.RentalRepository;
import com.econo.econobeepserver.domain.Rentee.*;
import com.econo.econobeepserver.domain.User.User;
import com.econo.econobeepserver.dto.Rentee.*;
import com.econo.econobeepserver.exception.DuplicatedBookmarkException;
import com.econo.econobeepserver.exception.NotFoundBookmarkException;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.service.ImageHandler;
import com.econo.econobeepserver.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RenteeService {

    private final RenteeRepository renteeRepository;
    private final RenteeThumbnailRepository thumbnailRepository;
    private final BookmarkRepository bookmarkRepository;
    private final RentalRepository rentalRepository;
    private final ImageHandler imageHandler;
    private final UserService userService;

    @Transactional
    public Rentee createRentee(RenteeSaveDto renteeSaveDto) {
        Rentee rentee = renteeSaveDto.toEntity();
        RenteeThumbnail thumbnail = imageHandler.parseRenteeThumbnail(renteeSaveDto.getThumbnail());

        imageHandler.downloadImage(renteeSaveDto.getThumbnail(), thumbnail.getFilePath());
        thumbnail = thumbnailRepository.save(thumbnail);
        rentee.setRenteeThumbnail(thumbnail);

        return renteeRepository.save(rentee);
    }

    private void validateDuplicatedBookmark(long userId, long renteeId) {
        if (bookmarkRepository.existsByUser_IdAndRentee_Id(userId, renteeId)) {
            throw new DuplicatedBookmarkException();
        }
    }

    public Bookmark registerBookmark(Long renteeId, Long userId) {
        User user = userService.getUserByUserId(userId);
        Rentee rentee = getRenteeById(renteeId);

        validateDuplicatedBookmark(user.getId(), rentee.getId());

        Bookmark bookmark = new Bookmark(user, rentee);
        return bookmarkRepository.save(bookmark);
    }

    public Rentee getRenteeById(Long id) {
        return renteeRepository.findById(id).orElseThrow(NotFoundRenteeException::new);
    }

    public Rentee getRenteeByName(String name) {
        return renteeRepository.findByName(name).orElseThrow(NotFoundRenteeException::new);
    }

    public RenteeInfoDto getRenteeInfoDtoByIdWithUserId(Long id, Long userId) {
        Rentee rentee = getRenteeById(id);
        List<RentalElementDto> rentalElementDtos = rentalRepository.findByRentee_IdOrderByCreatedDateDesc(rentee.getId()).stream().map(RentalElementDto::new).collect(Collectors.toList());
        boolean isBookmarked = false;
        int bookmarkCount = bookmarkRepository.countByRentee_Id(rentee.getId());

        if (userId != null) {
            isBookmarked = bookmarkRepository.findByUser_IdAndRentee_Id(userId, rentee.getId()).isPresent();
        }

        return new RenteeInfoDto(rentee, rentalElementDtos, isBookmarked, bookmarkCount);
    }

    public List<RenteeElementDto> searchRenteeElementDtosByNameWithPaging(String name, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Rentee> rentees = renteeRepository.findByNameContaining(name, pageRequest);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> searchRenteeElementDtosByNameFromBookWithPaging(String name, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Rentee> rentees = renteeRepository.findByTypeAndNameContaining(RenteeType.BOOK, name, pageRequest);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> searchRenteeElementDtosByNameFromDeviceWithPaging(String name, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Rentee> rentees = renteeRepository.findByTypeAndNameContaining(RenteeType.DEVICE, name, pageRequest);

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
            case OUTDATED_RENTAL:
                rentees = renteeRepository.findRenteesNameContainingFromBookOrderByOutdatedRentalWithPaging(name, pageRequest);
                break;
            case LATEST_RENTAL:
                rentees = renteeRepository.findRenteesNameContainingFromBookOrderByLatestRentalWithPaging(name, pageRequest);
                break;
        }

        return rentees.stream()
                .map(rentee -> new RenteeManagementInfoDto(rentee, rentalRepository.findFirstByRentee_IdOrderByCreatedDateDesc(rentee.getId()).orElse(null)))
                .collect(Collectors.toList());
    }

    public List<RenteeManagementInfoDto> searchRenteeManagementInfoDtosByNameFromDeviceWithSortAndPaging(String name, RenteeSort renteeSort, int pageIndex, int pageSize) {
        List<Rentee> rentees = Collections.emptyList();
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        switch (renteeSort) {
            case NONE:
                rentees = renteeRepository.findRenteesNameContainingFromDeviceWithPaging(name, pageRequest);
                break;
            case CREATED_ASC:
                rentees = renteeRepository.findRenteesNameContainingFromDeviceOrderByCreatedAscWithPaging(name, pageRequest);
                break;
            case CREATED_DESC:
                rentees = renteeRepository.findRenteesNameContainingFromDeviceOrderByCreatedDescWithPaging(name, pageRequest);
                break;
            case LATEST_RENTAL:
                rentees = renteeRepository.findRenteesNameContainingFromDeviceOrderByLatestRentalWithPaging(name, pageRequest);
                break;
            case OUTDATED_RENTAL:
                rentees = renteeRepository.findRenteesNameContainingFromDeviceOrderByOutdatedRentalWithPaging(name, pageRequest);
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

    public List<RenteeElementDto> getBookmarkedRenteeByUserId(long userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByUser_Id(userId);

        return bookmarks.stream().map((bookmark -> new RenteeElementDto(bookmark.getRentee()))).collect(Collectors.toList());
    }

    @Transactional
    public void updateRenteeById(long id, RenteeSaveDto renteeSaveDto) {
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

    public void unregisterBookmark(Long renteeId, Long userId) {
        User user = userService.getUserByUserId(userId);
        Rentee rentee = getRenteeById(renteeId);

        Bookmark bookmark = bookmarkRepository.findByUser_IdAndRentee_Id(user.getId(), rentee.getId()).orElseThrow(NotFoundBookmarkException::new);
        bookmarkRepository.delete(bookmark);
    }
}
