package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.domain.Rentee.RentState;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

@Setter
@Getter
@NoArgsConstructor
public class DeviceManagementElementDto {


    private Long id;
    private String thumbnailUrl;
    private RenteeType type;
    private String name;
    private String note;
    private RentState rentState;

    private String recentRenterName;
    private Long recentRentalEpochSecond;

    public DeviceManagementElementDto(Rentee rentee, String recentRenterName, Long recentRentalEpochSecond) {
        this.id = rentee.getId();
        this.thumbnailUrl = "/api/rentees/" + rentee.getId() + "/thumbnail";
        this.type = rentee.getType();
        this.name = rentee.getName();
        this.note = rentee.getNote();
        this.rentState = rentee.getRentState();

        this.recentRenterName = recentRenterName;
        this.recentRentalEpochSecond = recentRentalEpochSecond;
    }

    public DeviceManagementElementDto(RenteeManagementInfoDto renteeManagementInfoDto) {
        this.id = renteeManagementInfoDto.getId();
        this.thumbnailUrl = "/api/rentees/" + renteeManagementInfoDto.getId() + "/thumbnail";
        this.type = renteeManagementInfoDto.getType();
        this.name = renteeManagementInfoDto.getName();
        this.note = renteeManagementInfoDto.getNote();
        this.rentState = renteeManagementInfoDto.getRentState();

        this.recentRenterName = renteeManagementInfoDto.getRecentRenterName();
        this.recentRentalEpochSecond = renteeManagementInfoDto.getRecentRentalEpochSecond();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceManagementElementDto)) return false;
        DeviceManagementElementDto that = (DeviceManagementElementDto) o;
        return getThumbnailUrl().equals(that.getThumbnailUrl()) && getType() == that.getType() && getName().equals(that.getName()) && Objects.equals(getNote(), that.getNote()) && getRentState() == that.getRentState();
    }

    @Override
    public int hashCode() {
        return 0;
    }
}