package assignment2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant();
    }

    @Test
    void setParticipantType() {
        participant.setParticipantType("individual");
        assertEquals("individual", participant.participantType);
    }

//    @Test(expected=Exception.class)
//    void setParticipantTypeInvalid() {
//        assertThrows(Exception.class, participant.setParticipantType("abc"))
//    }

    @Test
    void setParticipantID() {
        participant.setParticipantID(1);
        assertEquals(1, participant.participantID);
    }

    @Test
    void setSingleEvent() {
        participant.setSingleEvent(true);
        assertTrue(participant.singleEvent);
    }
}