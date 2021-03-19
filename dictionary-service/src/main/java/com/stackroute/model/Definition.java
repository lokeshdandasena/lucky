
package com.stackroute.model;

import java.util.List;

public class Definition {

    public String definition;
    public List<String> synonyms = null;
    public String example;

    public Definition() {
    }

    public Definition(String definition, List<String> synonyms, String example) {
        super();
        this.definition = definition;
        this.synonyms = synonyms;
        this.example = example;
    }

}
