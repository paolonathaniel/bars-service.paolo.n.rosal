package com.accenture.bars.controller;

import com.accenture.bars.domain.Record;
import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin("*")
public class BarsController {
    private static final Logger log =
            LoggerFactory.getLogger(BarsController.class);

    private FileProcessor fileProcessor;

    @Autowired
    public BarsController(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @GetMapping("/bars")
    public List<Record> requestBilling(@RequestParam
            ("filePath") String fileName)
            throws BarsException, IOException {
        File file = new File("C:\\BARS_TEST\\valid-txt.txt");
        List<Request> requests = fileProcessor.execute(file);
        List<Record> records = fileProcessor.retrieveRecordfromDB(requests);
        return records;
    }
}
