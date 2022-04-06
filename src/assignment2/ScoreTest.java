package assignment2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    Score score;

    @BeforeEach
    void setUp() {
        score = new Score();
    }

    @Test
    void setParticipantID() {
        score.setParticipantID(1);
        assertEquals(1, score.participantID);
    }

    @Test
    void add() {
        score.add(5);
        assertEquals(5, score.score);
    }
}