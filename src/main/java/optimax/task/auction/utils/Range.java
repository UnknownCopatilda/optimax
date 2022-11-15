package optimax.task.auction.utils;


public record Range(int lowerLimit, int upperLimit) {

    /**
     * Calculates Range of values with a deviation of 10 percent from the estimate in both directions.
     * @param myValue my value of current auction round.
     * @return range of values.
     */
    public static Range calcRange(int myValue) {
        var lowerLimit = (int) Math.ceil(myValue * 0.9);
        var upperLimit = (int) Math.floor(myValue * 1.1);

        return new Range(lowerLimit, upperLimit);
    }
}
