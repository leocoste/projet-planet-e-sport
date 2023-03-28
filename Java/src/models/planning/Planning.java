package models.planning;

import entities.Room;
import entities.Team;
import entities.Tournament;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Planning {
    private List<Match> firstBracket;
    private final Tournament tournament;
    private static int lastMatchId = 0;

    /**
     * teams : List of teams playing in the tournament
     * type : Regional / Global (use constants in Tournament)
     * startTime : hour
     * minMatchTime : minutes
     * maxMatchTime : minutes
     * rooms : List of rooms available for the tournament
     * startingDate : Date
     * matchDuration : minutes
     * breakDuration : time between matches, minutes
     */
    public Planning(List<Team> teams,int type, int startTime,int minMatchTime, int maxMatchTime, List<Room> rooms, Date startingDate, int matchDuration, int breakDuration){
        this.tournament=new Tournament(startingDate, startTime*60,type, matchDuration, breakDuration,rooms, teams);
    }
    public List<Match> generateFirstBracket(){
        System.out.println("Generating first bracket");
        // Returns a list of matches created considering the time between the matches, the available rooms
        int nRooms = this.tournament.getRooms().toArray().length;
        int freeRooms = nRooms;
        // List to return
        List<Match> matches = new ArrayList<>();
        int i = 0;
        Match currentMatch = null;
        float matchTime = this.tournament.getStartTime();

        for(Team team: this.tournament.getTeams()){
            if(i%2==0){
                // Every 2 team, instance new match
                try {
                    currentMatch = new Match(matchTime, this.tournament.getRooms().get(nRooms - freeRooms), lastMatchId++);
                }catch (IndexOutOfBoundsException e){
                    currentMatch = new Match(matchTime, null, lastMatchId++);

                }
                // Add the new match instance to the list
                matches.add(currentMatch);
                // Decrement the number of available rooms
                freeRooms--;
            }
            // Add team to current match
            currentMatch.addTeam(team);
            i++;

            if(freeRooms == 0){
                // All rooms are assigned, shift the matchTime by the match duration + the time between the matches
                freeRooms = nRooms;
                matchTime += this.tournament.getMatchDuration()+(float)1/((float)this.tournament.getBreakDuration()/60);
            }
        }
        this.firstBracket = matches;
        createNextMatches(matches);
        return matches;
    }

    private void createNextMatches(List<Match> bracket){
        if(bracket.toArray().length <= 1) return;
        int i = 0;
        int nRooms = this.tournament.getRooms().toArray().length;
        int freeRooms = nRooms;
        float matchTime = this.tournament.getStartTime();
        float matchDuration = (float)1/((float)this.tournament.getMatchDuration()/60);
        float breakDuration = (float)1/((float)this.tournament.getBreakDuration()/60);
        List<Match> nextBracket = new ArrayList<>();
        Match nextMatch = null;

        for(Match match : bracket){
            if(i % 2 == 0 ) {
                try {
                    nextMatch = new Match(matchTime, this.tournament.getRooms().get(nRooms - freeRooms), lastMatchId++);
                    freeRooms--;
                }catch(IndexOutOfBoundsException e){
                    nextMatch = new Match(matchTime, null, lastMatchId++);
                }
            }else{
                nextBracket.add(nextMatch);
            }
            if(freeRooms == 0){
                freeRooms = nRooms;
                matchTime += matchDuration + breakDuration;
            }

            match.setNext(nextMatch);
            i++;
        }
        createNextMatches(nextBracket);
    }

    public void forward(){
        System.out.println("------------------------");
        this.forward(this.firstBracket);
    }

    public void forward(List<Match> bracket){
        System.out.println("-- Forward --");
        System.out.println(bracket);
        if(bracket.toArray().length < 1) return;
        List<Match> nextBracket =  new ArrayList<>();
        for(Match match : bracket){
            Match nextMatch = match.getNext();
            Team winner = match.getWinner();
            if(winner == null) continue;
            nextMatch.addTeam(winner);
            nextBracket.add(nextMatch);
        }
        forward(nextBracket);
    }

    public void setTeams(List<Team> teams){
        this.tournament.setTeams(teams);
    }

    public Tournament getTournament() {
        return tournament;
    }

    public List<Match> getFirstBracket() {
        return firstBracket;
    }
}
