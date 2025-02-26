package de.techfak.gpse.explo.parsing;

import de.techfak.gpse.explo.parsing.domain.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void parseHTML(String xml_content) {
        Document doc = Jsoup.parse(xml_content, "", Parser.xmlParser());
        Element data = doc.getElementsByTag("data").getFirst();

        HashMap<String, Row> rowMap = new HashMap<>();
        ArrayList<Row> rows = new ArrayList<>();
        for (int i = 0; i < data.childrenSize(); i++) {
            Element child = data.children().get(i);
            Row row = new Row(child);

            Element text = row.getText();
            Element toc = text.getElementById("tableOfContents");
            if (toc != null) {
                TableOfContent tableOfContent = new TableOfContent(toc);
                row.setTable_of_content(tableOfContent);
            }

            Elements problems = text.select("section[data-style='problem']");

            for (int j = 0; j < problems.size(); j++) {
                Element problemNode = problems.get(j);
                Problem problem = new Problem(problemNode);
                row.addProblem(problem);
                System.out.println(problem);
                System.out.println(row.getId());
            }

            Elements definitionNodes = text.select("section[data-style='definition']");
            for (int j = 0; j < definitionNodes.size(); j++) {
                Element definitionBox = definitionNodes.get(j);
                ArrayList<Definition> definitions = DefinitionFactory.parse(definitionBox);
                row.addDefinitions(definitions);
                if (!definitions.isEmpty()) {
                    System.out.println(definitions.getFirst());
                    System.out.println(row.getId());
                }
            }
            rowMap.put(row.getId(), row);
            rows.add(row);
        }



        System.out.println(rowMap.get("236"));

        System.out.println("Total rows parsed: " + rows.size());
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String file = "src/main/resources/schemas.xml";
        try (InputStream inputStream = new FileInputStream(file)) {
            // Identical text found on the website, the file is just a static representation
            String xml = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            parseHTML(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
