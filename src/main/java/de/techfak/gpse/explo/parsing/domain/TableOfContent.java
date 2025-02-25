package de.techfak.gpse.explo.parsing.domain;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TableOfContent {

    TieredListElement self = new TieredListElement("Root");

    public TableOfContent(Element toc) {
        // "<li style="margin: 0rem 0rem 0rem 2.7rem;" data-style="heading4">Kind Text</li>"
        int currentIndent = 0;
        TieredListElement current = self;

        Elements children = toc.children();
        for (int i = 0; i < children.size(); i++) {
            Element child = children.get(i);
            // "heading2"
            String style = child.attr("data-style");
            style = style.replaceAll("[^0-9]", "");

            int indent = Integer.parseInt(style);

            TieredListElement childElement = new TieredListElement(child.text());
            if (indent > currentIndent) {
                for (int j = currentIndent + 1; j < indent; j++) {
                    TieredListElement buffer = new TieredListElement("");
                    current.addChild(buffer);
                    current = buffer;
                }
                current.addChild(childElement);
            } else if (indent == currentIndent) {
                current.addSibling(childElement);
            } else if (indent < currentIndent) {
                for (int j = indent; j < currentIndent; j++) {
                    current = current.getParent();
                }

                current.addSibling(childElement);
            }

            current = childElement;
            currentIndent = indent;
        }
    }

    @Override
    public String toString() {
        return self.toString();
    }
}
