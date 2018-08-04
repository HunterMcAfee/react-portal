package com.syntel.reactportal.model;

import java.sql.Blob;
import java.sql.Clob;

public class PDFModel {
    private String name;
    private Blob content;
    private Clob description;

    public PDFModel() {}

    public PDFModel(String name, Blob content, Clob description) {
        this.name = name;
        this.content = content;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }

    public Clob getDescription() {
        return description;
    }

    public void setDescription(Clob description) {
        this.description = description;
    }
}
