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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author jack
 */
public class ConfigParser {
    
    private static final String FILE_NAME = "config.ini";
    private static final String CONFIG_PATH = "res/";

    private static File file;
    
    public LinkedHashMap<String, ConfigSection> sections;
    
    public ConfigParser() {
        sections = new LinkedHashMap<>();
        ConfigParser.file = new File(ConfigParser.CONFIG_PATH + ConfigParser.FILE_NAME);
        this.readFile();
    }
    
    private void readFile() {
        System.out.println("Reading file " + ConfigParser.FILE_NAME);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(ConfigParser.file));
            System.out.println("Successfully loaded file " + ConfigParser.FILE_NAME);
            String currentSection = "";
            String line;
            while ((line = br.readLine()) != null) {
                String[] kvPair;
                if (!line.equals("")) {
                    if (line.charAt(0) == '[' && line.charAt(line.length() - 1) == ']') {
                        currentSection = this.addSection(line.substring(1, line.length() - 1));
                        System.out.println("[ConfigParser] New section added \"[" + line.substring(1, line.length() - 1) + "]\"");
                    } else if (!currentSection.equals("")) {
                        kvPair = line.split("=");
                        this.sections.get(currentSection).set(kvPair[0], kvPair[1].equals("\"\"") ? "" : kvPair[1]);
                        System.out.println("[ConfigParser] New K, V pair { " + kvPair[0] + ", " + kvPair[1] + " } added to section \"" + currentSection + "\"");
                    }
                } else {
                    currentSection = "";
                }
            }
        } catch (IOException e) {
            System.err.println("IOException caught!");
        } catch (StringIndexOutOfBoundsException ex) {
            System.err.println("StringIndexOutOfBoundsException caught!");
        }
    }
    
    private void writeFile() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(ConfigParser.file);
            for (Map.Entry<String, ConfigSection> e : sections.entrySet()) {
                pw.print("[" + e.getKey() + "]\n");
                for (Map.Entry<String, String> f : e.getValue().getDataMap().entrySet()) {
                    pw.print(f.getKey() + "=" + (f.getValue().equalsIgnoreCase("") ? "\"\"" : f.getValue()) + "\n");
                }
                pw.print("");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot write to config file! File " + ConfigParser.FILE_NAME + " not found!");
        }
    }
    
    public String addSection(String section) {
        this.sections.put(section, new ConfigSection(section));
        return section;
    }
    
    public ConfigSection getSection(String section) {
        return this.sections.get(section);
    }
    
    public void replaceSection(String oldSection, ConfigSection newSection) {
        this.sections.replace(oldSection, newSection);
    }
    
    public void set(String section, String k, String v) {
        if (this.sections.get(section) != null) {
            this.sections.get(section).replace(k, v);
        } else {
            System.out.println("Section " + section + " does not exist!");
        }
    }
    
    public String get(String section, String k) {
        return this.sections.get(section).get(k);
    }
    
    public void writeConfig() {
        this.writeFile();
    }
    
}
