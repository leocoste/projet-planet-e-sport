package entities;

import ui.components.panels.planning.PlanningDisplayPanel;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Tournament implements Entity {
    public static final int GLOBAL = 0;
    public static final int REGIONAL = 1;
    private int tournamentId;
    private Date beginDate;
    private Date endDate;
    private final int type;
    private final int startTime;
    private final int matchDuration;
    private final int breakDuration;
    private final List<Room> rooms;
    private List<Team> teams;

    public Tournament(Date beginDate,int startTime, int type, int matchDuration, int breakDuration, int nTeams){
        this(beginDate,startTime,type,matchDuration,breakDuration,new ArrayList<>(), PlanningDisplayPanel.createTestPlanning(nTeams,startTime,matchDuration,breakDuration).getTournament().getTeams());
    }

    public Tournament(Date beginDate,int startTime, int type, int matchDuration, int breakDuration, List<Room> rooms, List<Team> teams) {
        if(type != GLOBAL && type != REGIONAL) throw new RuntimeException("Invalid tournament type");
        this.beginDate = beginDate;
        this.type = type;
        this.startTime = startTime;
        this.matchDuration = matchDuration;
        this.breakDuration = breakDuration;
        this.rooms = rooms;
        this.teams = teams;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isGlobal(){
        return this.type==GLOBAL;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getType() {
        return type;
    }

    public int getMatchDuration() {
        return matchDuration;
    }

    public int getBreakDuration() {
        return breakDuration;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public int getNTeams() {
        return teams.size();
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tournamentId=" + tournamentId +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", type=" + type +
                ", startTime=" + startTime +
                ", matchDuration=" + matchDuration +
                ", breakDuration=" + breakDuration +
                '}';
    }
}
