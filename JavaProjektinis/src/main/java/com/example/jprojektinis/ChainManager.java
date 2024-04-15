package com.example.jprojektinis;

import java.util.ArrayList;
import java.util.List;

public class ChainManager {
    private List<ChainObject> chainObjects;

    public ChainManager() {
        chainObjects = new ArrayList<>();
    }

    public void connectObjects(ChainObject object1, ChainObject object2) {
        chainObjects.add(object1);
        chainObjects.add(object2);
        System.out.println("Objects connected!");
    }
}
