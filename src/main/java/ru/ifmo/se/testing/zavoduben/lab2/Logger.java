package ru.ifmo.se.testing.zavoduben.lab2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logger {

    private File file;

    public Logger(File file){
        this.file = file;
    }

    public void write(String name, Double x,  Double value) {
        try {
            String new_line = name + " , " + x + " , " + value + "\n";
            String path = file.getPath();
            Files.write(Paths.get(path), new_line.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
