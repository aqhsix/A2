package assignment2;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    Member member;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        member = new Member();
    }

    @org.junit.jupiter.api.Test
    void setFirstName() {
        member.setFirstName("First");
        assertEquals("First", member.firstName);
    }

    @org.junit.jupiter.api.Test
    void setLastName() {
        member.setLastName("Last");
        assertEquals("Last", member.lastName);
    }
}