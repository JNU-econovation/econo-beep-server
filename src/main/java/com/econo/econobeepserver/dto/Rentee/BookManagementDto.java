package com.econo.econobeepserver.dto.Rentee;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class BookManagementDto {

    private Long totalCount;
    private List<BookManagementElementDto> bookManagementElementDtos;

    @Builder
    public BookManagementDto(Long totalCount, List<BookManagementElementDto> bookManagementElementDtos) {
        this.totalCount = totalCount;
        this.bookManagementElementDtos = bookManagementElementDtos;
    }
}
