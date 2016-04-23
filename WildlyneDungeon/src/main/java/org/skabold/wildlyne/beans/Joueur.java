/*
 * Wildlyne Dungeon
 * (c) 2016 Skabold - All rights reserved
 */
package org.skabold.wildlyne.beans;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.mongodb.morphia.annotations.Entity;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * The Class Joueur. Utilisé pour la connexion et pour tout référencer
 */
@Entity
public class Joueur extends BaseBean {

    /** SUID */
    private static final long serialVersionUID = 1L;

    /** pseudo du joueur. */
    private String pseudo;

    /** email du joueur. */
    private String email;

    /** mot de passe stocké encrypté et encodé en base 64. */
    private String encryptedPassword;

    /**
     * Gets the pseudo du joueur.
     * @return the pseudo du joueur
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Sets the pseudo du joueur.
     * @param pseudo the new pseudo du joueur
     */
    public void setPseudo(final String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Gets the email du joueur.
     * @return the email du joueur
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email du joueur.
     * @param email the new email du joueur
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    // ----------------------------------------- Gestion du mot de passe avec stockage crypté --------------------------------------
    /** vecteur d'initilisation AES */
    private static final byte[] INIT_IV = "AAAAAAAAAAAAAAAA".getBytes();
    /** clé d'encryption du mdp - 32 chars. */
    private static final byte[] CLE_ENCRYPTION_PWD = "CeciEstUneSuperCleDencryptionDeM".getBytes();


    /**
     * Récupère le mot de passe (en clair).
     * @return the mot de passe en clair
     */
    public String getPassword() {
        try {
            return decrypt(Base64.decode(encryptedPassword));
        } catch (final Base64DecodingException excep) {
            throw new IllegalArgumentException(excep);
        }
    }

    /**
     * Sets the mot de passe en clair.
     * @param password the new password
     */
    public void setPassword(final String password) {
        // Encrypte le mdp
        encryptedPassword = Base64.encode(encrypt(password));
    }

    /**
     * Encrypt.
     * @param plainText the plain text
     * @return the byte[]
     */
    private static byte[] encrypt(final String plainText) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            final SecretKeySpec key = new SecretKeySpec(CLE_ENCRYPTION_PWD, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(INIT_IV));
            return cipher.doFinal(plainText.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException | UnsupportedEncodingException | InvalidKeyException
                | InvalidAlgorithmParameterException excep) {
            throw new IllegalStateException(excep);
        }
    }

    /**
     * Decrypt.
     * @param cipherText the cipher text
     * @return the string
     */
    private static String decrypt(final byte[] cipherText) {
        try {
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            final SecretKeySpec key = new SecretKeySpec(CLE_ENCRYPTION_PWD, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(INIT_IV));
            return new String(cipher.doFinal(cipherText), "UTF-8");
        } catch (final BadPaddingException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException
                | InvalidKeyException | InvalidAlgorithmParameterException | UnsupportedEncodingException
                | IllegalBlockSizeException excep) {
            throw new IllegalStateException(excep);
        }
    }
}
