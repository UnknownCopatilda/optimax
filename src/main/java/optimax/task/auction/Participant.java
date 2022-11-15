package optimax.task.auction;

public class Participant {
    private int cash;
    private int quantityWon = 0;
    private int roundsToWin;
    private int totalRounds;

    public Participant(int cash, int totalRounds) {
        this.cash = cash;
        this.totalRounds = totalRounds;
        this.roundsToWin = totalRounds / 2 + 1;
    }

    public int getCash() {
        return cash;
    }

    public int getQuantityWon() {
        return quantityWon;
    }

    public int getRoundsToWin() {
        return roundsToWin;
    }

    /**
     * Updates the state of the participant in case of victory.
     * @param bid bid that was made by participant.
     */
    public void win(int bid) {
        roundEnded();
        quantityWon += 2;
        roundsToWin--;
        pay(bid);
    }

    /**
     * Updates the state of the participant in case of draw.
     * @param bid bid that was made by participant.
     */
    public void draw(int bid) {
        roundEnded();
        quantityWon += 1;
        roundsToWin = totalRounds / 2 + 1;
        pay(bid);
    }

    /**
     * Updates the state of the participant in case of lose.
     * @param bid bid that was made by participant.
     */
    public void lose(int bid) {
        roundEnded();
        pay(bid);
    }

    private void pay(int bid) {
        this.cash -= bid;
    }

    private void roundEnded() {
        totalRounds--;
    }

}
