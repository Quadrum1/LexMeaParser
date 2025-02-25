package de.techfak.gpse.explo.parsing.domain;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class Row {

    private String id;
    private String user_id;
    private String title;
    private String curated;
    private String field_of_law_id;
    private String slug;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;
    private Date published_at;

    private TableOfContent table_of_content;

    private Element text;

    public Row(Element self) {
        id = self.getElementsByTag("id").getFirst().text();
        user_id = self.getElementsByTag("user_id").getFirst().text();
        title = self.getElementsByTag("title").getFirst().text();
        curated = self.getElementsByTag("curated").getFirst().text();
        field_of_law_id = self.getElementsByTag("field_of_law_id").getFirst().text();
        slug = self.getElementsByTag("slug").getFirst().text();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            created_at = format.parse(self.getElementsByTag("created_at").getFirst().text());
        } catch (ParseException e) {
            created_at = Date.from(Instant.EPOCH);
        }
        try {
            updated_at = format.parse(self.getElementsByTag("updated_at").getFirst().text());
        } catch (ParseException e) {
            updated_at = Date.from(Instant.EPOCH);
        }
        try {
            deleted_at = format.parse(self.getElementsByTag("deleted_at").getFirst().text());
        } catch (ParseException e) {
            deleted_at = Date.from(Instant.EPOCH);
        }
        try {
            published_at = format.parse(self.getElementsByTag("published_at").getFirst().text());
        } catch (ParseException e) {
            published_at = Date.from(Instant.EPOCH);
        }

        text = self.getElementsByTag("text").getFirst();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Row{");
        sb.append("id='").append(id).append('\'');
        sb.append(", user_id='").append(user_id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", currated='").append(curated).append('\'');
        sb.append(", field_of_law_id='").append(field_of_law_id).append('\'');
        sb.append(", slug='").append(slug).append('\'');
        sb.append(", text=").append(text);
        sb.append(", created_at=").append(created_at);
        sb.append(", updated_at=").append(updated_at);
        sb.append(", deleted_at=").append(deleted_at);
        sb.append(", published_at=").append(published_at);
        sb.append('}');
        return sb.toString();
    }
}
