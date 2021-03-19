
package com.stackroute.model;

import java.util.List;

public class WordMeaning {

    public String word;
    public List<Phonetic> phonetics = null;
    public List<Meaning> meanings = null;

    public WordMeaning() {
    }

    public WordMeaning(String word, List<Phonetic> phonetics, List<Meaning> meanings) {
        super();
        this.word = word;
        this.phonetics = phonetics;
        this.meanings = meanings;
    }

}
