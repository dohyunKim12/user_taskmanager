package com.bos.usertaskmanager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class License {
    private String licenseId;
    private String licenseType;
    private int maxVal;
    private int currentVal;
}
