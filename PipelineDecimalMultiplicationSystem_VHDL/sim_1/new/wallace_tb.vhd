----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/04/2020 07:59:08 PM
-- Design Name: 
-- Module Name: wallace_tb - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity wallace_tb is
--  Port ( );
end wallace_tb;

architecture Behavioral of wallace_tb is

signal X : STD_LOGIC_VECTOR(7 downto 0):=(others=>'0');
signal Y : STD_LOGIC_VECTOR(7 downto 0):=(others=>'0'); 
signal P : STD_LOGIC_VECTOR(15 downto 0):=(others=>'0');
signal Clk, Rst, Start: STD_LOGIC:='0';
constant clockPeriod : time := 20 ns;

begin

clk_process: process
begin
    Clk <= '0';
    wait for clockPeriod/2;
    Clk <= '1';
    wait for clockPeriod/2;
end process;

    DUT: entity WORK.wallace_mul port map ( 
            a => x,
            b => y,
            Prod => P,
            Clk => Clk,
            Rst =>Rst,
            Load => Start);

testBench: process
begin
    X <= CONV_STD_LOGIC_VECTOR(3, 8);
    Y <= CONV_STD_LOGIC_VECTOR(4, 8);
  wait for clockPeriod;
        Rst<='1';
        wait for clockPeriod;
        Rst<='0';
        wait for clockPeriod;
        Start<='1';
        wait for clockPeriod;
    wait;
    
end process;
end Behavioral;
