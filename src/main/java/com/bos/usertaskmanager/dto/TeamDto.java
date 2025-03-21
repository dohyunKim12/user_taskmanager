package com.bos.usertaskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto {
    private String teamId;
    private String teamName;
    private List<String> LicenseTypes = new ArrayList<>();
}