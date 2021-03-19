
package com.stackroute.model;

import java.util.List;

public class Meaning {

    public String partOfSpeech;
    public List<Definition> definitions = null;

    public Meaning() {
    }

    public Meaning(String partOfSpeech, List<Definition> definitions) {
        super();
        this.partOfSpeech = partOfSpeech;
        this.definitions = definitions;
    }

}
