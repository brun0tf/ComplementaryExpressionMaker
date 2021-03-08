package com.company;

import java.io.*;

public class CreateFile {
    private FileOutputStream buffer;

    public void createFile(String fileName) throws FileNotFoundException {
        this.buffer = new FileOutputStream("OutputFile/" + fileName + ".txt");
    }
    public void addLine(String expression) throws IOException {
        BufferedWriter arch = null;
        assert arch != null;
        arch.write(expression);

    }

}

