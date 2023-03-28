-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 18 jan. 2023 à 15:35
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
-- Structure de la table `Team`
--

CREATE TABLE `Team` (
  `teamID` int(11) NOT NULL,
  `teamName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `teamImg` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `teamUTC` int(2) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Team`
--

INSERT INTO `Team` (`teamID`, `teamName`, `teamImg`, `teamUTC`) VALUES
(1, '9Cloud', 'et', 2),
(2, 'Team Vitality', 'pellentesque', 2),
(3, 'Fnatic', 'risus.', 2),
(4, 'Team DK', 'feugiat', 1),
(5, 'Team Liquid ', 'mauris', 5);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Team`
--
ALTER TABLE `Team`
  ADD PRIMARY KEY (`teamID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Team`
--
ALTER TABLE `Team`
  MODIFY `teamID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
