package com.example.houseofcards2024fulldemo.domain;

import java.util.Random;

public class Game {

    private static final int MAX_NUMBER = 10;

    private int playerPoints;
    private int number;

    // TODO maybe improve the project by using Map instead of Array for houses
    private House[] houses;
    private Random rng;

    public Game() {
        playerPoints = 0;

        houses = new House[4];
        houses[0] = new House(); // alpha
        houses[1] = new House(); // beta
        houses[2] = new House(); // charlie
        houses[3] = new House(); // delta

        rng = new Random();

        generateNumber();
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public int getNumber() {
        return number;
    }

    public House assignToHouse(int houseIndex) {
        if (houseIndex < 0 || houseIndex > houses.length - 1) {
            throw new IllegalArgumentException("houseIndex must be in the range of 0 to houses.length - 1, which is " + (houses.length - 1));
        }

        House selectedHouse = houses[houseIndex];
        int pointsToAddToPlayer = selectedHouse.addNumber(number);
        playerPoints += pointsToAddToPlayer;
        generateNumber();

        return selectedHouse;
    }

    public boolean isGameOver() {
        boolean isGameOver = true;
        for (int i = 0; i < houses.length; i++) {
            isGameOver = isGameOver && houses[i].isClosed();
        }

        return isGameOver;
    }

    private void generateNumber() {
        number = rng.nextInt(MAX_NUMBER) + 1;
    }
}
