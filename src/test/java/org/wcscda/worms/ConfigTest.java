package org.wcscda.worms;

import junit.framework.TestCase;

public class ConfigTest extends TestCase {
    public void testGetRecordGame() {
        assertTrue(Config.getRecordGame() instanceof Boolean);
    }
}
