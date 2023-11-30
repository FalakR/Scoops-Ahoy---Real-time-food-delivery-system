package entities;

import static org.junit.Assert.assertEquals;

import entities.CommonUser;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;


public class CommonUserTest {
    private CommonUser user;

    @Before
    public void setUp() throws Exception {
        this.user = new CommonUser(
                "name",
                "email",
                "pass",
                LocalDateTime.of(2022, 1, 29, 10, 30)
        );
    }

    @Test
    public void testName() {
        assertEquals("name", this.user.getName());
    }

    @Test
    public void testEmail() {
        assertEquals("email", this.user.getEmail());
    }
}
