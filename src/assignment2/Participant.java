package assignment2;

import java.util.List;
import java.util.ArrayList;

public class Participant {
    protected String participantType; // team or individual
    protected List<Member> members = new ArrayList<>();
    protected int participantID;
    protected boolean singleEvent = false;

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void setParticipantID(int participantID) {
        this.participantID = participantID;
    }

    public void setSingleEvent(boolean singleEvent) {
        this.singleEvent = singleEvent;
    }
}
