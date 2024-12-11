package luna.tag.management;

public class GameManagement {
    // placeholder
    private boolean gameOngoing;
    private String gameName;

    private int instanceCounter;
    private static volatile GameManagement instance;
    private GameManagement() {
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
}
