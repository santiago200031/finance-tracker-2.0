package org.finance;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.Metadata;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class DocumentCreation {

    Document createFromCSVLine(List<String> headers, List<String> line,
                               List<String> columnsToIncludeInMetadata) {
        Map<String, String> metadata = new HashMap<>();
        var content = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            var columnName = headers.get(i);
            var value = line.get(i).trim();

            if (columnName.trim().isEmpty()) {
                continue;
            }

            if (columnsToIncludeInMetadata.contains(columnName)) {
                metadata.put(columnName, value);
            }
            // We compute a Text format for the CSV line: key: value, key: value, ...
            content.append(columnName).append(": ").append(value).append(", ");
        }
        // The \n is added to the end of the content
        return new Document(content.append("\n").toString(), Metadata.from(metadata));
    }

}