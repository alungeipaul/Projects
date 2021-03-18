----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/20/2020 04:44:53 PM
-- Design Name: 
-- Module Name: matrix_mul_tb - Behavioral
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

entity matrix_mul_tb is
--  Port ( );
end matrix_mul_tb;

architecture Behavioral of matrix_mul_tb is

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

    DUT: entity WORK.matrix_mul port map ( 
            Clk => Clk,
            Rst => '0',
            Load => Start,
            X => X,
            Y => Y,
            P => P);
            
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

--    X <= CONV_STD_LOGIC_VECTOR(255, 8);
--    Y <= CONV_STD_LOGIC_VECTOR(2, 8);
--    wait for clockPeriod;
--    Rst<='1';
--    wait for clockPeriod;
--    Rst<='0';
--    wait for clockPeriod;
--    Start<='1';
--    wait for clockPeriod;
 
--    X <= CONV_STD_LOGIC_VECTOR(255, 8);
--    Y <= CONV_STD_LOGIC_VECTOR(255, 8);
--    wait for clockPeriod;
--    Rst<='1';
--    wait for clockPeriod;
--    Rst<='0';
--    wait for clockPeriod;
--    Start<='1';
--    wait for clockPeriod;

end process;
end Behavioral;
