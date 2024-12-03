-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 03/12/2024 às 22:25
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `atividade_final_java`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `evento`
--

CREATE TABLE `evento` (
  `id` int(11) NOT NULL,
  `organizador_id` int(11) DEFAULT NULL,
  `local_id` int(11) DEFAULT NULL,
  `data` datetime NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `vagas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `evento`
--

INSERT INTO `evento` (`id`, `organizador_id`, `local_id`, `data`, `descricao`, `vagas`) VALUES
(1, 1, 1, '2024-12-25 20:00:00', 'Ceia de Natal', 15),
(2, 1, 1, '2024-11-27 20:45:50', 'teste', 60),
(3, 1, 1, '2025-11-27 20:50:30', 'teste2', 50),
(4, 1, 1, '2024-11-27 21:05:00', 'teste3', 50),
(5, 1, 1, '2024-11-27 21:05:00', 'teste4', 50),
(6, 2, 1, '2024-12-30 21:05:00', 'Virada de ano', 49);

-- --------------------------------------------------------

--
-- Estrutura para tabela `evento_participante`
--

CREATE TABLE `evento_participante` (
  `evento_id` int(11) NOT NULL,
  `participante_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `evento_participante`
--

INSERT INTO `evento_participante` (`evento_id`, `participante_id`) VALUES
(1, 1),
(2, 1),
(5, 1),
(6, 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `local`
--

CREATE TABLE `local` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `vagas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `local`
--

INSERT INTO `local` (`id`, `descricao`, `vagas`) VALUES
(1, 'local espa‡oso', 50);

-- --------------------------------------------------------

--
-- Estrutura para tabela `notifica`
--

CREATE TABLE `notifica` (
  `id` int(11) NOT NULL,
  `texto` varchar(255) NOT NULL,
  `destino` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `notifica`
--

INSERT INTO `notifica` (`id`, `texto`, `destino`) VALUES
(1, 'Você foi notificado para o evento!', '47912345678'),
(2, 'Você foi notificado para o evento!', '47912345678'),
(3, 'Você foi notificado para o evento!', '47912345678'),
(4, 'Você foi notificado para o evento!', '47912345678'),
(5, 'Você tem uma nova atualização no evento!', 'senac@gmail.com'),
(6, 'Você tem uma nova atualização no evento!', 'senac@gmail.com'),
(7, 'Você foi notificado para o evento!', '47912345678');

-- --------------------------------------------------------

--
-- Estrutura para tabela `organizador`
--

CREATE TABLE `organizador` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `notificacao_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `organizador`
--

INSERT INTO `organizador` (`id`, `nome`, `email`, `notificacao_id`) VALUES
(1, 'senac', 'senac@gmail.com', 6),
(2, 'senai', 'senai@gmail.com', NULL),
(3, 'sebrae', 'aebrae@gmail.com', NULL),
(4, 'ascensus', 'algo@gmail.com', NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `participante`
--

CREATE TABLE `participante` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `notificacao_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `participante`
--

INSERT INTO `participante` (`id`, `nome`, `telefone`, `notificacao_id`) VALUES
(1, 'Iago', '47912345678', 7),
(2, 'Marieli', '47901234567', NULL);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `local_id` (`local_id`),
  ADD KEY `organizador_id` (`organizador_id`);

--
-- Índices de tabela `evento_participante`
--
ALTER TABLE `evento_participante`
  ADD PRIMARY KEY (`evento_id`,`participante_id`),
  ADD KEY `participante_id` (`participante_id`);

--
-- Índices de tabela `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `notifica`
--
ALTER TABLE `notifica`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `organizador`
--
ALTER TABLE `organizador`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notificacao_id` (`notificacao_id`);

--
-- Índices de tabela `participante`
--
ALTER TABLE `participante`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notificacao_id` (`notificacao_id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `evento`
--
ALTER TABLE `evento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `local`
--
ALTER TABLE `local`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `notifica`
--
ALTER TABLE `notifica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `organizador`
--
ALTER TABLE `organizador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `participante`
--
ALTER TABLE `participante`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`local_id`) REFERENCES `local` (`id`),
  ADD CONSTRAINT `evento_ibfk_2` FOREIGN KEY (`organizador_id`) REFERENCES `organizador` (`id`);

--
-- Restrições para tabelas `evento_participante`
--
ALTER TABLE `evento_participante`
  ADD CONSTRAINT `evento_participante_ibfk_1` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`),
  ADD CONSTRAINT `evento_participante_ibfk_2` FOREIGN KEY (`participante_id`) REFERENCES `participante` (`id`);

--
-- Restrições para tabelas `organizador`
--
ALTER TABLE `organizador`
  ADD CONSTRAINT `organizador_ibfk_1` FOREIGN KEY (`notificacao_id`) REFERENCES `notifica` (`id`);

--
-- Restrições para tabelas `participante`
--
ALTER TABLE `participante`
  ADD CONSTRAINT `participante_ibfk_1` FOREIGN KEY (`notificacao_id`) REFERENCES `notifica` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
