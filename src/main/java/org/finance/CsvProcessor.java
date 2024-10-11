package org.finance;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.model.huggingface.client.EmbeddingRequest;
import jakarta.inject.Inject;
import org.finance.utils.CSVFileProperties;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CsvProcessor {

    @Inject
    DocumentCreation documentCreation;

    @Inject
    EmbeddingModel embeddingModel;

    public void processCsv(String filePath, List<String> columnsToIncludeInMetadata) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<String> headers = Arrays.asList(
                CSVFileProperties.CSV_HEADER.getValue()
                        .split(CSVFileProperties.DELIMITER.getValue()));
        for (int i = 1; i < lines.size(); i++) {
            List<String> line = Arrays.asList(
                    lines.get(i).split(
                            CSVFileProperties.DELIMITER.getValue()));
            Document document = documentCreation.createFromCSVLine(
                    headers, line, columnsToIncludeInMetadata);
            embeddingModel.embed(new EmbeddingRequest(List.of(document.text()), true));
        }
    }

}