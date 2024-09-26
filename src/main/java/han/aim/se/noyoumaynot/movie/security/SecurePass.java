package han.aim.se.noyoumaynot.movie.security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import static han.aim.se.noyoumaynot.movie.security.Hex.fromHex;
import static han.aim.se.noyoumaynot.movie.security.Hex.toHex;

@Service
public class SecurePass {
    @Value("${iterations}")
    private int iterations;

    public static void main(String[] args)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecurePass securePass = new SecurePass();
        String generatedSecuredPasswordHashCombination
                = securePass.generateStrongPasswordHash(args[0]);
        String generatedSecuredPasswordSalt = generatedSecuredPasswordHashCombination.split(":")[1];
        String generatedSecuredPasswordHash = generatedSecuredPasswordHashCombination.split(":")[2];
        System.out.println("Secure password hash: " + generatedSecuredPasswordHash);
        System.out.println("Secure password salt: " + generatedSecuredPasswordSalt);

        boolean matched = securePass.validatePassword(args[0], generatedSecuredPasswordHash, generatedSecuredPasswordSalt);
        System.out.println(matched);

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("supersecret");

        String encryptedData = encryptor.encrypt(String.valueOf(1000));
        System.out.println(encryptedData);
    }

    public String generateStrongPasswordHash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    public boolean validatePassword(String originalPassword, String storedPassword, String saltedString)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        byte[] salt = fromHex(saltedString);
        byte[] hash = fromHex(storedPassword);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(),
                salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public int getIterations() {
        return this.iterations;
    }
}
