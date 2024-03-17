package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.InsufficientFundsException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;

import java.util.*;

public class Game {
    private final String gameId;
    private final int numberOfPlayers;
    private List<Player> players;
    private boolean started;
    private boolean canRoll;
    private boolean ended;
    private Player currentPlayer;
    private Player winner;
    private Dice lastRoll;
    private boolean directSale;

    public static final int JAIL_FINE_MONEY = 50;
    public static final int TAX_FINE = 200;
    public static final int TAX_POSITION_1 = 4;
    public static final int TAX_POSITION_2 = 38;

    public Game(int numberOfPlayers, String gameId) {
        this.gameId = gameId;
        this.numberOfPlayers = numberOfPlayers;
        this.players = new ArrayList<>();
        this.canRoll = false;
        this.ended = false;
        this.started = false;
        this.currentPlayer = null;
        this.winner = null;
        this.directSale = false;
    }

    public void playerJoinsGame(Player player) {
        if (players.size() < numberOfPlayers && !alreadyInLobby(player)) {
            players.add(player);
        }
        makeGameStarted();
    }

    public boolean alreadyInLobby(Player player) {
        for (Player playerInLobby : players) {
            if (!players.isEmpty()) {
                return playerInLobby.getName().equals(player.getName());
            }
        }
        return false;
    }

    private void makeGameStarted() {
        if (players.size() == numberOfPlayers) {
            started = true;
            canRoll = true;
            currentPlayer = players.get(0);
        }
    }

