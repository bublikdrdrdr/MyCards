package com.bublik.mycards;

import android.graphics.Color;

/**
 * Created by Bublik on 05-Nov-16.
 */
public class Card {
    public String name;
    public int color;

    public Card(String name)
    {
        new Card(name, Color.TRANSPARENT);
    }

    public Card(String name, int color)
    {
        this.name = name;
        this.color = color;
    }
}
