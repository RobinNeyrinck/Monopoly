package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.non_properties.ChanceCard;
import be.howest.ti.monopoly.logic.implementation.non_properties.CommunityCard;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;

public class ServiceAdapter implements IService {

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tile> getTiles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game createGame(String prefix, int numberOfPlayers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Game> getGames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ChanceCard> getChance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<CommunityCard> getCommunityChest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game rollDice(String gameID, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean joinGame(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean buyProperty(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean dontBuyProperty(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game declareBankruptcy(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getOutOfJailFine(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean receiveRent(String gameId, String playerName, String propertyName, String debtorName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getGame(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean useOutOfJailCard(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    public boolean clearGameList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean payTax(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

}
