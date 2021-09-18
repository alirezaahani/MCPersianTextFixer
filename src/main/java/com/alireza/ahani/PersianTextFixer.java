package com.alireza.ahani;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;

public class PersianTextFixer {

    public String convert(String input) throws ArabicShapingException {
        input = input.replace("ی", "ي")
                .replace("ک", "ك")
                .replace("چ", "ج")
                .replace("گ", "ك")
                .replace("پ", "ب");

        Bidi bidi = new Bidi((new ArabicShaping(ArabicShaping.LETTERS_SHAPE)).shape(input), Bidi.DIRECTION_DEFAULT_RIGHT_TO_LEFT);
        bidi.setReorderingMode(Bidi.REORDER_DEFAULT);
        input = bidi.writeReordered(Bidi.DO_MIRRORING);

        return input;
    }
}
