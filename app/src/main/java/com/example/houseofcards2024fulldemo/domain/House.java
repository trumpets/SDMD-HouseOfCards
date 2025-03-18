package com.example.houseofcards2024fulldemo.domain;

public class House {

    private static final int SUM_MAX = 30;
    private static final int SUM_SCORING = 21;
    private static final int POINTS_SCORING = 10;
    private static final int POINTS_PERFECT = 50;
    private static final int POINTS_NEGATIVE = -20;

    private int currentSum;
    private boolean isClosed;

    public House() {
        currentSum = 0;
        isClosed = false;
    }

    public int getCurrentSum() {
        return currentSum;
    }

    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Adds the new value to the house sum total and returns back the points
     * it awards the player.
     *
     * @param number new value to add to the house sum total
     * @return the points to award the player
     */
    public int addNumber(int number) {
        if (isClosed) {
            return 0;
        }

        currentSum += number;
        if (currentSum == SUM_SCORING) {
            currentSum = 0;
            return POINTS_SCORING;
        }

        if (currentSum >= SUM_MAX) {
            isClosed = true;

            if (currentSum == SUM_MAX) {
                return POINTS_PERFECT;
            } else {
                return POINTS_NEGATIVE;
            }
        }

        return 0;
    }
}
