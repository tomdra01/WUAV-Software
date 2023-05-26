package dk.easv.bll.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordSecurity {

    /**
     * Hashes a password using bcrypt.
     *
     * @param password the password to hash
     * @return the hashed password
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Checks a plaintext password against a hashed password to see if they match.
     *
     * @param plaintext the plaintext password to check
     * @param hashed    the hashed password to check against
     * @return true if the passwords match, false otherwise
     */
    public static boolean checkPassword(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }
}