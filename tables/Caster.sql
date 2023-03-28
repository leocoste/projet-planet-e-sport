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
-- Structure de la table `Caster`
--

CREATE TABLE `Caster` (
  `casterID` int(11) NOT NULL,
  `casterName` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `casterImg` varchar(50) CHARACTER SET ucs2 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Caster`
--

INSERT INTO `Caster` (`casterID`, `casterName`, `casterImg`) VALUES
(1, 'ZeratoR', 'magna.'),
(2, 'Doigby', 'eget,'),
(3, 'Lutti', 'in,'),
(4, 'Forrest Goodman', 'ultrices'),
(5, 'McKenzie Foreman', 'Sed');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Caster`
--
ALTER TABLE `Caster`
  ADD PRIMARY KEY (`casterID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Caster`
--
ALTER TABLE `Caster`
  MODIFY `casterID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
