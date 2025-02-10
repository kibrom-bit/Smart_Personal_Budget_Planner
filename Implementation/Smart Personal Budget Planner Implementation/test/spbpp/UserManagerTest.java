package spbpp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class UserManagerTest {
    private UserManager userManager;
    private static final String TEST_USERS_FILE = "test_users.txt"; // Use a test file

    @Before
    public void setUp() {
        // Create a new UserManager instance
        userManager = new UserManager();
    }

    @After
    public void tearDown() {
        // Clean up the test file after each test
        File file = new File(TEST_USERS_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Test of authenticate method, of class UserManager.
     */
    @Test
    public void testAuthenticate_Success() {
        System.out.println("authenticate - Success");
        String username = "testUser  ";
        String password = "testPass";
        
        // Create the account in the test file
        userManager.createAccount(username, password); // Create the account

        // Now authenticate
        boolean result = userManager.authenticate(username, password);
        assertTrue(result);
    }

    @Test
    public void testAuthenticate_Failure() {
        System.out.println("authenticate - Failure");
        String username = "wrongUser  ";
        String password = "wrongPass";

        // Attempt to authenticate with wrong credentials
        boolean result = userManager.authenticate(username, password);
        assertFalse(result);
    }

    /**
     * Test of createAccount method, of class UserManager.
     */
    @Test
    public void testCreateAccount() {
        System.out.println("createAccount");
        String username = "newUser  ";
        String password = "newPass";
        
        // Create the account
        userManager.createAccount(username, password);

        // Verify that the account was created successfully
        assertTrue(userManager.authenticate(username, password));
    }
}