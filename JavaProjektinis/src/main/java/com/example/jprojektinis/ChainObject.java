package com.example.jprojektinis;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class ChainObject {
    private String name;
    private Label label;

    public ChainObject(String name) {
        this.name = name;
        this.label = new Label(name);
    }

    public String getName() {
        return name;
    }

    public Node getLabel() {
        return label;
    }
}
