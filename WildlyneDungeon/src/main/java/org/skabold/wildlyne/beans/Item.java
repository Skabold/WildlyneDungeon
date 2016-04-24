/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.beans;

import org.mongodb.morphia.annotations.Entity;

/**
 * The Class Item.
 */
@Entity
public class Item extends BaseBean {

    /** SUID */
    private static final long serialVersionUID = 1L;
    /** nom de l'item. */
    private String nom;

    /**
     * Gets the nom de l'item.
     * @return the nom de l'item
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the nom de l'item.
     * @param nom the new nom de l'item
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }
}
