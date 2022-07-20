package org.wcscda.worms.utils;

import java.util.HashMap;
import java.util.function.Supplier;

public class DefaultHashMap<K, V> extends HashMap<K, V> {
    protected final Supplier<V> defaultValueSupplier;

    public DefaultHashMap(Supplier<V> defaultValue) {
        this.defaultValueSupplier = defaultValue;
    }

    @Override
    public V get(Object k) {
        V value = super.get(k);
        if (value != null) {
            return value;
        } else {
            V newValue = defaultValueSupplier.get();
            put((K) k, newValue);
            return newValue;
        }
    }
}
