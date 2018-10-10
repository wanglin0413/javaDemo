package com.example.demo.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputTest {

    public static void main(String[] args){
        File file = new File("test");
        FileInputStream inputStream = null;
        try {
             inputStream = new FileInputStream(file);
             byte buf[] = new byte[1024];
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
