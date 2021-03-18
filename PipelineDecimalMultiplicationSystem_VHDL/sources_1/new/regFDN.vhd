----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/20/2020 05:57:40 PM
-- Design Name: 
-- Module Name: regFDN - Behavioral
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

entity regFDN is
Generic (n: natural);
Port ( D: in STD_LOGIC_VECTOR(n-1 downto 0);
       Q: out STD_LOGIC_VECTOR(n-1 downto 0);
       Clk, CE, RST: in STD_LOGIC );
end regFDN;

architecture Behavioral of regFDN is

begin

process (Clk)
    begin
        if(rising_edge(Clk)) then
            if(RST='1') then
                 Q<= (others => '0');
            elsif( CE='1') then
                Q<=D;
            end if;
        end if;
end process;

end Behavioral;
