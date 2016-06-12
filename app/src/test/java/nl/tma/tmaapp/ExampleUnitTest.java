package nl.tma.tmaapp;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * dit zijn de unittests om de username te testen
 *
 *
 */

public class ExampleUnitTest {


    @Test
    public void testValidEmailId() throws Exception {
        LoginActivity loginActivity= new LoginActivity();
    /*Act*/
        boolean result = loginActivity.isUsernameValid("andy@testdomain.com");
    /*Assert*/
        assertFalse(result );
    }

    @Test
    public void testInvalidEmailId() throws Exception {
    /*Arrange*/
        LoginActivity loginActivity= new LoginActivity();
    /*Act*/
        boolean result = loginActivity.isUsernameValid("andy@testdomain");
    /*Assert*/
        assertTrue(result);
    }

}