package net.daneau.libgdxjam.utils;

import com.badlogic.gdx.utils.IntMap;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public class StringNumber {

    private static final IntMap<String> numberMapping = new IntMap<>();

    private StringNumber() {
    }

    public static String get(int number) {
        if (!numberMapping.containsKey(number)) {
            numberMapping.put(number, String.valueOf(number));
        }
        return numberMapping.get(number);
    }
}
