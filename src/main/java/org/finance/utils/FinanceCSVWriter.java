package org.finance.utils;

import jakarta.enterprise.context.ApplicationScoped;
import org.finance.models.finance.BaseFinance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

import static org.finance.utils.CSVFileProperties.CSV_HEADER;

@ApplicationScoped
public class FinanceCSVWriter {

    public void writeFinanceCSV(String fileName, BaseFinance... dataList) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName))) {
            fileWriter.write(CSV_HEADER.getValue());
            fileWriter.newLine();

            for (BaseFinance data : dataList) {
                fileWriter.write(
                        data.getPrice() + "," +
                        data.getPriceChange() + "," +
                        data.getDisplayName() + "," +
                        data.getTimeLastUpdated()
                );
                fileWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void appendFinanceCSV(String fileName, BaseFinance... dataList) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            for (BaseFinance data : dataList) {
                fileWriter.write(
                        data.getPrice() + "," +
                        data.getDifferencePrice() + "," +
                        data.getPriceChange() + "," +
                        data.getDisplayName() + "," +
                        data.getTimeLastUpdated() + "," +
                        Instant.now().toEpochMilli()
                );
            }

            fileWriter.newLine();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}