/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.main;

import org.skabold.wildlyne.mongo.AdminStore;

/**
 * The Class Game : classe principale "singleton".
 */

public final class Game {

    /** l'unique instance de game. */
    private final static Game instance = new Game();

    /** instance de la base mongo. */
    private final AdminStore mongo;


    /**
     * constructeur privé initialisant le jeu.
     */
    private Game() {
        // TODO : paramétrer via un fichier de configuration
        System.setProperty(AdminStore.VARENV_MONGODB_HOST, "localhost");
        System.setProperty(AdminStore.VARENV_MONGODB_PORT, "27017");
        System.setProperty(AdminStore.VARENV_MONGODB_NAME, "wildlyne");
        System.setProperty(AdminStore.VARENV_MONGODB_USER, "");
        System.setProperty(AdminStore.VARENV_MONGODB_PWD, "");

        mongo = new AdminStore();
    }

    /**
     * A appeler à la fin du jeu.
     */
    public void exit() {
        mongo.close();
    }

    /**
     * Gets the instance de la base mongo.
     * @return the instance de la base mongo
     */
    public AdminStore getMongo() {
        return mongo;
    }

    /** méthode de test du jsp TODO : à supprimer */
    public String hello() {
        return "Hello !";
    }

    /**
     * Instance du singleton.
     * @return l'instance
     */
    public static Game getInstance() {
        return instance;
    }


}
