/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.beans;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Classe de base pour un bean mongo (doit contenir un object id).
 */
@Entity
public class BaseBean implements Cloneable, Serializable {

    /** SUID */
    private static final long serialVersionUID = 1L;

    /** id - auto-generated, if not set (see ObjectId). */
    @Id
    private ObjectId objId;

    /**
     * Gets the id - auto-generated, if not set (see ObjectId).
     * @return the id - auto-generated, if not set (see ObjectId)
     */
    public ObjectId getObjId() {
        return objId;
    }

    /**
     * Sets the id - auto-generated, if not set (see ObjectId).
     * @param objId the new id - auto-generated, if not set (see ObjectId)
     */
    public void setObjId(final ObjectId objId) {
        this.objId = objId;
    }
}
