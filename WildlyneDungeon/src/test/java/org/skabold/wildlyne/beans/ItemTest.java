/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.beans;

import org.junit.Test;

import junit.framework.Assert;

/**
 * The Class ItemTest.
 */
public class ItemTest {

    /**
     * Test.
     */
    @Test
    public void test() {
        final Item item = new Item();
        item.setNom("Yoyo");
        Assert.assertEquals("Yoyo", item.getNom());
    }

}
