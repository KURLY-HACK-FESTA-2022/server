package com.kurly.hackfesta.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NoticeDto {

    private int status;

    private LocalDateTime createdAt;

    private String detail;

    private String name;
}
