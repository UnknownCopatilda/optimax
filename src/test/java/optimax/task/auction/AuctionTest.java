package optimax.task.auction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {

    @Test
    void testMyWin() {
        int cash = 50;
        int totalRounds = 4;
        var me = new Participant(cash, totalRounds);
        var opponent = new Participant(cash, totalRounds);
        var judge = new Auction(me, opponent, totalRounds);

        judge.judge(40, 30);
        assertEquals(10, me.getCash());
        assertEquals(2, me.getRoundsToWin());
        assertEquals(2, me.getQuantityWon());

        assertEquals(20, opponent.getCash());
        assertEquals(3, opponent.getRoundsToWin());
        assertEquals(0, opponent.getQuantityWon());
    }

    @Test
    void testDraw() {
        int cash = 50;
        int totalRounds = 4;
        var me = new Participant(cash, totalRounds);
        var opponent = new Participant(cash, totalRounds);
        var judge = new Auction(me, opponent, totalRounds);

        judge.judge(30, 30);
        assertEquals(20, me.getCash());
        assertEquals(2, me.getRoundsToWin());
        assertEquals(1, me.getQuantityWon());

        assertEquals(20, opponent.getCash());
        assertEquals(2, opponent.getRoundsToWin());
        assertEquals(1, opponent.getQuantityWon());
    }

    @Test
    void testMyLose() {
        int cash = 50;
        int totalRounds = 4;
        var me = new Participant(cash, totalRounds);
        var opponent = new Participant(cash, totalRounds);
        var judge = new Auction(me, opponent, totalRounds);

        judge.judge(30, 40);
        assertEquals(20, me.getCash());
        assertEquals(3, me.getRoundsToWin());
        assertEquals(0, me.getQuantityWon());

        assertEquals(10, opponent.getCash());
        assertEquals(2, opponent.getRoundsToWin());
        assertEquals(2, opponent.getQuantityWon());
    }

    @Test
    void testExceptionalCase() {
        int cash = 50;
        int totalRounds = 4;
        var me = new Participant(cash, totalRounds);
        var opponent = new Participant(cash, totalRounds);
        var judge = new Auction(me, opponent, totalRounds);

        assertThrows(IllegalStateException.class, () -> judge.judge(60,40));
        assertThrows(IllegalStateException.class, () -> judge.judge(60,60));
        assertThrows(IllegalStateException.class, () -> judge.judge(40,60));
    }
}