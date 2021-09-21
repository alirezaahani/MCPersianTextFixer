package com.alireza.ahani;

import net.minecraft.text.CharacterVisitor;
import net.minecraft.text.Style;

// A class to get the damn string from CharacterVisitor
public class GiveMeTheString implements CharacterVisitor {

    public StringBuilder TheString = new StringBuilder();

    @Override
    public boolean accept(int index, Style style, int codePoint) {
        TheString.appendCodePoint(codePoint);
        return true;
    }

    @Override
    public String toString() {
        return TheString.toString();
    }
}
