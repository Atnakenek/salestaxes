package com.lastminute.salestaxes.model.types;

public enum Modifier {

    PERCENTAGE("PERC"),
    FLAT("FLAT");
    
    private final String value;

    Modifier(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
