package com.util;

import com.enums.Regions;
import org.springframework.stereotype.Component;


@Component
public class RegionMapping {
    public String regionMapping(String region) throws IllegalArgumentException {
        String code = Regions.getCodeByName(region);

        if (code != null) {
            return code;
        }

        code = Regions.getCodeByName(Regions.getNameByCode(region));
        if (code != null) {
            return code;
        }

        throw new IllegalArgumentException("Invalid region: " + region);
    }
}

