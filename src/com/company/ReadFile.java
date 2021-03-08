package com.company;

import java.io.*;

 class Readfile {

    // Metodo que le um arquivo para dentro de uma string
    public static String LoadFileToString(String filename) {
        File sourcefile = new File(filename);
        if (sourcefile.isFile()) {
            if (sourcefile.canRead()) {
                StringBuffer strBuff = new StringBuffer();
                try {
                    FileReader fReader = new FileReader(sourcefile);
                    BufferedReader p = new BufferedReader(fReader);
                    String str = p.readLine();
                    while (str != null) {
                        strBuff.append(str + "\n");
                        str = p.readLine();
                    }
                    p.close();
                    fReader.close();
                    return strBuff.toString();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("\n" + "Access denied! You cannot read this file.");
            }
        }
        return null;
    }
}
