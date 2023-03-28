-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : ven. 13 jan. 2023 à 12:26
-- Version du serveur : 10.3.32-MariaDB
-- Version de PHP : 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `p2101797`
--

-- --------------------------------------------------------

--
-- Structure de la table `EsportMatch`
--

CREATE TABLE `EsportMatch` (
  `matchID` int(11) NOT NULL,
  `team1ID` int(11) DEFAULT NULL,
  `team2ID` int(11) DEFAULT NULL,
  `matchDate` date DEFAULT NULL,
  `gameID` int(11) DEFAULT NULL,
  `tournamentID` int(11) DEFAULT NULL,
  `winnerID` int(11) DEFAULT NULL,
  `team1score` int(11) NOT NULL DEFAULT 0,
  `team2score` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `EsportMatch`
--

INSERT INTO `EsportMatch` (`matchID`, `team1ID`, `team2ID`, `matchDate`, `gameID`, `tournamentID`, `winnerID`, `team1score`, `team2score`) VALUES
(1, 1, 2, '2023-03-10', 1, 1, 1, 0, 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `EsportMatch`
--
ALTER TABLE `EsportMatch`
  ADD PRIMARY KEY (`matchID`),
  ADD KEY `FK_tournament` (`tournamentID`),
  ADD KEY `FK_team1ID` (`team1ID`),
  ADD KEY `FK_gameID` (`gameID`) USING BTREE,
  ADD KEY `FK_team2ID` (`team2ID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `EsportMatch`
--
ALTER TABLE `EsportMatch`
  MODIFY `matchID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `EsportMatch`
--
ALTER TABLE `EsportMatch`
  ADD CONSTRAINT `FK_gameID` FOREIGN KEY (`gameID`) REFERENCES `Game` (`gameID`),
  ADD CONSTRAINT `FK_team1ID` FOREIGN KEY (`team1ID`) REFERENCES `Team` (`teamID`),
  ADD CONSTRAINT `FK_team2ID` FOREIGN KEY (`team2ID`) REFERENCES `Team` (`teamID`),
  ADD CONSTRAINT `FK_tounamentID` FOREIGN KEY (`tournamentID`) REFERENCES `Tournament` (`tournamentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
