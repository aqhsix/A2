package assignment2;

// This stores the score of each participant.
public class Score {
    protected int participantID;
    protected int score = 0;

    public void setParticipantID(int participantID) {
        this.participantID = participantID;
    }

    public void add(int score) {
        this.score += score;
    }
}
