package de.techfak.gpse.explo.parsing.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SchemaParent {
    private int id;
    private String name;

    private SchemaParent parent;

    public SchemaParent() {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SchemaParent{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", parent=").append(parent);
        sb.append('}');
        return sb.toString();
    }
}
