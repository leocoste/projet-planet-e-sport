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
-- Structure de la table `Room`
--

CREATE TABLE `Room` (
  `roomID` int(11) NOT NULL,
  `roomName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `roomAddress` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `roomRegion` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `roomCapacity` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Room`
--

INSERT INTO `Room` (`roomID`, `roomName`, `roomAddress`, `roomRegion`, `roomCapacity`) VALUES
(1, 'Accor Arena', '8 Bd de Bercy, 75012 Paris', 'Europe', 1000),
(2, 'Detroit MI', '18400 Hall Rd Clinton Twp, MI 48038', 'North America', 2500);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Room`
--
ALTER TABLE `Room`
  ADD PRIMARY KEY (`roomID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Room`
--
ALTER TABLE `Room`
  MODIFY `roomID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
