package domain.Employees;

import Repoistory.Employee.Exceptions.EmployeeError;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 * CREATED BY mathi @ 23-11-2020 - 16:14
 **/
public class Employee {
    private static final int PASSWORD_ITTERATIONS = 65536;
    private static final int PASSWORD_LENGTH = 256; //32 bytes
    private static final SecretKeyFactory PASSWORD_FACTORY;

    static {
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PASSWORD_FACTORY = factory;
    }

    private final Role role;

    public enum Role {
        ADMIN,
        SALESMAN,
    }

    private int id;
    private String email;
    private byte[] salt;
    private byte[] secret;

    public Employee(Role role, int id, String email, byte[] salt, byte[] secret) {
        this.role = role;
        this.id = id;
        this.email = email;
        this.salt = salt;
        this.secret = secret;
    }

    public static byte[] genereateSalt() {
        SecureRandom random = new SecureRandom(); //tbh not that complicated just generate some random 16bit bytes
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[]
    calculateSecret(byte[] salt, String password) throws EmployeeError {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt,
                PASSWORD_ITTERATIONS, PASSWORD_LENGTH);
        try {
            return PASSWORD_FACTORY.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new EmployeeError("ERROR: " + e);
        }
    }


    public boolean isPasswordCorrect(String password) throws EmployeeError {
        return Arrays.equals(this.secret, calculateSecret(salt, password));
    }

    public Role getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSecret() {
        return secret;
    }

    public void setSecret(byte[] secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "role=" + role +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", salt=" + Arrays.toString(salt) +
                ", secret=" + Arrays.toString(secret) +
                '}';
    }
}
