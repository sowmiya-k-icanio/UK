/*
 * Copyright (c) Icanio
 */

package com.example.compass.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String companyName;
    private Boolean acceptTnc;
}
