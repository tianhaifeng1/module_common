package com.example.module_common.model;

import android.location.Location;

/**
 * @ProjectName: QualityShield
 * @Package: com.example.module_common.model
 * @ClassName: LocationModel
 * @Description:
 * @Author: tian
 * @CreateDate: 2022/7/27 17:15
 */
public class LocationModel {
    private Location location;

    public Location getLocation() {
        return location;
    }

    public LocationModel(Location location) {
        this.location = location;
    }
}
