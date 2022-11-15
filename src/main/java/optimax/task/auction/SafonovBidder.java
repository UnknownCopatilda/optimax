package optimax.task.auction;

import optimax.task.auction.utils.Range;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Safonov Vladimir bidder.
 * Read readme.md for more info.
 */
public class SafonovBidder implements Bidder {

    private Auction auction;

    public Auction getAuction() {
        return auction;
    }

    @Override
    public void init(int quantity, int cash) {
        int totalRounds = quantity / 2;
        var me = new Participant(cash, totalRounds);
        var opponent = new Participant(cash, totalRounds);
        this.auction = new Auction(me, opponent, totalRounds);
    }

    @Override
    public int placeBid() {
        var me = auction.getMe();
        var opponent = auction.getOpponent();

        // No need to waste money if the game is over.
        if (noChanceToWin()) {
            return 0;
        }

        // If the opponent needs to win all the remaining rounds, then in order to stay in the game,
        // he may want place bid "all my money + 1".
        // I will place bid 0 at this point and the next round I will probably win.
        if (opponent.getRoundsToWin() == auction.getRemainingRounds()) {
            return 0;
        }

        // If it is last round, and we are in the same conditions, bid everything, so nobody wins.
        if (lastRound() && sameConditions(me, opponent)) {
            return me.getCash();
        }

        int myDefinitelyWonRounds = definitelyWonRounds(me, opponent);
        if (myDefinitelyWonRounds >= me.getRoundsToWin()) {
            return opponent.getCash() + 1;
        }

        int opponentsDefinitelyWonRounds = definitelyWonRounds(opponent, me);
        if (opponentsDefinitelyWonRounds >= auction.getRemainingRounds()
                || opponentsDefinitelyWonRounds >= opponent.getRoundsToWin()) {
            return 0;
        }

        return calcBid();
    }

    private boolean lastRound() {
        return auction.getRemainingRounds() == 1;
    }

    private boolean sameConditions(Participant me, Participant opponent) {
        return me.getRoundsToWin() == 1 && opponent.getRoundsToWin() == 1
                && me.getCash() == opponent.getCash();
    }

    private boolean noChanceToWin() {
        return auction.getMe().getRoundsToWin() == 0 || auction.getOpponent().getRoundsToWin() == 0;
    }

    private int calcBid() {
        var me = auction.getMe();
        int myValue = calcMyValue(me);
        Range range = calcRange(myValue);
        return ThreadLocalRandom.current().nextInt(Math.min(me.getCash(), range.upperLimit()) - range.lowerLimit()) + range.lowerLimit();
    }

    private int calcMyValue(Participant me) {
        return me.getCash() / me.getRoundsToWin();
    }

    private Range calcRange(int myValue) {
        return Range.calcRange(myValue);
    }

    private int definitelyWonRounds(Participant first, Participant second) {
        int definitelyWin = 0;
        int initialBudget = first.getCash();
        while (initialBudget - second.getCash() - 1 >= 0) {
            definitelyWin++;
            initialBudget = initialBudget - second.getCash() - 1;
        }
        return definitelyWin;
    }

    @Override
    public void bids(int own, int other) {
        auction.judge(own, other);
    }

}
