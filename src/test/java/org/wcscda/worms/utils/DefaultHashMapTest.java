package org.wcscda.worms.utils;

import java.util.ArrayList;
import junit.framework.TestCase;

public class DefaultHashMapTest extends TestCase {
    public void testGetDefault() {
        DefaultHashMap<Integer, ArrayList<String>> t = new DefaultHashMap<>(() -> new ArrayList<String>());
        t.get(1).add("Hello");
        t.get(1).add("world !");
        assertEquals(2, t.get(1).size());
        assertEquals("world !", t.get(1).get(1));
        assertTrue(t.get(2) != null);
        t.get(2).add("Knight of Java");
        assertEquals(2, t.get(1).size());
        assertEquals(1, t.get(2).size());
        assertEquals("Knight of Java", t.get(2).get(0));
    }
}
