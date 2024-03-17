package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.non_properties.ChanceCard;
import be.howest.ti.monopoly.logic.implementation.non_properties.CommunityCard;
import be.howest.ti.monopoly.logic.implementation.non_properties.FreeParking;
import be.howest.ti.monopoly.logic.implementation.non_properties.Jail;
import be.howest.ti.monopoly.logic.implementation.non_properties.executing_tiles.*;
import be.howest.ti.monopoly.logic.implementation.properties.RailRoad;
import be.howest.ti.monopoly.logic.implementation.properties.Street;
import be.howest.ti.monopoly.logic.implementation.properties.Utility;

import java.util.ArrayList;
import java.util.List;


public class MonopolyService extends ServiceAdapter {
    private final List<Game> allGames = new ArrayList<>();

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public List<Tile> getTiles() {
        return List.of(
                new Go(0, "Start"),
                new Street(1, "Maes", 60, 30, 2, 2),
                new CommunityChest(2, "Algemene Fonds I"),
                new Street(3, "Maes Radler", 60, 30, 2, 4),
                new TaxIncome(4, "Tournee Generale I"),
                new RailRoad(5, "Station I", 200, 100, 4, 25),
                new Street(6, "Cara Rouge", 100, 50, 3, 6),
                new Chance(7, "Kans I"),
                new Street(8, "Cara Blond", 100, 50, 3, 6),
                new Street(9, "Cara", 120, 60, 3, 6),
                new Jail(10, "Ziekenhuis"),
                new Street(11, "Leffe Blond", 140, 70, 3, 10),
                new Utility(12, "Jupiler 0.0", 150, 75, 2, 10),
                new Street(13, "Leffe Bruin", 140, 70, 3, 10),
                new Street(14, "Leffe Tripel", 160, 80, 3, 12),
                new RailRoad(15, "Station II", 200, 100, 4, 25),
                new Street(16, "Martha Sexy Blond", 180, 90, 3, 14),
                new CommunityChest(17, "Algemene fonds II"),
                new Street(18, "Martha Guilty Pleasure", 180, 90, 3, 14),
                new Street(19, "Martha Brown Eyes", 200, 100, 3, 16),
                new FreeParking(20, "Happy Hour"),
                new Street(21, "Duvel Blond", 220, 110, 3, 18),
                new Chance(22, "Kans II"),
                new Street(23, "Duvel 666", 220, 110, 3, 18),
                new Street(24, "Duvel Tripel Hop", 240, 120, 3, 20),
                new RailRoad(25, "Station III", 200, 100, 4, 25),
                new Street(26, "Kasteel Tripel", 260, 130, 3, 22),
                new Street(27, "Kasteel Blond", 260, 130, 3, 22),
                new Utility(28, "Stella Artois 0.0", 150, 75, 2, 25),
                new Street(29, "Kasteel Bruin", 280, 140, 3, 24),
                new GoToJail(30, "Ga Naar Ziekenhuis"),
                new Street(31, "Grimbergen Blond", 300, 150, 3, 26),
                new Street(32, "Grimbergen Double", 300, 150, 3, 26),
                new CommunityChest(33, "Algemene fonds III"),
                new Street(34, "Grimbergen Tripel", 300, 150, 3, 26),
                new RailRoad(35, "Station IV", 200, 100, 4, 35),
                new Chance(36, "Kans III"),
                new Street(37, "Jupiler", 350, 175, 2, 35),
                new LuxuryTax(38, "Tournee Generale II"),
                new Street(39, "Stella", 400, 200, 2, 50)
        );
    }

    @Override
    public Tile getTile(int position) {
        for (Tile tile : getTiles()) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public Tile getTile(String name) {
        for (Tile tile : getTiles()) {
            if (tile.getName().equals(name)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public List<ChanceCard> getChance() {
        return List.of(
                new ChanceCard("Advance to Boardwalk"),
                new ChanceCard("Advance to Go (Collect $200)"),
                new ChanceCard("If you pass Go, collect $200")
        );
    }

    @Override
    public boolean collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
        Game game = getGame(gameId);
        Player owner = game.searchForTheRightPlayer(playerName);
        Player debtor = game.searchForTheRightPlayer(debtorName);
        return game.collectDebt(debtor, owner);
    }

    @Override
    public List<CommunityCard> getCommunityChest() {
        return List.of(
                new CommunityCard("Advance to Boardwalk"),
                new CommunityCard("Bank error in your favor. Collect $200"),
                new CommunityCard("Get Out of Jail Free")
        );
    }

    @Override
    public Game rollDice(String gameID, String playerName) {
        Game game = getGame(gameID);
        game.rollDice(playerName);
        return game;
    }

    @Override
    public Game getGame(String gameID) {
        for (Game entry : allGames) {
            if (entry.getGameId().equals(gameID)) {
                return entry;
            }
        }
        throw new MonopolyResourceNotFoundException("No game found");
    }

    @Override
    public Game createGame(String prefix, int numberOfPlayers) {
        String gameID = prefix + "_" + (allGames.size() + 1);

        Game newGame = new Game(numberOfPlayers, gameID);

        allGames.add(newGame);
        return newGame;
    }

    @Override
    public List<Game> getGames() {
        return allGames;
    }

    @Override
    public boolean buyProperty(String gameId, String playerName, String propertyName) {
        Game game = getGame(gameId);
        Player player = game.searchForTheRightPlayer(playerName);
        Property property = searchForTheRightProperty(propertyName);
        game.buyProperty(player, property);
        return true;
    }

    @Override
    public boolean dontBuyProperty(String gameId, String playerName, String propertyName) {
        Game game = getGame(gameId);
        game.dontBuyProperty();
        return false;
    }

    public Property searchForTheRightProperty(String propertyName) {
        List<Tile> tiles = getTiles();
        for (Tile tile : tiles) {
            if (tile.getName().equals(propertyName)) {
                return (Property) tile;
            }
        }
        throw new MonopolyResourceNotFoundException("There is no tile or property named like this");
    }

    @Override
    public Game declareBankruptcy(String gameId, String playerName) {
        Game game = getGame(gameId);
        game.declarePlayerBankrupt(playerName);
        return game;
    }

    @Override
    public boolean joinGame(String gameId, String playerName) {
        Game game = getGame(gameId);
        game.playerJoinsGame(new Player(playerName));
        return true;
    }

    @Override
    public boolean getOutOfJailFine(String gameId, String playerName) {
        Game game = getGame(gameId);
        return game.getOutOfJailFine(playerName);
    }

    @Override
    public boolean useOutOfJailCard(String gameId, String playerName) {
        Game game = getGame(gameId);
        return game.useOutOfJailCard(playerName);
    }

    @Override
    public boolean clearGameList() {
        allGames.clear();
        return true;
    }

    @Override
    public boolean payTax(String gameId, String playerName) {
        Game game = getGame(gameId);
        return game.payTax(playerName);
    }
}
