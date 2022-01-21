-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2022 at 06:34 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restoran_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `nomor` int(11) NOT NULL,
  `no_transaksi` int(10) NOT NULL,
  `id_makanan` char(5) NOT NULL,
  `harga` mediumint(9) NOT NULL,
  `jumlah` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`nomor`, `no_transaksi`, `id_makanan`, `harga`, `jumlah`) VALUES
(1, 1, 'P0002', 85000, 1),
(2, 1, 'P0003', 95000, 2),
(4, 1, 'P0003', 95000, 3),
(5, 1, 'P0003', 95000, 3),
(6, 2, 'P0003', 95000, 3),
(7, 2, 'P0003', 95000, 3),
(8, 6, 'P0002', 95000, 1),
(9, 6, 'P0001', 87000, 1),
(10, 6, 'P0003', 10000, 1),
(11, 7, 'P0002', 95000, 1),
(12, 7, 'P0003', 10000, 1),
(13, 7, 'P0001', 87000, 1),
(14, 8, 'P0003', 10000, 1),
(15, 8, 'P0002', 95000, 1),
(16, 8, 'P0001', 87000, 1),
(17, 10, 'P0003', 10000, 1),
(18, 9, 'P0003', 10000, 1),
(19, 11, 'P0003', 10000, 1),
(20, 11, 'P0003', 10000, 1),
(21, 12, 'P0001', 87000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `makanan`
--

CREATE TABLE `makanan` (
  `id_makanan` char(5) NOT NULL,
  `nama_makanan` varchar(50) NOT NULL,
  `harga` mediumint(9) NOT NULL,
  `gambar` varchar(50) NOT NULL,
  `status` enum('Aktif','Tidak Aktif') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `makanan`
--

INSERT INTO `makanan` (`id_makanan`, `nama_makanan`, `harga`, `gambar`, `status`) VALUES
('P0001', 'American Classic Cheeseburger', 87000, 'American Beef.png', 'Aktif'),
('P0002', 'Grilled Beef Supreme', 95000, 'American Chicken.png', 'Aktif'),
('P0003', 'American Sausage', 10000, 'American Sausage.png', 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `username` varchar(35) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `level` enum('Kasir','Pelayan','Owner','Admin') NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`username`, `nama`, `level`, `password`) VALUES
('arhakim.info@gmail.cok', 'Kentang Goreng', 'Admin', '12345678'),
('arhakim.info@gmail.com', 'Kentang Goreng', 'Kasir', '12345678'),
('arhakim.info@gmail.cop', 'Kentang Goreng', 'Admin', '11345'),
('arhakim.info@gmail.cou', 'Kentang Goreng', '', '11345'),
('arhakim.info@gmail.in', 'AR Hakim', 'Kasir', '12345678'),
('arhakim@students.amikom.ac.id', 'Kentang Goreng', 'Owner', '122345678'),
('arhakim@students.amikom.com', 'Kentang Goreng', 'Admin', '12345678'),
('bukaresep.com@gmail.com', 'Kentang Goreng', 'Kasir', '12345678'),
('coba@gmail.com', 'coba lagi', 'Kasir', 'coba123'),
('cobalagi@gmail.com', 'coba lagi', 'Kasir', 'coba123'),
('stevi.ema@amikom.ac.id', 'Stevi Ema Wijayanto', 'Owner', '12345'),
('steviema.wijayanti@gmail.com', 'Stevi', 'Admin', 'qwerty'),
('tes@gmail.com', 'tes', 'Admin', 'tes123');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `no_transaksi` int(10) NOT NULL,
  `tanggal` date NOT NULL,
  `username` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`no_transaksi`, `tanggal`, `username`) VALUES
(1, '2022-01-10', 'stevi.ema@amikom.ac.id'),
(2, '2022-01-11', 'stevi.ema@amikom.ac.id'),
(3, '2022-01-11', 'stevi.ema@amikom.ac.id'),
(4, '2022-01-11', 'stevi.ema@amikom.ac.id'),
(5, '2022-01-11', 'stevi.ema@amikom.ac.id'),
(6, '2022-01-21', ''),
(7, '2022-01-21', ''),
(8, '2022-01-21', ''),
(9, '2022-01-21', ''),
(10, '2022-01-21', ''),
(11, '2022-01-21', ''),
(12, '2022-01-21', 'arhakim.info@gmail.in');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD PRIMARY KEY (`nomor`),
  ADD KEY `no_transaksi` (`no_transaksi`,`id_makanan`),
  ADD KEY `id_makanan` (`id_makanan`);

--
-- Indexes for table `makanan`
--
ALTER TABLE `makanan`
  ADD PRIMARY KEY (`id_makanan`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`no_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  MODIFY `nomor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `no_transaksi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
