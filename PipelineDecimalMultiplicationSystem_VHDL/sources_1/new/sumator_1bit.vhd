----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/20/2020 04:55:21 PM
-- Design Name: 
-- Module Name: sumator_1bit - Behavioral
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

entity sumator_1bit is
    Port ( 
        X : in STD_LOGIC;
        Y : in STD_LOGIC;
        Tin : in STD_LOGIC;
        S : out STD_LOGIC;
        Tout : out STD_LOGIC);
end sumator_1bit;

architecture Behavioral of sumator_1bit is

begin

	S <= X xor Y xor Tin;
    Tout <= (X and Y) or (X and Tin) or (Y and Tin);

end Behavioral;
