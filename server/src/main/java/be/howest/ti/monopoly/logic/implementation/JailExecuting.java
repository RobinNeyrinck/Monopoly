package be.howest.ti.monopoly.logic.implementation;

public class JailExecuting {
    private int amountOfDoubleRolled = 0;

    public boolean isOnGoToJail(Player player) {
        int goToJailTilePosition = 30;
        return player.getPosition() == goToJailTilePosition;
    }

    public int getAmountOfDoubleRolled() {
        return amountOfDoubleRolled;
    }

    public void addDoubleRolled(){
        amountOfDoubleRolled ++;
    }

    public void sendToJail(Player player) {
        if (isOnGoToJail(player) || (checkIfTripleDoubleRolled())) {
            player.moveToJail();
        }
    }

    public boolean checkIfTripleDoubleRolled(){
        return amountOfDoubleRolled == 3;
    }

    public void resetAmountOfDoubleRolled() {
        amountOfDoubleRolled = 0;
    }
}
