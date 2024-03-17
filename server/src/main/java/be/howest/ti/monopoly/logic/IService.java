package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.non_properties.ChanceCard;
import be.howest.ti.monopoly.logic.implementation.non_properties.CommunityCard;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;

public interface IService {
    String getVersion();

    List<Tile> getTiles();

    Tile getTile(int position);

    Tile getTile(String name);

    Game createGame(String prefix, int numberOfPlayers);

    List<Game> getGames();

    List<ChanceCard> getChance();

    List<CommunityCard> getCommunityChest();

    Game rollDice(String gameID, String playerName);

    boolean joinGame(String gameId, String playerName);

    boolean buyProperty(String gameId, String playerName, String propertyName);

    boolean dontBuyProperty(String gameId, String playerName, String propertyName);

    boolean collectDebt(String gameId, String playerName, String propertyName, String debtorName);

    Game declareBankruptcy(String gameId, String playerName);

    boolean getOutOfJailFine(String gameId, String playerName);

    boolean receiveRent(String gameId, String playerName, String propertyName, String debtorName);

    Game getGame(String gameId);

    boolean useOutOfJailCard(String gameId, String playerName);

    boolean clearGameList();

    boolean payTax(String gameId, String playerName);
}
