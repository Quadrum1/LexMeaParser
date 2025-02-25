package parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import parsing.domain.Row;
import parsing.domain.TableOfContent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {

    public static void parseHTML(String xml_content) {
        Document doc = Jsoup.parse(xml_content, "", Parser.xmlParser());
        Element data = doc.getElementsByTag("data").getFirst();

        ArrayList<Row> rows = new ArrayList<>();
        for (int i = 0; i < data.childrenSize(); i++) {
            Element child = data.children().get(i);
            Row row = new Row(child);
            rows.add(row);
            Element text = row.getText();

            Element toc = text.getElementById("tableOfContents");
            if (toc != null) {
                TableOfContent tableOfContent = new TableOfContent(toc);
                System.out.println(tableOfContent);
            }
            System.out.println("Row done");
        }

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
