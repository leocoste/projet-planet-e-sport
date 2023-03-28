-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 18 jan. 2023 à 15:34
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
-- Structure de la table `Tournament`
--

CREATE TABLE `Tournament` (
  `tournamentID` int(11) NOT NULL,
  `beginDate` date DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  `casterID` int(11) DEFAULT NULL,
  `roomID` int(1) DEFAULT NULL,
  `tournamentType` int(5) DEFAULT NULL,
  `matchDuration` int(3) DEFAULT NULL,
  `breakDuration` int(3) DEFAULT NULL,
  `nTeams` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Tournament`
--

INSERT INTO `Tournament` (`tournamentID`, `beginDate`, `startTime`, `casterID`, `roomID`, `tournamentType`, `matchDuration`, `breakDuration`, `nTeams`) VALUES
(1, '2023-01-18', '00:17:00', 1, 1, NULL, 90, 15, 5),
(2, '2023-02-18', '00:17:30', 1, 1, NULL, 90, 15, 3),
(3, '2023-03-18', '00:20:00', 5, 2, NULL, 120, 30, 7);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Tournament`
--
ALTER TABLE `Tournament`
  ADD PRIMARY KEY (`tournamentID`),
  ADD KEY `FK_casterID` (`casterID`),
  ADD KEY `FK_roomID` (`roomID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Tournament`
--
ALTER TABLE `Tournament`
  MODIFY `tournamentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Tournament`
--
ALTER TABLE `Tournament`
  ADD CONSTRAINT `FK_casterID` FOREIGN KEY (`casterID`) REFERENCES `Caster` (`casterID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
