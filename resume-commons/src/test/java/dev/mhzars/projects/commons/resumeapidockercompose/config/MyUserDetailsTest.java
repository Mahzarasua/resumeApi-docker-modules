package dev.mhzars.projects.commons.resumeapidockercompose.config;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyUserDetailsTest {
    private static MyUserDetails myUserDetails;

    @BeforeEach
    void setUp() {
        myUserDetails = manufacturedPojo(MyUserDetails.class);
    }

    @Test
    void test() {
        assertNotNull(myUserDetails);
    }

    @Test
    void test1() {
        CommonAuthUser user = manufacturedPojo(CommonAuthUser.class);
        myUserDetails = new MyUserDetails(user);
        assertNotNull(myUserDetails);
        assertEquals(myUserDetails.getUsername(), user.getUsername());
        assertTrue(
                new BCryptPasswordEncoder()
                        .matches(user.getPassword(), myUserDetails.getPassword()));
        assertTrue(myUserDetails.isAccountNonExpired());
        assertTrue(myUserDetails.isAccountNonLocked());
        assertTrue(myUserDetails.isCredentialsNonExpired());
        assertEquals(myUserDetails.isEnabled(), user.isActive());
    }
}
