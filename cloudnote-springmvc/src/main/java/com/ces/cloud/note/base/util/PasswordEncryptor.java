package com.ces.cloud.note.base.util;

/**
 * Strategy used for encrypting and matching encrypted passwords. The purpose is
 * to make the password encryption possible to extend.
 * 
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Rev$, $Date$
 */

public interface PasswordEncryptor {
	/**
	 * Encrypts the password
	 * 
	 * @param password
	 *            The clear text password
	 * @return The encrypted password
	 */
	String encrypt(String password);

	/**
	 * Matches an encrypted password with that stored
	 * 
	 * @param passwordToCheck
	 *            The encrypted password to check
	 * @param storedPassword
	 *            The stored password
	 * @return true if the password match
	 */
	boolean matches(String passwordToCheck, String storedPassword);
}
