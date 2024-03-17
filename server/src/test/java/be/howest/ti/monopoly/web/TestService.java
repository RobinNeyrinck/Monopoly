package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.non_properties.ChanceCard;
import be.howest.ti.monopoly.logic.implementation.non_properties.CommunityCard;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;


public class TestService implements IService {

    IService delegate = new ServiceAdapter();

    void setDelegate(IService delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getVersion() {
        return delegate.getVersion();
    }

    @Override
    public List<Tile> getTiles() {
        return delegate.getTiles();
    }

    @Override
    public Tile getTile(int tileId) {
        return delegate.getTile(tileId);
    }

    @Override
    public Tile getTile(String tileName) {
        return delegate.getTile(tileName);
    }

    @Override
    public Game createGame(String prefix, int numberOfPlayers) {
        return delegate.createGame(prefix, numberOfPlayers);
    }

    @Override
    public List<Game> getGames() {
        return delegate.getGames();
    }

    @Override
    public List<ChanceCard> getChance() {
        return delegate.getChance();
    }

    @Override
    public List<CommunityCard> getCommunityChest() {
        return delegate.getCommunityChest();
    }

    @Override
    public Game rollDice(String gameID, String playerName) {
        return delegate.rollDice(gameID, playerName);
    }

    @Override
    public boolean joinGame(String gameId, String playerName) {
        return delegate.joinGame(gameId, playerName);
    }

    @Override
    public boolean buyProperty(String gameId, String playerName, String propertyName) {
        return delegate.buyProperty(gameId, playerName, propertyName);
    }

    @Override
    public boolean dontBuyProperty(String gameId, String playerName, String propertyName) {
        return delegate.dontBuyProperty(gameId, playerName, propertyName);
    }

    @Override
    public boolean collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
        return delegate.collectDebt(gameId, playerName, propertyName, debtorName);
    }

    @Override
    public boolean receiveRent(String gameId, String playerName, String propertyName, String debtorName) {
        return delegate.receiveRent(gameId, playerName, propertyName, debtorName);
    }

    @Override
    public Game getGame(String gameId) {
        return delegate.getGame(gameId);
    }

    @Override
    public boolean useOutOfJailCard(String gameId, String playerName) {
        return delegate.useOutOfJailCard(gameId, playerName);
    }

    @Override
    public boolean clearGameList() {
        return delegate.clearGameList();
    }

    @Override
    public boolean payTax(String gameId, String playerName) {
        return delegate.payTax(gameId, playerName);
    }

    @Override
    public Game declareBankruptcy(String gameId, String playerName) {
        return delegate.declareBankruptcy(gameId, playerName);
    }

    @Override
    public boolean getOutOfJailFine(String gameId, String playerName) {
        return delegate.getOutOfJailFine(gameId, playerName);
    }

}
