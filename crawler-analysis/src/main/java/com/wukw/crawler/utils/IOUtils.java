package com.wukw.crawler.utils;

import java.io.*;

public class IOUtils {

    public static String  inputToString(InputStream inputStream){
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String line = " ";
        while (true){
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.append(line);
        }
        return buffer.toString();
    }

}
