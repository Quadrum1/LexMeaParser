package de.techfak.gpse.explo.parsing;

import de.techfak.gpse.explo.parsing.domain.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Parser p = new Parser();
        String file = "src/main/resources/schemasWithFoLTree.json";
        try (InputStream inputStream = new FileInputStream(file)) {
            // Identical text found on the website, the file is just a static representation
            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            p.discoverJSON(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
