package optimax.task.auction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParticipantTest {

    @Test
    public void testWin() {
        var participant = new Participant(50, 5);
        participant.win(20);
        assertEquals(30, participant.getCash());
        assertEquals(2, participant.getQuantityWon());
        assertEquals(2, participant.getRoundsToWin());
    }

    @Test
    public void testDraw() {
        var participant = new Participant(50, 3);
        participant.draw(20);
        assertEquals(30, participant.getCash());
        assertEquals(1, participant.getQuantityWon());
        assertEquals(2, participant.getRoundsToWin());
    }

    @Test
    public void testLose() {
        var participant = new Participant(50, 5);
        participant.lose(20);
        assertEquals(30, participant.getCash());
        assertEquals(0, participant.getQuantityWon());
        assertEquals(3, participant.getRoundsToWin());
    }
}