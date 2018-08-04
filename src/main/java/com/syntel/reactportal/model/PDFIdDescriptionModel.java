package com.syntel.reactportal.model;

public class PDFIdDescriptionModel {
    private Integer pdf_id;
    private String name;
    private String description;

    public PDFIdDescriptionModel() {}

    public PDFIdDescriptionModel(Integer pdf_id, String name, String description) {
        this.pdf_id = pdf_id;
        this.name = name;
        this.description = description;
    }

    public Integer getPdf_id() {
        return pdf_id;
    }

    public void setPdf_id(Integer pdf_id) {
        this.pdf_id = pdf_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
