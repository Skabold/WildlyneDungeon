/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.main;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.skabold.wildlyne.beans.Joueur;

/**
 * The Class Securite. Singleton référencé par Game ; permet de gérer la
 * sécurité
 */
public class Securite {


    /**
     * Vérifie la sécurité : un joueur est-il authentifié dans la session ? Si
     * oui, tout va bien. Si on, il faut rediriger vers la page de connexion
     * @param session the session
     * @param reponse the reponse
     * @throws IOException probleme de redirection
     * @return true si ok
     */
    public boolean checkSecurite(final HttpSession session, final HttpServletResponse reponse) throws IOException {
        if (session.isNew() || session.getAttribute("joueur") == null) {
            // Doit renvoyer vers la page de login
            session.setAttribute("message", ""); // pas de message d'erreur
            session.setAttribute("message_nouveauJoueur", ""); // pas de message d'erreur
            reponse.sendRedirect(Game.BASE_URL + "/login.jsp");
            return false;
        }
        return true;
    }

    /**
     * Vérifie le login dans la base et connecte le joueur.
     * @param session the session
     * @param reponse the reponse
     * @param pseudo the pseudo
     * @param password the password
     * @throws IOException pb
     */
    public void checkLogin(final HttpSession session, final HttpServletResponse reponse, final String pseudo,
            final String password) throws IOException {

        // Va rechercher le joueur de pseudo indiqué dans la base Mongo
        final Joueur found = Game.getInstance().getMongo().getDS().createQuery(Joueur.class).field("pseudo")
                .equal(pseudo).get();

        if (found != null && found.getPassword().equals(password)) {
            // OK ! Stocke le joueur en session
            session.setAttribute("joueur", found);
            // page d'accueil
            reponse.sendRedirect(Game.BASE_URL + "/index.jsp");
        } else {
            // KO... Recommence, avec un message
            session.setAttribute("message", "Joueur inconnu ou mauvais mot de passe");
            reponse.sendRedirect(Game.BASE_URL + "/login.jsp");
        }
    }

    /**
     * Creates the user.
     * @param session the session
     * @param reponse the reponse
     * @param email the email
     * @param pseudo the pseudo
     * @param password1 the password1
     * @param password2 the password2
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void createUser(final HttpSession session, final HttpServletResponse reponse, final String email,
            final String pseudo, final String password1, final String password2) throws IOException {
        String erreur = "";
        // Les mots de passe doivent être d'au moins 8 caractères
        if (StringUtils.isEmpty(password1) || StringUtils.isEmpty(password2) || StringUtils.isEmpty(email)
                || StringUtils.isEmpty(pseudo)) {
            erreur = "Tous les champs sont obligatoires";
        } else if (!password1.equals(password2)) {
            erreur = "Les mots de passe ne correspondent pas";
        } else if ((pseudo.length() < 6) || (password1.length() < 6)) {
            erreur = "Le pseudo et le mot de passe doivent avoir au moins 6 caractères";
        } else {
            // Vérifie si le pseudo existe
            final Joueur existe = Game.getInstance().getMongo().getDS().createQuery(Joueur.class).field("pseudo")
                    .equal(pseudo).get();
            if (existe != null) {
                erreur = "Le pseudonyme " + pseudo + " existe déjà";
            } else {
                // Tout OK !
                final Joueur joueur = new Joueur();
                joueur.setEmail(email);
                joueur.setPseudo(pseudo);
                joueur.setPassword(password1);
                Game.getInstance().getMongo().enregistre(joueur);
                session.setAttribute("joueur", joueur);
                session.setAttribute("message_nouveauJoueur", "");
                reponse.sendRedirect(Game.BASE_URL + "/index.jsp");
                return;
            }
        }
        // Pas bon
        session.setAttribute("message_nouveauJoueur", erreur);
        reponse.sendRedirect(Game.BASE_URL + "/nouveauJoueur.jsp");
    }

    /**
     * retourne le joueur actuel.
     * @param session the session
     * @return the joueur
     */
    public Joueur getJoueur(final HttpSession session) {
        return (Joueur) session.getAttribute("joueur");
    }


}
