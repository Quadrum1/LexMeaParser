package de.techfak.gpse.explo.parsing.domain;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Getter
@Setter
public class Definition {

    private String property = "";
    private String definition = "";

    public Definition(String property, String definition) {
        this.property = property;
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "Definition{" +
                "property='" + property + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
