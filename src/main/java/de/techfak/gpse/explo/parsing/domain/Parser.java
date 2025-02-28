package de.techfak.gpse.explo.parsing.domain;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.validation.Schema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Parser {
    HashMap<Integer, Entry> entries = new HashMap<>();


    public Parser() {

    }

    public void parseEntry(JSONObject entryObj, SchemaParent parent) {
        Entry entry = new Entry(entryObj);
        String textString = entry.getText();

        Document doc = Jsoup.parse(textString, "", org.jsoup.parser.Parser.xmlParser());
        Element text = (Element) doc;

        entry.setLawParent(parent);

        Element toc = text.getElementById("tableOfContents");
        if (toc != null) {
            TableOfContent tableOfContent = new TableOfContent(toc);

            entry.setTable_of_content(tableOfContent);
        }

        Elements problems = text.select("section[data-style='problem']");
        for (int j = 0; j < problems.size(); j++) {
            Element problemNode = problems.get(j);
            Problem problem = new Problem(problemNode);
            entry.addProblem(problem);
        }

        Elements definitionNodes = text.select("section[data-style='definition']");
        for (int j = 0; j < definitionNodes.size(); j++) {
            Element definitionBox = definitionNodes.get(j);
            ArrayList<Definition> definitions = DefinitionFactory.parse(definitionBox);
            entry.addDefinitions(definitions);
        }
        entries.put(entry.getId(), entry);
    }

    public void parseParagraph(JSONObject paragraph, SchemaParent parent) {
        JSONArray entries = paragraph.getJSONArray("entries");
        for (int i = 0; i < entries.length(); i++) {
            JSONObject entry = entries.getJSONObject(i);
            parseEntry(entry, parent);
        }
    }

    public void discoverJSON(String jsonContent) {
        JSONArray obj = new JSONArray(jsonContent);

        Queue<UserContentTabFoL> parseQueue = new LinkedList<>();


        for (int i = 0; i < obj.length(); i++) {
            JSONObject child = obj.getJSONObject(i);
            UserContentTabFoL parse = new UserContentTabFoL(child);
            parseQueue.add(parse);
        }

        while (!parseQueue.isEmpty()) {
            UserContentTabFoL next = parseQueue.poll();
            JSONObject nextObj = next.getObject();

            if (nextObj.has("children")) {
                JSONArray children = nextObj.getJSONArray("children");
                for (int j = 0; j < children.length(); j++) {
                    JSONObject childObj = children.getJSONObject(j);
                    UserContentTabFoL child = new UserContentTabFoL(childObj);
                    child.getSchemaParent().setParent(next.getSchemaParent());
                    parseQueue.offer(child);
                }
            }

            if (nextObj.has("paragraphs")) {
                JSONArray paragraphs = nextObj.getJSONArray("paragraphs");
                for (int j = 0; j < paragraphs.length(); j++) {
                    JSONObject paragraphObj = paragraphs.getJSONObject(j);
                    parseParagraph(paragraphObj, next.getSchemaParent());
                }
            }
        }
        System.out.println(entries.get(236));
        System.out.printf("Parsed %d entries", entries.size());
    }
}
