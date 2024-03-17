package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player implements Comparable<Player> {
    private final String name;
    private int position;
    private boolean jailed;
    private int money;
    private boolean bankrupt;
    private int getOutJailCards;
    private List<Property> properties;
    private boolean hasPayedTax;

    public static final int LAST_POSITION_OF_BORD = 39;
    public static final int INDEX_CORRECTION_BORD = 1; // board starts at position 0, so -1 to correct this feature.
    public static final int MONEY_FROM_START = 200;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.jailed = false;
        this.money = 1500;
        this.bankrupt = false;
        this.getOutJailCards = 1;
        this.properties = new ArrayList<>();
        this.hasPayedTax = false;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void removeMoney(int amount) {
        if (amount > 0) {
            money -= amount;
        } else {
            throw new IllegalStateException("You cant remove negative amount");
        }
    }

    public void unJailPlayer() {
        jailed = false;
    }

    public void addPropertyToProperties(Property property) {
        properties.add(property);
    }

    public void setPosition(Dice dice, int currentPosition) {
        currentPosition += dice.getLastRollAsInt();
        if (currentPosition > LAST_POSITION_OF_BORD) {
            currentPosition -= (LAST_POSITION_OF_BORD + INDEX_CORRECTION_BORD);
            addMoney(MONEY_FROM_START);
        }
        this.position = currentPosition;
    }

    public void setPosition(int newPosition) {
        this.position = newPosition;
    }

    public int getPosition() {
        return position;
    }

    public boolean isJailed() {
        return jailed;
    }

    public int getMoney() {
        return money;
    }

    public int getGetOutJailCards() {
        return getOutJailCards;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(name.compareTo(other.name), 0);
    }

    @Override
    public String toString() {
        return "name -- " + name + " -- position -- " + position;
    }

    public void moveToJail() {
        position = 10;
        jailed = true;
    }

    public void setHasPayedTax(boolean taxPayed){
        this.hasPayedTax = taxPayed;
    }

    public void setPlayerBankrupt() {
        this.bankrupt = true;
    }

    public void clearAssets() {
        this.properties.clear();
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void payRent(int rent) {
        this.money -= rent;
    }

    public void useOutOfJailCard() {
        getOutJailCards--;
    }

    public boolean isHasPayedTax() {return hasPayedTax;}

    public void getOutOfJailCard() {
        getOutJailCards++;
    }
}
