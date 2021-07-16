package com.accenture.bars.file;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static com.accenture.bars.exception.BarsException.*;

public class TextInputFileImpl extends AbstractInputFile {
    private static final Logger log = null;

    public TextInputFileImpl() {
        // empty constructor
    }

    @Override
    public List<Request> readFile() throws IOException, BarsException {
        List<Request> requests = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        Scanner scanner = new Scanner(System.in);

        int billingCycle = 0;
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                try {
                    billingCycle = Integer.parseInt(line.substring(0, 1));
                    if (billingCycle >= MIN_BILLING_CYCLE
                            || billingCycle <= MAX_BILLING_CYCLE) {
                        //requests.add(new Request(billingCycle));
                    }
                } catch (BarsException e) {
                    throw new BarsException(BILLING_CYCLE_NOT_ON_RANGE);
                }
            }
        } catch (BarsException e) {
            throw new BarsException(INVALID_BILLING_CYCLE);
        }

        LocalDate startDate;
        try {
            String line = scanner.nextLine();
            String[] data = line.split(" ");
            startDate = LocalDate.parse(line.substring(2, 9), formatter);
        } catch (BarsException e) {
            throw new BarsException(INVALID_START_DATE_FORMAT);
        }

        LocalDate endDate;
        try {
            String line = scanner.nextLine();
            String[] data = line.split(",");
            endDate = LocalDate.parse(line.substring(10, 17), formatter);
        } catch (BarsException e) {
            throw new BarsException(INVALID_END_DATE_FORMAT);
        }

        requests.add(new Request(billingCycle, startDate, endDate));

        return requests;
    }
}
