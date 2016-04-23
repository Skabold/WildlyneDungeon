/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.beans;

import org.junit.Test;

import junit.framework.Assert;

/**
 * The Class JoueurTest.
 */
public class JoueurTest {

    /**
     * Test de l'encryption
     */
    @Test
    public void test() {
        final Joueur joueur = new Joueur();
        joueur.setPassword("Toto");
        Assert.assertEquals("Toto", joueur.getPassword());
    }

}
