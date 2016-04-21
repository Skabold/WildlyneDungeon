/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.mongo;

import org.junit.Test;
import org.skabold.wildlyne.TestBase;
import org.skabold.wildlyne.beans.Item;

import junit.framework.Assert;

/**
 * The Class AdminStoreTest.
 */
public class AdminStoreTest extends TestBase {

    /**
     * Test.
     */
    @Test
    public void test() {
        try (final AdminStore store = new AdminStore()) {
            final Item item = new Item();
            item.setNom("Titi");

            store.enregistre(item);
            // Retrouve l'item
            final Item stored = store.getDS().get(Item.class, item.getObjId());
            store.getDS().delete(stored);
            // test
            Assert.assertEquals("Titi", stored.getNom());
        }
    }

}
