package com.accenture.bars.factory;

import com.accenture.bars.exception.BarsException;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.file.CSVInputFileImpl;
import com.accenture.bars.file.TextInputFileImpl;

import java.io.File;

import static com.accenture.bars.exception.BarsException.FILE_NOT_SUPPORTED;

public class InputFileFactory {

    private static InputFileFactory factory;

    private InputFileFactory() {
        // empty constructor
    }

    public static InputFileFactory getInstance() {
        if (factory == null) {
            factory = new InputFileFactory();
        }
        return factory;
    }

    public AbstractInputFile getInputFile(File file) throws BarsException {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".txt") != -1
                && fileName.lastIndexOf(".txt") != 0) {
            return new TextInputFileImpl();
        } else if (fileName.lastIndexOf(".csv") != -1
                && fileName.lastIndexOf(".csv") != 0) {
            return new CSVInputFileImpl();
        } else {
            throw new BarsException(FILE_NOT_SUPPORTED);
        }
    }
}
