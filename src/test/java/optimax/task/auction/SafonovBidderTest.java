package optimax.task.auction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SafonovBidderTest {

    @Test
    void testInit() {
        SafonovBidder bidder = new SafonovBidder();
        bidder.init(10, 50);
        var me = bidder.getAuction().getMe();
        var opponent = bidder.getAuction().getOpponent();

        assertEquals(50, me.getCash());
        assertEquals(3, me.getRoundsToWin());
        assertEquals(0, me.getQuantityWon());

        assertEquals(50, opponent.getCash());
        assertEquals(3, opponent.getRoundsToWin());
        assertEquals(0, opponent.getQuantityWon());

        var judge = bidder.getAuction();
        assertEquals(5, judge.getRemainingRounds());
    }

    @Test
    void placeBidWhenIHasAlreadyWon() {
        SafonovBidder bidder = new SafonovBidder();
        bidder.init(10 , 50);
        bidder.bids(10, 1);
        bidder.bids(10, 1);
        bidder.bids(10, 1);

        assertEquals(0, bidder.placeBid());
    }

    @Test
    void placeBidWhenHeHasAlreadyWon() {
        SafonovBidder bidder = new SafonovBidder();
        bidder.init(10 , 50);
        bidder.bids(1, 10);
        bidder.bids(1, 10);
        bidder.bids(1, 10);

        assertEquals(0, bidder.placeBid());
    }

    @Test
    void placeBidWhenICantWin() {
        SafonovBidder bidder = new SafonovBidder();
        bidder.init(10 , 50);
        bidder.bids(20, 1);
        bidder.bids(20, 1);

        assertEquals(0, bidder.placeBid());
    }

    @Test
    void placeBidWhenILostTooMuchTooEarly() {
        SafonovBidder bidder = new SafonovBidder();
        bidder.init(10 , 50);
        bidder.bids(40, 1);

        assertEquals(0, bidder.placeBid());
    }

    @Test
    void trickyBid() {
        SafonovBidder bidder = new SafonovBidder();
        bidder.init(10 , 50);
        bidder.bids(10, 9);
        bidder.bids(10, 9);
        bidder.bids(9, 10);

        assertEquals(0, bidder.placeBid());
    }

    @Test
    void ordinalBid() {
        SafonovBidder bidder = new SafonovBidder();
        bidder.init(10 , 50);

        assertTrue(bidder.placeBid() >= 15);
        assertTrue(bidder.placeBid() <= 17);
    }
}