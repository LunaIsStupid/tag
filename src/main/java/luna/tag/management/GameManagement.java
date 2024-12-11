package luna.tag.management;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameManagement {
    // placeholder
    private boolean gameOngoing;
    private String gameName;

    private List<UUID> losers;

    private List<UUID> players;

    private int instanceCounter;
    private static volatile GameManagement instance;
    private GameManagement() {
        players = new ArrayList<>();
        losers = new ArrayList<>();
        instanceCounter++;
        gameOngoing = false;
    }
    public static GameManagement getInstance() {
        if (instance == null) {
            instance = new GameManagement();
        }
        return instance;
    }

    public Boolean getGameOngoing() {
        return gameOngoing;
    }

    public void setGameOngoing(Boolean gameOngoing) {
        this.gameOngoing = gameOngoing;
    }

    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<UUID> getLosers() {
        return losers;
    }

    public void addLoser(UUID loser) {
        losers.add(loser);
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public void addPlayer(UUID player) {
        players.add(player);
    }

    public void clearLosers() {
        losers.clear();
    }
    public void clearPlayers() {
        players.clear();
    }
}
