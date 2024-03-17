package be.howest.ti.monopoly.logic.implementation;

import java.security.SecureRandom;

public class Dice {
    private int[] lastRoll = new int[2];
    private int lastRollAsInt;
    public static final int NUMBER_OF_DICES = 2;
    private final SecureRandom random = new SecureRandom();

    public Dice() {
        calculateDiceRoll();
    }

    public void calculateDiceRoll() {
        int[] numbers = new int[2];
        int randomNumber;
        for (int i = 0; i < NUMBER_OF_DICES; i++) {
            randomNumber = random.nextInt(6) + 1;
            numbers[i] = randomNumber;
        }
        this.lastRoll = numbers;
        diceRollAsInt();
    }

    public int[] getLastRoll() {
        return lastRoll;
    }

    public int getLastRollAsInt() {
        return lastRollAsInt;
    }

    public void diceRollAsInt() {
        int rollAsInt = 0;

        for (int number : lastRoll) {
            rollAsInt += number;
        }

        this.lastRollAsInt = rollAsInt;
    }

    public boolean checkDoubleRoll(){
        return lastRoll[0] == lastRoll[1];
    }


}
