/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.utils;

import java.util.LinkedHashMap;

/**
 * @author Jack Darlington
 * Student ID: 19082592
 * Date: 17/05/2023
 */

public class ConfigSection {

    public String sectionName;
    private LinkedHashMap<String, String> data;
    
    public LinkedHashMap<String, String> getDataMap() {
        return this.data;
    }
    
    public ConfigSection(String name) {
        this.sectionName = name;
        this.data = new LinkedHashMap<>();
    }
    
    public void set(String k, String v) {
        this.data.put(k, v);
    }
    
    public void replace(String k, String v) {
        this.data.replace(k, v);
    }
    
    public String get(String k) {
        return this.data.get(k);
    }
    
}
