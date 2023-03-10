package com.econo.econobeepserver.service.Rentee;

import com.econo.econobeepserver.domain.Bookmark.Bookmark;
import com.econo.econobeepserver.domain.Bookmark.BookmarkRepository;
import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.domain.Rental.RentalRepository;
import com.econo.econobeepserver.domain.Rentee.*;
import com.econo.econobeepserver.domain.User.User;
import com.econo.econobeepserver.dto.Rentee.*;
import com.econo.econobeepserver.dto.User.UserRenterDto;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

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
    public Rentee create(RenteeSaveDto renteeSaveDto) {
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
        Rentee rentee = getRenteeByRenteeId(renteeId);

        validateDuplicatedBookmark(user.getId(), rentee.getId());

        Bookmark bookmark = new Bookmark(user, rentee);
        return bookmarkRepository.save(bookmark);
    }

    public Rentee getRenteeByRenteeId(Long renteeId) {
        return renteeRepository.findById(renteeId).orElseThrow(NotFoundRenteeException::new);
    }

    public Rentee getRenteeByRenteeName(String renteeName) {
        return renteeRepository.findByName(renteeName).orElseThrow(NotFoundRenteeException::new);
    }

    public RenteeInfoDto getRenteeInfoDtoByIdWithUserId(Long renteeId, Long userId) {
        Rentee rentee = getRenteeByRenteeId(renteeId);

        List<RentalElementDto> rentalElementDtos = rentalRepository
                .findByRentee_IdOrderByCreatedDateDesc(rentee.getId())
                .stream()
                .map(rental -> {
                    long renterId = rental.getRenter().getId();
                    UserRenterDto userRenterDto = userService.getUserRenterDtoByUserId(renteeId);

                    return new RentalElementDto(rental, userRenterDto);
                })
                .collect(Collectors.toList());

        boolean isBookmarked = false;
        if (userId != null) {
            isBookmarked = bookmarkRepository.findByUser_IdAndRentee_Id(userId, rentee.getId()).isPresent();
        }

        int bookmarkCount = bookmarkRepository.countByRentee_Id(rentee.getId());

        return new RenteeInfoDto(rentee, rentalElementDtos, isBookmarked, bookmarkCount);
    }

    public List<RenteeElementDto> getRenteeElementDtosByRenteeNameContainingWithPaging(String renteeName, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Rentee> rentees = renteeRepository.findByNameContaining(renteeName, pageRequest);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> getRenteeElementDtosByBookNameWithPaging(String renteeName, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Rentee> rentees = renteeRepository.findByTypeAndNameContaining(RenteeType.BOOK, renteeName, pageRequest);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    public List<RenteeElementDto> getRenteeElementDtosByDeviceNameWithPaging(String renteeName, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Rentee> rentees = renteeRepository.findByTypeAndNameContaining(RenteeType.DEVICE, renteeName, pageRequest);

        return rentees.stream().map(RenteeElementDto::new).collect(Collectors.toList());
    }

    private RenteeManagementInfoDto getRenteeManagementInfoDtoByRentee(final Rentee rentee) {
        String recentRenterName = null;
        Long recentRentalEpochSecond = null;

        Optional<Rental> recentRental = rentalRepository.findFirstByRentee_IdOrderByCreatedDateDesc(rentee.getId());
        if (recentRental.isPresent()) {
            Long renterId = recentRental.get().getRenter().getId();
            recentRenterName = userService.getUserRenterDtoByUserId(renterId).getUsername();
            recentRentalEpochSecond = toEpochSecond(recentRental.get().getRentalDateTime());
        }

        return new RenteeManagementInfoDto(rentee, recentRenterName, recentRentalEpochSecond);
    }

    public Long countByBookNameContainingWithSort(String renteeName, RenteeSort renteeSort) {
        Long count = null;

        switch (renteeSort) {
            case NONE:
                count = renteeRepository.countByRenteeNameContainingFromBook(renteeName);
                break;
            case CREATED_ASC:
                count = renteeRepository.countByRenteeNameContainingFromBookOrderByCreatedAsc(renteeName);
                break;
            case CREATED_DESC:
                count = renteeRepository.countByRenteeNameContainingFromBookOrderByCreatedDesc(renteeName);
                break;
            case OUTDATED_RENTAL:
                count = renteeRepository.countByRenteeNameContainingFromBookOrderByOutdatedRental(renteeName);
                break;
            case LATEST_RENTAL:
                count = renteeRepository.countByRenteeNameContainingFromBookOrderByLatestRental(renteeName);
                break;
        }

        return count;
    }

    public List<RenteeManagementInfoDto> getRenteeManagementInfoDtosByBookNameContainingWithSortAndPaging(String renteeName, RenteeSort renteeSort, int pageIndex, int pageSize) {
        List<Rentee> rentees = Collections.emptyList();
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        switch (renteeSort) {
            case NONE:
                rentees = renteeRepository.findByRenteeNameContainingFromBookWithPaging(renteeName, pageRequest);
                break;
            case CREATED_ASC:
                rentees = renteeRepository.findByRenteeNameContainingFromBookOrderByCreatedAscWithPaging(renteeName, pageRequest);
                break;
            case CREATED_DESC:
                rentees = renteeRepository.findByRenteeNameContainingFromBookOrderByCreatedDescWithPaging(renteeName, pageRequest);
                break;
            case OUTDATED_RENTAL:
                rentees = renteeRepository.findByRenteeNameContainingFromBookOrderByOutdatedRentalWithPaging(renteeName, pageRequest);
                break;
            case LATEST_RENTAL:
                rentees = renteeRepository.findByRenteeNameContainingFromBookOrderByLatestRentalWithPaging(renteeName, pageRequest);
                break;
        }

        return rentees.stream()
                .map(this::getRenteeManagementInfoDtoByRentee)
                .collect(Collectors.toList());
    }

    public Long countByDeviceNameContainingWithSort(String renteeName, RenteeSort renteeSort) {
        Long count = null;

        switch (renteeSort) {
            case NONE:
                count = renteeRepository.countByRenteeNameContainingFromDevice(renteeName);
                break;
            case CREATED_ASC:
                count = renteeRepository.countByRenteeNameContainingFromDeviceOrderByCreatedAsc(renteeName);
                break;
            case CREATED_DESC:
                count = renteeRepository.countByRenteeNameContainingFromDeviceOrderByCreatedDesc(renteeName);
                break;
            case LATEST_RENTAL:
                count = renteeRepository.countByRenteeNameContainingFromDeviceOrderByLatestRental(renteeName);
                break;
            case OUTDATED_RENTAL:
                count = renteeRepository.countByRenteeNameContainingFromDeviceOrderByOutdatedRental(renteeName);
                break;
        }

        return count;
    }

    public List<RenteeManagementInfoDto> getRenteeManagementInfoDtosByDeviceNameContainingWithSortAndPaging(String renteeName, RenteeSort renteeSort, int pageIndex, int pageSize) {
        List<Rentee> rentees = Collections.emptyList();
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        switch (renteeSort) {
            case NONE:
                rentees = renteeRepository.findByRenteeNameContainingFromDeviceWithPaging(renteeName, pageRequest);
                break;
            case CREATED_ASC:
                rentees = renteeRepository.findByRenteeNameContainingFromDeviceOrderByCreatedAscWithPaging(renteeName, pageRequest);
                break;
            case CREATED_DESC:
                rentees = renteeRepository.findByRenteeNameContainingFromDeviceOrderByCreatedDescWithPaging(renteeName, pageRequest);
                break;
            case LATEST_RENTAL:
                rentees = renteeRepository.findByRenteeNameContainingFromDeviceOrderByLatestRentalWithPaging(renteeName, pageRequest);
                break;
            case OUTDATED_RENTAL:
                rentees = renteeRepository.findByRenteeNameContainingFromDeviceOrderByOutdatedRentalWithPaging(renteeName, pageRequest);
                break;
        }

        return rentees.stream()
                .map(this::getRenteeManagementInfoDtoByRentee)
                .collect(Collectors.toList());
    }

    public String getThumbnailFilePathByRenteeId(long renteeId) {
        Rentee rentee = getRenteeByRenteeId(renteeId);

        return rentee.getThumbnail().getFilePath();
    }

    public List<RenteeElementDto> getRenteeElementDtosByBookmarkedAndUserId(long userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByUser_Id(userId);

        return bookmarks.stream().map((bookmark -> new RenteeElementDto(bookmark.getRentee()))).collect(Collectors.toList());
    }

    @Transactional
    public void updateByRenteeId(Long renteeId, RenteeSaveDto renteeSaveDto) {
        Rentee rentee = getRenteeByRenteeId(renteeId);
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
    public void deleteByRenteeId(Long renteeId) {
        try {
            Rentee rentee = getRenteeByRenteeId(renteeId);
            String thumbnailPath = rentee.getThumbnail().getFilePath();

            renteeRepository.deleteById(renteeId);
            imageHandler.deleteImage(thumbnailPath);

        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundRenteeException();
        }
    }

    public void unregisterBookmark(Long renteeId, Long userId) {
        User user = userService.getUserByUserId(userId);
        Rentee rentee = getRenteeByRenteeId(renteeId);

        Bookmark bookmark = bookmarkRepository.findByUser_IdAndRentee_Id(user.getId(), rentee.getId()).orElseThrow(NotFoundBookmarkException::new);
        bookmarkRepository.delete(bookmark);
    }
}
