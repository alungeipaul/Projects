----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/20/2020 04:43:05 PM
-- Design Name: 
-- Module Name: matrix_mul - Behavioral
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

entity matrix_mul is
   Port ( 
    Clk, Rst, Load: in STD_LOGIC;
    X : in STD_LOGIC_VECTOR (7 downto 0);
    Y : in STD_LOGIC_VECTOR (7 downto 0);
    P : out STD_LOGIC_VECTOR (15 downto 0));
end matrix_mul;

architecture Behavioral of matrix_mul is

    type partialProduct is array (0 to 7) of STD_LOGIC_VECTOR(7 downto 0);
	type sumArray is array (0 to 7) of STD_LOGIC_VECTOR(8 downto 0);
	type carryArray is array (0 to 6) of STD_LOGIC_VECTOR(8 downto 0);
	
	signal PP, Q: partialProduct := (others => (others => '0'));
	signal S: sumArray := (others => (others => '0'));
	signal T: carryArray := (others => (others => '0'));

begin
    
PP_generatorI: for i in 0 to 7 generate
      PP_generatorJ: for j in 0 to 7 generate
                PP(i)(j) <= X(j) and Y(i);  
      end generate;
end generate;

reg: for i in 0 to 7 generate
    regP: entity WORK.regFDN generic map(n=>8) port map (
            D => PP(i), 
            Q => Q(i), 
            Clk => Clk, 
            CE => Load, 
            Rst => Rst);
end generate;

S_generator0: for i in 0 to 7 generate
        S(0)(i) <= PP(0)(i);
end generate;

sum_generator1: for i in 0 to 6 generate
    sum_generator2: for j in 0 to 7 generate
            sumatorElementar: entity WORK.sumator_1bit port map(
                    X => Q(i+1)(j),
                    Y => S(i)(j+1),
                    Tin => T(i)(j),
                    S => S(i+1)(j),
                    Tout => T(i)(j+1));
     end generate;
end generate;

sum_generator3: for i in 1 to 7 generate
            S(i)(8) <= T(i-1)(8);
end generate;

P_generator1: for i in 0 to 7 generate
            P(i) <= S(i)(0);
end generate;

P_generator2: for i in 0 to 7 generate
            P(i+8) <= S(7)(i+1);
end generate;

end Behavioral;
