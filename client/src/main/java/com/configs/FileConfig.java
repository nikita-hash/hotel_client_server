package com.configs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class FileConfig {
    private static FileConfig fileConfigur=new FileConfig();
    private String path="config.txt";
    public FileConfig(){
    }

    public String getStyle() throws FileNotFoundException {
        Reader reader =new FileReader(path);
        Scanner scanner=new Scanner(reader);
        return scanner.nextLine();
    }

    public String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    public static FileConfig getFileConfigur(){
        return fileConfigur;
    }
}
