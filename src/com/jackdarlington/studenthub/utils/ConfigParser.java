/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jackdarlington.studenthub.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jack
 */
public class ConfigParser {
    
    private static final String configPath = "/config.ini";

    private static File file;
    
    HashMap<String, HashMap<String, String>> sections;
    
    public ConfigParser() {
        sections = new HashMap<>();
        this.file = new File(configPath);
        this.readFile();
    }
    
    private void readFile() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(this.file));
            String currentSection = "";
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] kvPair;
                if (line.charAt(0) == '[' && line.charAt(line.length()) == ']') {
                    currentSection = this.addSection(line.substring(1, line.length() - 1));
                    System.out.println("[ConfigParser] New section added \"[" + line.substring(1, line.length() - 1) + "]\"");
                } else if (!currentSection.equals("")) {
                    kvPair = line.split("=");
                    this.sections.get(currentSection).put(kvPair[0], kvPair[1]);
                    System.out.println("[ConfigParser] New K, V pair \"[" + kvPair[0] + ", " + kvPair[1] + "]\" added to section \"[" + currentSection + "]\"");
                }
                System.out.println("");
            }
        } catch (IOException e) {} 
    }
    
    private void writeFile() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(this.file);
            for (Map.Entry<String, HashMap<String, String>> e : sections.entrySet()) {
                pw.print("[" + e.getKey() + "]\n");
                for (Map.Entry<String, String> f : e.getValue().entrySet()) {
                    pw.print(f.getKey() + "=" + f.getValue() + "\n");
                }
                pw.println();
            }
        } catch (FileNotFoundException e) {}
    }
    
    public String addSection(String name) {
        this.sections.put(name, new HashMap<>());
        return name;
    }
    
    public void set(String section, String key, String value) {
        if (this.sections.get(section) != null) {
            this.sections.get(section).replace(key, value);
        } else {
            System.out.println("Section " + section + " does not exist!");
        }
    }
    
}
