/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.mongo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.skabold.wildlyne.beans.BaseBean;
import org.skabold.wildlyne.beans.Heros;
import org.skabold.wildlyne.beans.Item;
import org.skabold.wildlyne.beans.Joueur;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

/**
 * Classe de gestion du store Mongo administratif.<br>
 * Ne gère qu'une seule connexion ; un pool sera mis en place si nécessaire.
 */
public class AdminStore implements java.lang.AutoCloseable {

    /** logger. */
    public static Logger LOG = Logger.getLogger(AdminStore.class);

    /** Nom de la var d'env contenant le nom de la DB d'admin MongoDB. */
    public static final String VARENV_MONGODB_NAME = "COM_SLO_MONGODB_NAME";

    /** Nom de la var d'env contenant le host de la DB d'admin MongoDB. */
    public static final String VARENV_MONGODB_HOST = "COM_SLO_MONGODB_HOST";

    /** Nom de la var d'env contenant le port de la DB d'admin MongoDB. */
    public static final String VARENV_MONGODB_PORT = "COM_SLO_MONGODB_PORT";

    /** Nom de la var d'env contenant le user de la DB d'admin MongoDB. */
    public static final String VARENV_MONGODB_USER = "COM_SLO_MONGODB_USER";

    /**
     * Nom de la var d'env contenant le mot de passe crypté de la DB d'admin
     * MongoDB.
     */
    public static final String VARENV_MONGODB_PWD = "COM_SLO_MONGODB_PWD";

    /** instance du datastore partagée. */
    private Datastore dataStore = null;

    /** instance du morphia partagé. */
    private Morphia morphia = null;

    /** instance de la base mongo partagée. */
    private MongoClient mongo = null;

    /** false si la base est pas dispo. */
    private boolean dbIsUp = false;

    /**
     * Constructeur du wrapper Mongo.
     */
    public AdminStore() {
        // Récupération des paramètres depuis l'environnement
        final String storeName = System.getProperty(VARENV_MONGODB_NAME);
        final String host = System.getProperty(VARENV_MONGODB_HOST);
        final int port = Integer.parseInt(System.getProperty(VARENV_MONGODB_PORT));
        final String user = System.getProperty(VARENV_MONGODB_USER);
        final String pwd = System.getProperty(VARENV_MONGODB_PWD);

        // Connexion à Mongo
        try {
            final List<MongoCredential> credentialsList = new ArrayList<>();
            if (!StringUtils.isEmpty(user)) {
                final MongoCredential credentia = MongoCredential.createCredential(user, storeName, pwd.toCharArray());
                credentialsList.add(credentia);
            }
            mongo = new MongoClient(new ServerAddress(host, port), credentialsList);

            // Création du mapping
            morphia = new Morphia();
            mapBeans();
            // Création du datastore
            dataStore = morphia.createDatastore(mongo, storeName);
            // , user, pwd.toCharArray());
            dbIsUp = true;
        } catch (final MongoException excep) {
            // Log mais ne plante pas
            LOG.error("Passage mode dégradé sans Mongo", excep);
        }
    }

    /**
     * Fermeture. Cette instance d'adminstore n'est plus utilisable après.
     */
    @Override
    public void close() {
        if (mongo != null) {
            mongo.close();
        }
    }

    /**
     * Fermeture statique (pratique dans clause finally) permettant de fermer
     * l'objet sans râler s'il est null.
     * @param store sotre à fermer (éventuellement null)
     */
    public static void closeQuietly(final AdminStore store) {
        if (store != null) {
            store.close();
        }
    }

    /**
     * Retoune le datastore pour requêtes en lecture/écriture.
     * @return le datastore Morphia
     */
    public Datastore getDS() {
        return dataStore;
    }

    /**
     * Mappe toutes les classes "bean" du store d'administration.
     */
    private void mapBeans() {
        if (morphia != null) {
            morphia.map(Item.class);
            morphia.map(Heros.class);
            morphia.map(Joueur.class);
        }
    }

    /**
     * Checks if is false si la base est pas dispo.
     * @return the false si la base est pas dispo
     */
    public boolean isDbUp() {
        return dbIsUp;
    }

    /**
     * Sets the false si la base est pas dispo.
     * @param dbisUp the new false si la base est pas dispo
     */
    public void setDbUp(final boolean dbisUp) {
        dbIsUp = dbisUp;
    }

    /**
     * Enregistre un bean
     * @param <T> le type de bean mongo
     * @param bean objet à enregistrer. L'ID est éventuellement modifié en
     *            retour.
     */
    public <T extends BaseBean> void enregistre(final T bean) {
        if (isDbUp()) {
            LOG.debug("Enregistrement d'un objet de type " + bean.getClass().getName());
            // On sait que c'est ça
            final Key<T> key = getDS().save(bean);
            bean.setObjId((ObjectId) key.getId());
        }
    }

}
