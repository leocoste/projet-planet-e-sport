package entities;

public class Team implements Entity {
    private int teamId;
    private String teamName;

    private final int utc;

    public Team(int teamId, String teamName, int utc) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.utc = utc;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getUtc() {
        return utc;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", utc=" + utc +
                '}';
    }
}
