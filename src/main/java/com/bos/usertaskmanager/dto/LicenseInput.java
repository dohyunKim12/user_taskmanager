package com.bos.usertaskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicenseInput {
    private String licenseId;
    private String licenseType;
    private int maxVal;
    private int currentVal;
}
