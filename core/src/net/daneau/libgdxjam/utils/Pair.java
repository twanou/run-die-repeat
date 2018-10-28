package net.daneau.libgdxjam.utils;

/**
 * Created by Twanou on 2015-02-13.
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }


}
