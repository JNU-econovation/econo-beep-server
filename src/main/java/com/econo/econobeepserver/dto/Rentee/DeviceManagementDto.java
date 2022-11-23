package com.econo.econobeepserver.dto.Rentee;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DeviceManagementDto {

    private Long totalCount;
    private List<DeviceManagementElementDto> deviceManagementElementDtos;

    @Builder
    public DeviceManagementDto(Long totalCount, List<DeviceManagementElementDto> deviceManagementElementDtos) {
        this.totalCount = totalCount;
        this.deviceManagementElementDtos = deviceManagementElementDtos;
    }
}
