/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.skabold.wildlyne.beans.Heros;
import org.skabold.wildlyne.beans.Joueur;

/**
 * The Class Accueil - gère l'affichage de la page d'accueil (index.jsp)
 */

public class Accueil {

    /** instance singleton. */
    private final static Accueil INSTANCE = new Accueil();

    /**
     * Instantiates a new accueil.
     */
    private Accueil() {
        super();
    }

    /**
     * Gets the instance singleton.
     * @return the instance singleton
     */
    public static Accueil getInstance() {
        return INSTANCE;
    }

    /**
     * Récupère la liste des héros pour affichage.
     * @param session session
     * @return liste des héros du joueur
     */
    public List<Heros> getHeros(final HttpSession session) {
        session.removeAttribute("message_nouveauHeros");
        final Joueur joueur = Game.getInstance().getSecurite().getJoueur(session);
        return Game.getInstance().getMongo().getDS().find(Heros.class).field("playerId").equal(joueur.getObjId())
                .asList();
    }

    /**
     * Créer un héros.
     * @param session the session
     * @param reponse the reponse
     * @param nom the nom
     * @throws IOException pb
     */
    public void creerHeros(final HttpSession session, final HttpServletResponse reponse, final String nom)
            throws IOException {
        // TODO : vérifier si on a pas un héros du même nom déjà
        if (StringUtils.isEmpty(nom)) {
            session.setAttribute("message_nouveauHeros", "Vous devez donner un nom à votre héros");
            reponse.sendRedirect(Game.BASE_URL + "/nouveauHeros.jsp");
        } else {
            session.removeAttribute("message_nouveauHeros");
            final Heros heros = new Heros(nom, Game.getInstance().getSecurite().getJoueur(session));
            Game.getInstance().getMongo().enregistre(heros);
            reponse.sendRedirect(Game.BASE_URL + "/index.jsp");
        }
    }

}
