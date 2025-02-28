package de.techfak.gpse.explo.parsing.domain;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class Entry {

    private int id;
    private String title;
    private boolean curated;
    private String field_of_law_id;
    private int legal_text_paragraph_id;
    private String canonical_path;
    private String slug;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private String published_at;

    private TableOfContent table_of_content;
    private ArrayList<Problem> problems = new ArrayList<>();
    private ArrayList<Definition> definitions = new ArrayList<>();

    private String text;

    public Entry(JSONObject self) {
        id = (int) saveValue(self, "id");
        title = (String) saveValue(self, "title");
        curated = (boolean) saveValue(self, "curated");
        field_of_law_id = (String) saveValue(self, "field_of_law_id");
        canonical_path = (String) saveValue(self, "canonical_path");
        slug = (String) saveValue(self, "slug");
        legal_text_paragraph_id = (int) saveValue(self, "legal_text_paragraph_id");

        created_at = (String) saveValue(self, "created_at");
        updated_at = (String) saveValue(self, "updated_at");
        deleted_at = (String) saveValue(self, "deleted_at");
        published_at = (String) saveValue(self, "published_at");

        text = self.getString("text");
    }

    public Object saveValue(JSONObject parent, String fieldName) {
        if (parent.isNull(fieldName)) {
            return null;
        }

        return parent.get(fieldName);
    }

    public void addProblem(Problem problem) {
        this.problems.add(problem);
    }

    public void addDefinitions(ArrayList<Definition> definitions) {
        this.definitions.addAll(definitions);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entry{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", curated=").append(curated);
        sb.append(", field_of_law_id='").append(field_of_law_id).append('\'');
        sb.append(", canonical_path='").append(canonical_path).append('\'');
        sb.append(", slug='").append(slug).append('\'');
        sb.append(", created_at='").append(created_at).append('\'');
        sb.append(", updated_at='").append(updated_at).append('\'');
        sb.append(", deleted_at='").append(deleted_at).append('\'');
        sb.append(", published_at='").append(published_at).append('\'');
        sb.append(", table_of_content=").append(table_of_content);
        sb.append("\nproblems=").append(problems);
        sb.append("\ndefinitions=").append(definitions);
        //sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
