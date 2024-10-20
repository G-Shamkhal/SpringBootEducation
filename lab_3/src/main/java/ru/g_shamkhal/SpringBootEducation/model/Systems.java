package ru.g_shamkhal.SpringBootEducation.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Systems {

    ERP("EnterPrise Resource Planning"),
    CRM ("Customer Relationship Management"),
    WMS ("Warehouse Management System");

    public final String name;

    Systems (String name){
        this.name= name;
    }
    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
