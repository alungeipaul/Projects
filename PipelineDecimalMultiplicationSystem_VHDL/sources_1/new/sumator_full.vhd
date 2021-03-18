----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/04/2020 08:10:21 PM
-- Design Name: 
-- Module Name: sumator_full - Behavioral
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity sumator_full is
    Port ( a : in  STD_LOGIC;
       b : in  STD_LOGIC;
       c : in  STD_LOGIC;
       sum : out  STD_LOGIC;
       cout : out  STD_LOGIC);
end sumator_full;

architecture Behavioral of sumator_full is

begin
sum <= (a xor b xor c);
cout <= (a and b) or (c and b) or (a and c);


end Behavioral;
