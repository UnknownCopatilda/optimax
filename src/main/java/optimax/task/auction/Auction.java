package optimax.task.auction;

/**
 * Represents process of auction.
 * Stores number of rounds and parties.
 */
public class Auction {

    private final Participant me;
    private final Participant opponent;
    private int remainingRounds;

    public Auction(Participant me, Participant opponent, int remainingRounds) {
        this.me = me;
        this.opponent = opponent;
        this.remainingRounds = remainingRounds;
    }

    public int getRemainingRounds() {
        return remainingRounds;
    }

    public Participant getMe() {
        return me;
    }

    public Participant getOpponent() {
        return opponent;
    }

    /**
     * Decides who won round based on the bids passed.
     * @param myBid my bidder bid.
     * @param opponentBid opponent bidder bid.
     */
    public void judge(int myBid, int opponentBid) {
        if (myBid > me.getCash() || opponentBid > opponent.getCash()) {
            throw new IllegalStateException("Bid is higher than remaining cash!");
        }

        this.remainingRounds--;

        if (myBid > opponentBid) {
            me.win(myBid);
            opponent.lose(opponentBid);
            return;
        }

        if (myBid == opponentBid) {
            me.draw(myBid);
            opponent.draw(opponentBid);
            return;
        }

        me.lose(myBid);
        opponent.win(opponentBid);
    }
}
