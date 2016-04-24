/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.main;

import javax.servlet.http.HttpSession;

import org.skabold.wildlyne.mongo.AdminStore;

/**
 * The Class Game : classe principale "singleton".
 */

public final class Game {

    /** constante comportant l'url de base */
    public final static String BASE_URL= "/WildlyneDungeon";

    /** l'unique instance de game. */
    private final static Game INSTANCE = new Game();

    /** instance de la base mongo. */
    private final AdminStore mongo;

    /** instance du gestionnaire de sécurité (et du joueur). */
    private final Securite securite;

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
        securite = new Securite();
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

    /**
     * méthode de test du jsp TODO : à supprimer.
     * @return the string
     */
    public String hello(final HttpSession session) {
        return "Hello " + securite.getJoueur(session).getPseudo();
    }

    /**
     * Instance du singleton.
     * @return l'instance
     */
    public static Game getInstance() {
        return INSTANCE;
    }

    /**
     * Gets the instance du gestionnaire de sécurité (et du joueur).
     * @return the instance du gestionnaire de sécurité (et du joueur)
     */
    public Securite getSecurite() {
        return securite;
    }

    /**
     * Retrouve un message donné par son nom, ou retourne une chaine vide s'il n'y en a pas
     * @param session session
     * @param nomMessage message
     * @return le texte du message
     */
    public String getMessage(final HttpSession session, final String nomMessage) {
        String retVal = (String) session.getAttribute(nomMessage);
        if (retVal == null) {
            retVal = "";
        }
        return retVal;
    }

}