    public Player getPlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dice getLastRoll() {
        return lastRoll;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean canRoll() {
        return canRoll;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isCurrentPlayer(Player player) {
        return currentPlayer.equals(player);
    }

    public boolean isCurrentPlayer(String playerName) {
        return currentPlayer.getName().equals(playerName);
    }

    public boolean isDirectSale() {
        if (currentPlayer == null) {
            return false;
        }
        for (Property property : getAllPropertyTypes()) {
            if (property.getPosition() == currentPlayer.getPosition() && !alreadyPurchased(property)) {
                this.directSale = true;
                this.canRoll = false;
                return true;
            }
        }
        this.directSale = false;
        this.canRoll = true;
        return false;
    }

    public String getGameId() {
        return gameId;
    }

    private Player moveToNextPlayer(Player currentPlayer) {
        Player nextPlayer;
        int thisPlayer = players.indexOf(currentPlayer);

        if (thisPlayer == players.size() - 1) {
            nextPlayer = players.get(0);
        } else {
            nextPlayer = players.get(thisPlayer + 1);
        }
        return nextPlayer;
    }

    public void rollDice(String playerName) {
        JailExecuting jail = new JailExecuting();
        lastRoll = new Dice();
        Player player = getPlayer(playerName);
        if (canRoll && isCurrentPlayer(player)) {
            if (!player.isBankrupt()) {
                player.setHasPayedTax(false);
                player.setPosition(lastRoll, getCurrentPlayer().getPosition());
                checkDoubleRolled(player, lastRoll, jail);
            }
        } else {
            throw new IllegalMonopolyActionException("Not your turn");
        }
    }

    private void checkDoubleRolled(Player player, Dice dice, JailExecuting jail) {
        if (!dice.checkDoubleRoll()) {
            if (jail.isOnGoToJail(player)) {
                jail.sendToJail(player);
                jail.resetAmountOfDoubleRolled();
                setCurrentPlayer(moveToNextPlayer(player));
            } else {
                jail.resetAmountOfDoubleRolled();
            }
        } else {
            jail.addDoubleRolled();
            if (jail.isOnGoToJail(player) || jail.checkIfTripleDoubleRolled()) {
                jail.sendToJail(player);
                jail.resetAmountOfDoubleRolled();
            }
        }
    }

    public boolean getOutOfJailFine(String playerName) {
        Player player = searchForTheRightPlayer(playerName);
        if (!player.getName().equals(currentPlayer.getName())) {
            throw new IllegalMonopolyActionException("Not the current player");
        } else if (!player.isJailed()) {
            throw new IllegalMonopolyActionException("Not currently jailed");
        }
        else if (player.getMoney() >= JAIL_FINE_MONEY) {
            player.removeMoney(JAIL_FINE_MONEY);
            player.unJailPlayer();
            return true;
        } else {
            player.setPlayerBankrupt();
        }
        return false;
    }

    public boolean useOutOfJailCard(String playerName) {
        Player player = searchForTheRightPlayer(playerName);
        if (!player.getName().equals(currentPlayer.getName())) {
            throw new IllegalMonopolyActionException("Not your turn");
        } else if (!player.isJailed()) {
            throw new IllegalMonopolyActionException("You are not in jail");
        } else if (player.getGetOutJailCards() >= 1) {
            player.useOutOfJailCard();
            player.unJailPlayer();
        } else {
            throw new IllegalMonopolyActionException("Not enough cards");
        }
        return true;
    }


    public Player searchForTheRightPlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        throw new MonopolyResourceNotFoundException("Cannot find the right person");
    }

    public boolean isEnded() {
        return ended;
    }

    public Player getWinner() {
        return winner;
    }

    public void declarePlayerBankrupt(String playerName) {
        if (currentPlayer.getName().equals(playerName)) {
            currentPlayer.setPlayerBankrupt();
            currentPlayer.clearAssets();
            setCurrentPlayer(moveToNextPlayer(getCurrentPlayer()));
            checkIfGameEnded();
        } else {
            throw new IllegalMonopolyActionException("cannot set this player bankrupt");
        }
    }

    private void checkIfGameEnded() {
        int bankruptPlayers = 0;
        for (Player player : players) {
            if (player.isBankrupt()) {
                bankruptPlayers++;
            }
        }

        if (players.size() - 1 == bankruptPlayers) {
            this.ended = true;
            this.winner = this.currentPlayer;
        }
    }

    public void buyProperty(Player player, Property property) {
        if (!player.getName().equals(currentPlayer.getName())) {
            throw new IllegalMonopolyActionException("Not the current player");
        } else if (!isDirectSale()) {
            throw new IllegalMonopolyActionException("Cannot purchase a non-property or a property that is already bought");
        } else if (player.getMoney() < property.getCost()) {
            throw new IllegalMonopolyActionException("Cannot purchase a property due to lack of money");
        } else {
            player.removeMoney(property.getCost());
            player.addPropertyToProperties(property);
            setCurrentPlayer(moveToNextPlayer(getCurrentPlayer()));
            this.directSale = false;
            this.canRoll = true;
        }
    }

    public void dontBuyProperty() {
        setCurrentPlayer(moveToNextPlayer(getCurrentPlayer()));
        this.directSale = false;
        this.canRoll = true;
    }

    private boolean alreadyPurchased(Property property) {
        for (Player player : players) {
            if (player.getProperties().contains(property)) {
                return true;
            }
        }
        return false;
    }

    public boolean collectDebt(Player debtor, Player owner) {
        if (currentPlayer.getName().equals(debtor.getName())) {
            if (checkIfCurrentPlayerIsOnOwnedAsset(owner)) {
                Property property = checkIfDebtorHasEnoughMoney(debtor);
                debtor.payRent(property.getRent());
                owner.addMoney(property.getRent());
                return true;
            }
            throw new IllegalMonopolyActionException("It isn't an owned asset by the owner");
        }
        throw new IllegalMonopolyActionException("Owner can't get money from debtor who isn't on his property");
    }

    private Property checkIfDebtorHasEnoughMoney(Player debtor) {
        Tile currentTile = getCurrentTile(debtor);
        List<Property> properties = getAllPropertyTypes();
        if (properties.contains(currentTile)) {
            Property currentStreet = properties.get(properties.indexOf(currentTile));
            if (debtor.getMoney() >= currentStreet.getRent()) {
                return currentStreet;
            } else {
                declarePlayerBankrupt(debtor.getName());
                throw new InsufficientFundsException("not enough money, you are now bankrupt");
            }
        } else {
            throw new IllegalMonopolyActionException("Not a property means no rent");
        }
    }

    public Tile getCurrentTile(Player player) {
        for (Tile tile : new MonopolyService().getTiles()) {
            if (tile.getPosition() == player.getPosition()) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("Cannot find this tile");
    }

    private List<Property> getAllPropertyTypes() {
        List<Property> properties = new ArrayList<>();
        for (Tile tile : new MonopolyService().getTiles()) {
            if (tile.getType().equals("Street") || tile.getType().equals("RailRoad") || tile.getType().equals("Utility")) {
                properties.add((Property) tile);
            }
        }
        return properties;
    }

    public boolean checkIfCurrentPlayerIsOnOwnedAsset(Player owner) {
        for (Tile tile : owner.getProperties()) {
            if (currentPlayer.getPosition() == tile.getPosition()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }

    public boolean payTax(String playerName) {
        if (!currentPlayer.getName().equals(playerName)) {
            throw new IllegalMonopolyActionException("Its not your turn");
        } else if (currentPlayer.getPosition() != TAX_POSITION_1 && currentPlayer.getPosition() != TAX_POSITION_2) {
            throw new IllegalMonopolyActionException("Not on tax position");
        } else if (currentPlayer.getMoney() < TAX_FINE) {
            declarePlayerBankrupt(playerName);
            return false;
        } else if (currentPlayer.isHasPayedTax()){
            throw new IllegalMonopolyActionException("Cannot pay tax twice");
        } else {
            currentPlayer.removeMoney(TAX_FINE);
            currentPlayer.setHasPayedTax(true);
            setCurrentPlayer(moveToNextPlayer(getCurrentPlayer()));
            return true;
        }
    }
}
