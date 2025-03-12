package com.bos.usertaskmanager.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TaskFilterInput {
    private List<String> statuses;
    private List<String> usernames;
    private List<String> partitions;
    private List<String> licenseTypes;
    private List<String> cpuRange;
    private Double cpuOverValue;
    private List<Double> sanitizedCpuRange;
    private List<String> memRange;
    private List<MemRange> sanitizedMemRange;

    private String submittedFrom;
    private String submittedTo;
    private String startedFrom;
    private String startedTo;
    private String endedFrom;
    private String endedTo;

    public void sanitize() {
        if(statuses != null && statuses.isEmpty()) statuses = null;
        if(usernames != null && usernames.isEmpty()) usernames = null;
        if(partitions != null && partitions.isEmpty()) partitions = null;
        if(licenseTypes != null && licenseTypes.isEmpty()) licenseTypes = null;
        if(cpuRange != null && !cpuRange.isEmpty()) {
            sanitizedCpuRange = new ArrayList<Double>();
            for (String cpu : cpuRange) {
                if (cpu.endsWith("+")) {
                    cpuOverValue = Double.parseDouble(cpu.replace("+", ""));
                } else {
                    sanitizedCpuRange.add(Double.parseDouble(cpu));
                }
            }
        }
        if (memRange != null && !memRange.isEmpty()) {
            sanitizedMemRange = new ArrayList<>();
            for (String range : memRange) {
                try {
                    MemRange memRange = new MemRange();
                    if(range.contains("~")) {
                        String[] mems = range.split("~");
                        if(mems.length == 2) {
                            memRange.minMem = parseToMb(mems[0]);
                            memRange.maxMem = parseToMb(mems[1]);
                        }
                    } else if (range.endsWith("+")) {
                        String minPart = range.replace("+", "");
                        memRange.minMem = parseToMb(minPart);
                        memRange.maxMem = null;
                    }
                    sanitizedMemRange.add(memRange);
                } catch (NumberFormatException e) {
                    // ignore invalid range
                }
            }
        }
    }

    public class MemRange {
        private Double minMem;
        private Double maxMem;
    }

    private Double parseToMb(String memStr) {
        memStr = memStr.trim().toUpperCase();
        if (memStr.endsWith("GB")) {
            return Double.parseDouble(memStr.replace("GB", "").trim()) * 1024L;
        } else if (memStr.endsWith("MB")) {
            return Double.parseDouble(memStr.replace("MB", "").trim());
        } else if (memStr.endsWith("KB")) {
            return Double.parseDouble(memStr.replace("KB", "").trim()) / 1024L;
        } else {
            return Double.parseDouble(memStr);
        }
    }
}