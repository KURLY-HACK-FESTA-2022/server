package com.kurly.hackfesta.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {
    private String token;
    private String email;
    private String password;
    private String name;
    private Long id;
}