----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 01/03/2021 04:46:02 PM
-- Design Name: 
-- Module Name: modul_principal - Behavioral
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

entity modul_principal is
  Port (
   CLK, RSTNr, RST, START: in STD_LOGIC;
   X, Y: in STD_LOGIC_VECTOR(7 downto 0);
   An, Seg : out STD_LOGIC_VECTOR(7 downto 0);
   B1, B2 : in STD_LOGIC
   );
end modul_principal;

architecture Behavioral of modul_principal is

signal startBtn, rstNrBtn, btn1, btn2 : STD_LOGIC;
signal P1,P2 : STD_LOGIC_VECTOR(15 downto 0); 
signal REZ: STD_LOGIC_VECTOR(31 downto 0) := (others => '0'); 


begin

startB: entity WORK.debouncer port map(
        Clk => Clk, 
        Rst => Rst,
        d_in => Start, 
        q_out => startBtn
);

rstNrB: entity WORK.debouncer port map(
        Clk => Clk, 
        Rst => Rst,
        d_in => RSTNr, 
        q_out => rstNrBtn
);

btn_1: entity WORK.debouncer port map(
        Clk => Clk, 
        Rst => Rst,
        d_in => B1, 
        q_out => btn1
);

btn_2: entity WORK.debouncer port map(
        Clk => Clk, 
        Rst => Rst,
        d_in => B2, 
        q_out => btn2
);

Wallace_Tree: entity WORK.wallace_mul port map(
        A => X,
        B => Y,
        prod => P1,
        Clk => Clk, 
        Rst => rstNrBtn,
        Load => startBtn
);

Matrix_Multiply: entity WORK.matrix_mul port map(
        Clk => Clk, 
        Rst => rstNrBtn,
        Load => startBtn,
        X => X,
        Y => Y,
        P => P2
);

display: entity WORK.displ7seg port map(
        Clk => Clk, 
        Rst => Rst, 
        Data => REZ, 
        An => An, 
        Seg => Seg
);  

afisare_REZ: process(Clk)
begin
    if rising_edge(Clk) then
        if(RST='1') then
            REZ <= (others => '0');
        else
            if(btn1 ='1') then
                REZ(15 downto 0) <= P1;
            elsif(btn2='1') then
                REZ(15 downto 0) <= P2;
            else 
                REZ <= (others => '0');  
            end if;
        end if;
     end if;
end process;      
            
            
end Behavioral;
