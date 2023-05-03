-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2023 at 07:50 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbfood`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_barang`
--

CREATE TABLE `tbl_barang` (
  `id` int(11) NOT NULL,
  `nama_barang` varchar(80) NOT NULL,
  `harga_item` varchar(255) NOT NULL,
  `rating` varchar(80) NOT NULL,
  `gambar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_barang`
--

INSERT INTO `tbl_barang` (`id`, `nama_barang`, `harga_item`, `rating`, `gambar`) VALUES
(1, 'Coca-Cola', '8000', '4.8', 'cocacola.png'),
(3, 'Oreo', '5000', '4.9', 'oreo.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL,
  `nama_user` varchar(80) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `telepon` varchar(255) NOT NULL,
  `username` varchar(80) NOT NULL,
  `password` varchar(80) NOT NULL,
  `foto` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `nama_user`, `alamat`, `telepon`, `username`, `password`, `foto`) VALUES
(1, 'Chandra Ardiansyah', 'Jln. Perjuangan No 62', '089671626761', 'chanzy', '6af1e9302c4ddd46420d59050e12556f', 'chan.jpeg'),
(3, 'Chandra Aja', 'Jln. Slamet Riyadi', '0896716267612', 'ardi', '0264391c340e4d3cbba430cee7836eaf', ''),
(17, 'Chand Ardi', 'Krucuk', '123456', 'ardzz', '369ab721a87362b8c90a0d49c2985d7f', ''),
(32, 'asd', 'asdas', '123', 'asdsa', '26c322652770620e64ac90682eb6504c', '2360181698368693777.jpg'),
(33, 'Chandra Ardiansyah', 'Jln. Perjuangan No 62', '089671626761', 'chanzy', '6af1e9302c4ddd46420d59050e12556f', 'km.png'),
(34, 'Chandra Ardiansyah', 'Jln. Perjuangan No 62', '089671626761', 'chanzys', 'f6560cde4d1e8798f5ee7098d9077a33', 'img001.jpg'),
(35, 'chandra', 'krucuk', '121323', 'chandra', '26c322652770620e64ac90682eb6504c', '2360181698368693777.jpg'),
(36, 'chandra', 'Krucuk', '12232', 'chandra', '26c322652770620e64ac90682eb6504c', '1995231916.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
