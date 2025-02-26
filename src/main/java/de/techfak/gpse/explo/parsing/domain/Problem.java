package de.techfak.gpse.explo.parsing.domain;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Getter
@Setter
public class Problem {

    private String question = "";
    private String solution = "";

    public Problem(Element problem) {
        Elements children = problem.children();

        int i = 0;
        for (; i < children.size(); i++) {
            Element child = children.get(i);
            String text = child.text();
            this.question += text + "\n";
            if (text.contains("?")) {
                break;
            }
        }

        for (i++; i < children.size(); i++) {
            Element child = children.get(i);
            String text = child.text();
            this.solution += text + "\n";
        }
    }

    @Override
    public String toString() {
        return "Problem{" +
                "question='" + question + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}
