package com.minigamecollection.minigamecollection.statemanagers.games;

import java.io.Serializable;

/**
 * Created by M on 3/13/2017.
 */

public abstract class GameData implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void load();

}
