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

        StringBuilder sb2 = new StringBuilder();

        TheString.reverse();
        String ReturnValue = TheString.toString();

        char[] characters = ReturnValue.toCharArray();
        char c = ' ';
        int behind;

        for(int i = 0; i < ReturnValue.length(); i++) {
            c = characters[i];
            if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                sb2.setLength(0);
                behind = 1;

                while((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    sb2.append(c);
                    if(i + behind < characters.length) {
                        c = characters[i + behind];
                        behind += 1;
                    }
                    else {
                        break;
                    }
                }


                String to = sb2.toString();
                sb2.reverse();
                String from = sb2.toString();


                ReturnValue = ReturnValue.replace(to, from);

                i += behind - 1;
            }
        }

        return ReturnValue;
    }
}
