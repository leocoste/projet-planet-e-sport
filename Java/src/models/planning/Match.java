package models.planning;

import entities.Room;
import entities.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Match {

    private final int id;
    private final List<Team> teams;
    private Team winner;
    private int[] scores;
    private float time;
    private final Room room;

    private Match next;

    public Match(float time, Room room, int id){
        this.time = time;
        this.room = room;
        this.teams = new ArrayList<>();
        this.id = id;
    }

    public Match(Match match, ArrayList teams, Team winner, int[] score){
        this.time = match.time;
        this.room = match.room;
        this.teams = teams;
        this.id = match.id;
        this.winner = winner;
        this.scores = score;

    }
    public void addTeam(Team team){
        if(this.teams.toArray().length < 2) {
            this.teams.add(team);
        }
    }

    public float getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Team getWinner() {
        return winner;
    }

    public int[] getScores() {
        return scores;
    }

    public void setNext(Match next) {
        if(this.next == null )this.next = next;
    }

    public Match getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id="+id+
                ", teams=" + teams +
                ", winner=" + winner +
                ", scores=" + Arrays.toString(scores) +
                ", time=" + time +
                ", room=" + room +
                ", next=" + next +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Float.compare(match.time, time) == 0 && id == match.id && Objects.equals(teams, match.teams) && Objects.equals(winner, match.winner) && Arrays.equals(scores, match.scores) && Objects.equals(room, match.room) && Objects.equals(next, match.next);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(teams, winner, time, room, next, id);
        result = 31 * result + Arrays.hashCode(scores);
        return result;
    }
}
