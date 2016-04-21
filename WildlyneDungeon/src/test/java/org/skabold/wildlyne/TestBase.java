/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne;

import org.junit.Before;
import org.skabold.wildlyne.mongo.AdminStore;

/**
 * The Class TestBase.
 */
public class TestBase {
    @Before
    public void initEnv() {
        System.setProperty(AdminStore.VARENV_MONGODB_HOST, "localhost");
        System.setProperty(AdminStore.VARENV_MONGODB_PORT, "27017");
        System.setProperty(AdminStore.VARENV_MONGODB_NAME, "wildlyne");
        System.setProperty(AdminStore.VARENV_MONGODB_USER, "");
        System.setProperty(AdminStore.VARENV_MONGODB_PWD, "");
    }
}
