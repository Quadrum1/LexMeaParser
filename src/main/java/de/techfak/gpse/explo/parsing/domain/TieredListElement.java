package de.techfak.gpse.explo.parsing.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class TieredListElement {

    private TieredListElement parent = null;
    private ArrayList<TieredListElement> children = new ArrayList<>();

    private int hierarchyLevel = 0;

    private String text;

    public TieredListElement(String text) {
        this.text = text;
    }

    public void addChild(TieredListElement child) {
        child.setParent(this);
        child.setHierarchyLevel(hierarchyLevel + 1);
        children.add(child);
    }

    public void addSibling(TieredListElement sibling) {
        if (parent == null) {
            return;
        }
        parent.addChild(sibling);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("    ".repeat(this.hierarchyLevel)).append(this.text).append("\n");
        for (TieredListElement child : children) {
            sb.append(child.toString());
        }
        return sb.toString();
    }
}
