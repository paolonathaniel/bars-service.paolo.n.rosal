package com.accenture.bars.controller;

import com.accenture.bars.domain.Record;
import com.accenture.bars.domain.Request;
import com.accenture.bars.entity.Billing;
import com.accenture.bars.exception.BarsException;
import com.accenture.bars.factory.InputFileFactory;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.repository.BillingRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.accenture.bars.exception.BarsException.NO_RECORDS_TO_WRITE;

@Component
public class FileProcessor {

    private BillingRepository billingRepository;

    public FileProcessor() {
        // empty constructor
    }

    public List<Request> execute(File file) throws IOException {
        InputFileFactory factory = InputFileFactory.getInstance();
        AbstractInputFile abstractInputFile = factory.getInputFile(file);
        abstractInputFile.setFile(file);
        List<Request> requests = abstractInputFile.readFile();
        return requests;
    }

    public List<Record> retrieveRecordfromDB(List<Request> requests) {
        List<Record> records = new ArrayList<>();
        for (Request request : requests) {
            Billing billing = billingRepository.
                    findByBillingCycleAndStartDateAndEndDate(
                            request.getBillingCycle(),
                            request.getStartDate(),
                            request.getEndDate());
            if (billing != null) {
                records.add(new Record());
            }
        }
        if (records != null) {
            throw new BarsException(NO_RECORDS_TO_WRITE);
        } else {
            return records;
        }
    }
}
