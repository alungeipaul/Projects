----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/04/2020 07:37:43 PM
-- Design Name: 
-- Module Name: semi_sumator - Behavioral
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

entity semi_sumator is
Port ( a : in  STD_LOGIC;
       b : in  STD_LOGIC;
       sum : out  STD_LOGIC;
       cout : out  STD_LOGIC);
end semi_sumator;

architecture Behavioral of semi_sumator is

begin

sum <= a xor b;
cout <= a and b;

end Behavioral;
