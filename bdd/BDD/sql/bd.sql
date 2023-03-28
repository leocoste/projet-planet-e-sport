create table eSportMatch(
    matchID int NOT NULL,
    team1ID int NOT NULL,
    team2ID int NOT NULL,
    matchDate date NOT NULL,
    gameID int NOT NULL,
    tournamentID int NOT NULL,
    winnerID int,
    team1Score int,
    team2Score int
);
create table tournoi(
    tournamentID int NOT NULL,
    beginDate date NOT NULL,
    casterID int NOT NULL,
    endDate date
);
create table equipe(
    teamID int NOT NULL,
    teamName varchar(255) NOT NULL
);
create table utilisateur(
    userID int NOT NULL,
    userEMail text NOT NULL,
    userPWD text NOT NULL
);
create table jeu(
    gameID int NOT NULL,
    gameName text NOT NULL
);
create table streamer(
    streamerID int NOT NULL,
    steamerName text NOT NULL,
    matchID int
);
create table caster(
    casterID int NOT NULL,
    casterName text NOT NULL
);

alter table eSportMatch add constraint PK_MATCH PRIMARY KEY(matchID);
alter table equipe add constraint PK_TEAM PRIMARY KEY (teamID);
alter table utilisateur add constraint PK_USER primary key (userID);
alter table jeu add constraint PK_GAME primary key (gameID);
alter table streamer add constraint PK_STREAMER PRIMARY KEY (streamerID);
alter table caster add constraint PK_CASTER PRIMARY KEY (casterID);
alter table tournoi add constraint PK_TOURNAMENT PRIMARY KEY (tournamentID);

alter table eSportMatch add constraint FK_TEAM1 FOREIGN KEY(team1ID) references equipe(TeamID);
alter table eSportMatch add constraint FK_TEAM2 FOREIGN KEY (team2ID) references equipe(teamID);
alter table eSportMatch add constraint FK_GAME FOREIGN KEY (gameID) references jeu(gameID);
alter table eSportMatch add constraint FK_TOURNAMENT FOREIGN KEY (tournamentID) references tournoi(tournamentID);
alter table streamer add constraint FK_MATCH FOREIGN KEY (matchID) references eSportMatch(matchID);
alter table tournoi add constraint FK_CASTER FOREIGN KEY (casterID) references caster(casterID);