package com.accenture.bars.file;

import com.accenture.bars.domain.Request;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractInputFile {
    public static final int MIN_BILLING_CYCLE = 1;
    public static final int MAX_BILLING_CYCLE = 12;
    private File file;

    public AbstractInputFile() {}

    public List<Request> readFile() throws IOException {
        return (List<Request>) file;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}