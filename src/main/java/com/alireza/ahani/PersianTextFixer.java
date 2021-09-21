package com.alireza.ahani;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;

public class PersianTextFixer {

    public static String fixChars(String text) {
        text = text.replace("ی", "ي")
                .replace("ک", "ك")
                .replace("چ", "ج")
                .replace("گ", "ك")
                .replace("پ", "ب")
                .replace("\u200C", " ");

        return text;
    }

    public static String fixReordering(String text) throws  ArabicShapingException {
        Bidi bidi = new Bidi((new ArabicShaping(ArabicShaping.LETTERS_SHAPE)).shape(text), Bidi.DIRECTION_DEFAULT_RIGHT_TO_LEFT);
        bidi.setReorderingMode(Bidi.REORDER_DEFAULT);
        text = bidi.writeReordered(Bidi.DO_MIRRORING);

        return text;
    }

    public static String fixReverse(String text) {
        /* Credit to HardCoded from SciCraft's discord */

        StringBuilder result = new StringBuilder();
        int len = text.length();

        int bufferLen = 0;
        char[] buffer = new char[len];
        for(int i = len - 1; i >= 0; i--) {
            char c = text.charAt(i);

            boolean isEnglish = ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || (c == '(') || (c == ')'));
            if(isEnglish) {
                buffer[len - (++bufferLen)] = c;
            } else {
                if(bufferLen > 0) {
                    result.append(buffer, len - bufferLen, bufferLen);
                    bufferLen = 0;
                }

                result.append(c);
            }
        }

        if(bufferLen > 0) {
            result.append(buffer, len - bufferLen, bufferLen);
        }

        return result.toString();
    }

    public static String fixAll(String text) throws ArabicShapingException {
        return fixReordering(fixChars(text));
    }
}
