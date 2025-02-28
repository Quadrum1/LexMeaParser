package de.techfak.gpse.explo.parsing.domain;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.ArrayList;

@Getter
@Setter
public class UserContentTabFoL {
    private SchemaParent schemaParent = new SchemaParent();
    private JSONObject object;

    public UserContentTabFoL(JSONObject object) {
        this.object = object;
        schemaParent.setId(object.getInt("id"));
        schemaParent.setName(object.getString("name"));
    }
}
