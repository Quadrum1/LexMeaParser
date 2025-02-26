package de.techfak.gpse.explo.parsing.domain;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class DefinitionFactory {

    public DefinitionFactory() {
    }

    public static ArrayList<Definition> parse(Element definitionBox) {
        Elements children = definitionBox.children();

        ArrayList<Definition> results = new ArrayList<Definition>();

        for (int i = 0; i < children.size(); i++) {
            Element child = children.get(i);
            String text = child.text();

            String[] parts = text.split("=", 2);

            if (parts.length < 2) {
                continue;
            }
            Definition definition = new Definition(parts[0], parts[1]);
            results.add(definition);
        }

        return results;
    }
}
