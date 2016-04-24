/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.beans;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

/**
 * The Class Heros.
 */
@Entity
public class Heros extends BaseBean {

    /** SUID. */
    private static final long serialVersionUID = 1L;

    @Indexed
    /** Joueur d'appartenance. */
    private ObjectId playerId;

    /** nom du héros. */
    private String nom;

    /** niveau. */
    private int niveau;

    /** points d'expérience. */
    private int experience;

    /** Items possédés. */
    @Embedded
    private List<Item> items;

    /**
     * constructeur vide pour mongo.
     */
    public Heros() {
        super();
    }

    /**
     * constructeur à utiliser dans le code.
     * @param nom the nom
     * @param joueur the joueur
     */
    public Heros(final String nom, final Joueur joueur) {
        this.nom = nom;
        playerId = joueur.getObjId();
        items = new ArrayList<>();
    }

    /**
     * Gets the joueur d'appartenance.
     * @return the joueur d'appartenance
     */
    public ObjectId getPlayerId() {
        return playerId;
    }

    /**
     * Sets the joueur d'appartenance.
     * @param playerId the new joueur d'appartenance
     */
    public void setPlayerId(final ObjectId playerId) {
        this.playerId = playerId;
    }

    /**
     * Gets the nom du héros.
     * @return the nom du héros
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the nom du héros.
     * @param nom the new nom du héros
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Gets the niveau.
     * @return the niveau
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Sets the niveau.
     * @param niveau the new niveau
     */
    public void setNiveau(final int niveau) {
        this.niveau = niveau;
    }

    /**
     * Gets the points d'expérience.
     * @return the points d'expérience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets the points d'expérience.
     * @param experience the new points d'expérience
     */
    public void setExperience(final int experience) {
        this.experience = experience;
    }

    /**
     * Gets the items possédés.
     * @return the items possédés
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Sets the items possédés.
     * @param items the new items possédés
     */
    public void setItems(final List<Item> items) {
        this.items = items;
    }

}
