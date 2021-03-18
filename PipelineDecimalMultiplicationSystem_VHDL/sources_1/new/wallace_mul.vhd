----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/04/2020 07:40:22 PM
-- Design Name: 
-- Module Name: wallace_mul - Behavioral
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

entity wallace_mul is
	Port ( A : in  STD_LOGIC_VECTOR (7 downto 0);
       B : in  STD_LOGIC_VECTOR (7 downto 0);
       prod : out  STD_LOGIC_VECTOR (15 downto 0);
       Clk, Rst, Load: in STD_LOGIC);
end wallace_mul;

architecture Behavioral of wallace_mul is

component sumator_full is
    Port ( a : in  STD_LOGIC;
       b : in  STD_LOGIC;
       c : in  STD_LOGIC;
       sum : out  STD_LOGIC;
       cout : out  STD_LOGIC);
end component;

component semi_sumator is
    Port ( a : in  STD_LOGIC;
       b : in  STD_LOGIC;
       sum : out  STD_LOGIC;
       cout : out  STD_LOGIC);
end component;

type matrix8x8 is array(0 to 7) of STD_LOGIC_VECTOR(7 downto 0);
signal PP,outPP: matrix8x8 := (others => (others => '0'));
signal S, C: STD_LOGIC_VECTOR(68 downto 1) := (others => '0');
signal outS, outC: STD_LOGIC_VECTOR(68 downto 1):= (others => '0');


begin

PP_generatorI: for i in 0 to 7 generate
      PP_generatorJ: for j in 0 to 7 generate
                PP(i)(j)<= A(j) and B(i);  
      end generate;
end generate;

reg: for i in 0 to 7 generate
    regP: entity WORK.regFDN generic map(n=>8) port map (D=>PP(i), Q=>outPP(i), Clk=>Clk, CE=>Load, Rst=>Rst);
end generate;

regS0: entity WORK.regFDN generic map(n=>21) port map (D=>S(21 downto 1), Q=>outS(21 downto 1), Clk=>Clk, CE=>Load, Rst=>Rst);
regC0: entity WORK.regFDN generic map(n=>21) port map (D=>C(21 downto 1), Q=>outC(21 downto 1), Clk=>Clk, CE=>Load, Rst=>Rst);

regS1: entity WORK.regFDN generic map(n=>15) port map (D=>S(36 downto 22), Q=>outS(36 downto 22), Clk=>Clk, CE=>Load, Rst=>Rst);
regC1: entity WORK.regFDN generic map(n=>15) port map (D=>C(36 downto 22), Q=>outC(36 downto 22), Clk=>Clk, CE=>Load, Rst=>Rst);

regS2: entity WORK.regFDN generic map(n=>12) port map (D=>S(48 downto 37), Q=>outS(48 downto 37), Clk=>Clk, CE=>Load, Rst=>Rst);
regC2: entity WORK.regFDN generic map(n=>12) port map (D=>C(48 downto 37), Q=>outC(48 downto 37), Clk=>Clk, CE=>Load, Rst=>Rst);

regS3: entity WORK.regFDN generic map(n=>11) port map (D=>S(59 downto 49), Q=>outS(59 downto 49), Clk=>Clk, CE=>Load, Rst=>Rst);
regC3: entity WORK.regFDN generic map(n=>11) port map (D=>C(59 downto 49), Q=>outC(59 downto 49), Clk=>Clk, CE=>Load, Rst=>Rst);

regS4: entity WORK.regFDN generic map(n=>9) port map (D=>S(68 downto 60), Q=>outS(68 downto 60), Clk=>Clk, CE=>Load, Rst=>Rst);
regC4: entity WORK.regFDN generic map(n=>9) port map (D=>C(68 downto 60), Q=>outC(68 downto 60), Clk=>Clk, CE=>Load, Rst=>Rst);


--stage zero

ha00: semi_sumator port map(outPP(0)(1),outPP(1)(0),S(1),C(1));
fa00: sumator_full port map(outPP(2)(0),outPP(0)(2),outPP(1)(1),S(2),C(2));
fa01: sumator_full port map(outPP(3)(0),outPP(2)(1),outPP(1)(2),S(3),C(3));
fa02: sumator_full port map(outPP(4)(0),outPP(3)(1),outPP(2)(2),S(4),C(4));
ha01: semi_sumator port map(outPP(1)(3),outPP(0)(4),S(5),C(5));
fa03: sumator_full port map(outPP(5)(0),outPP(4)(1),outPP(3)(2),S(6),C(6));
fa04: sumator_full port map(outPP(2)(3),outPP(1)(4),outPP(0)(5),S(7),C(7));
fa05: sumator_full port map(outPP(6)(0),outPP(5)(1),outPP(4)(2),S(8),C(8));
fa06: sumator_full port map(outPP(3)(3),outPP(2)(4),outPP(1)(5),S(9),C(9));
fa07: sumator_full port map(outPP(7)(0),outPP(6)(1),outPP(5)(2),S(10),C(10));
fa08: sumator_full port map(outPP(4)(3),outPP(3)(4),outPP(2)(5),S(11),C(11));
ha02: semi_sumator port map(outPP(1)(6),outPP(0)(7),S(12),C(12));
fa09: sumator_full port map(outPP(7)(1),outPP(6)(2),outPP(5)(3),S(13),C(13));
fa90: sumator_full port map(outPP(4)(4),outPP(3)(5),outPP(2)(6),S(14),C(14));
fa31: sumator_full port map(outPP(7)(2),outPP(6)(3),outPP(5)(4),S(15),C(15));
fa32: sumator_full port map(outPP(4)(5),outPP(3)(6),outPP(2)(7),S(16),C(16));
fa33: sumator_full port map(outPP(7)(3),outPP(6)(4),outPP(5)(5),S(17),C(17));
ha03: semi_sumator port map(outPP(4)(6),outPP(3)(7),S(18),C(18));
fa34: sumator_full port map(outPP(7)(4),outPP(6)(5),outPP(5)(6),S(19),C(19));
fa35: sumator_full port map(outPP(7)(5),outPP(6)(6),outPP(5)(7),S(20),C(20));
ha04: semi_sumator port map(outPP(7)(6),outPP(6)(7),S(21),C(21));


--stage one
ha10: semi_sumator port map(outS(2),outC(1),S(22),C(22));
fa10: sumator_full port map(outPP(0)(3),outC(2),outS(3),S(23),C(23));
fa11: sumator_full port map(outS(4),outS(5),outC(3),S(24),C(24));
fa12: sumator_full port map(outS(6),outS(7),outC(4),S(25),C(25));
fa13: sumator_full port map(outS(8),outS(9),outPP(0)(6),S(26),C(26));
ha11: semi_sumator port map(outC(6),outC(7),S(27),C(27));
fa14: sumator_full port map(outS(10),outS(11),outS(12),S(28),C(28));
ha12: semi_sumator port map(outC(8),outC(9),S(29),C(29));
fa15: sumator_full port map(outS(13),outS(14),outPP(1)(7),S(30),C(30));
fa16: sumator_full port map(outC(10),outC(11),outC(12),S(31),C(31));
fa17: sumator_full port map(outS(15),outS(16),outC(13),S(32),C(32));
fa18: sumator_full port map(outS(17),outS(18),outC(15),S(33),C(33));
fa19: sumator_full port map(outS(19),outC(17),outC(18),S(34),C(34));
ha13: semi_sumator port map(outS(20),outC(19),S(35),C(35));
ha14: semi_sumator port map(outS(21),outC(20),S(36),C(36));



--stage two
ha40: semi_sumator port map(outS(23),outC(22),S(37),C(37));
ha41: semi_sumator port map(outC(23),outS(24),S(38),C(38));
fa40: sumator_full port map(outC(24),outS(25),outC(5),S(39),C(39));
fa41: sumator_full port map(outC(25),outS(26),outS(27),S(40),C(40));
fa42: sumator_full port map(outC(26),outC(27),outS(28),S(41),C(41));
fa43: sumator_full port map(outC(28),outC(29),outS(30),S(42),C(42));
fa44: sumator_full port map(outC(30),outC(31),outS(32),S(43),C(43));
fa45: sumator_full port map(outC(32),outC(16),outS(33),S(44),C(44));
fa46: sumator_full port map(outC(33),outPP(4)(7),outS(34),S(45),C(45));
ha42: semi_sumator port map(outS(35),outC(34),S(46),C(46));
ha43: semi_sumator port map(outC(35),outS(36),S(47),C(47));
fa47: sumator_full port map(outPP(7)(7),outC(21),outC(36),S(48),C(48));



--stage three
ha50: semi_sumator port map(outC(37),outS(38),S(49),C(49));
fa50: sumator_full port map(outS(39),outC(38),outC(49),S(50),C(50));
fa51: sumator_full port map(outS(40),outC(39),outC(50),S(51),C(51));
fa52: sumator_full port map(outC(40),outS(41),outS(29),S(52),C(52));
fa53: sumator_full port map(outC(41),outS(31),outS(42),S(53),C(53));
fa54: sumator_full port map(outC(14),outC(42),outS(43),S(54),C(54));
fa55: sumator_full port map(outS(44),outC(43),outC(54),S(55),C(55));
fa56: sumator_full port map(outC(44),outS(45),outC(55),S(56),C(56));
fa57: sumator_full port map(outS(46),outC(45),outC(56),S(57),C(57));
fa58: sumator_full port map(outC(46),outS(47),outC(57),S(58),C(58));
fa59: sumator_full port map(outS(48),outC(47),outC(58),S(59),C(59));


--stage four
ha70: semi_sumator port map(outC(51),outS(52),S(60),C(60));
fa70: sumator_full port map(outC(52),outS(53),outC(60),S(61),C(61));
fa71: sumator_full port map(outC(53),outS(54),outC(61),S(62),C(62));
ha71: semi_sumator port map(outS(55),outC(62),S(63),C(63));
ha72: semi_sumator port map(outS(56),outC(63),S(64),C(64));
ha73: semi_sumator port map(outS(57),outC(64),S(65),C(65));
ha74: semi_sumator port map(outS(58),outC(65),S(66),C(66));
ha75: semi_sumator port map(outS(59),outC(66),S(67),C(67));
fa81: sumator_full port map(outC(48),outC(59),outC(67),S(68),C(68));


prod(0) <= outPP(0)(0);
prod(1) <= outS(01);
prod(2) <= outS(22);
prod(3) <= outS(37);
prod(4) <= outS(49);
prod(5) <= outS(50);
prod(6) <= outS(51);
prod(7) <= outS(60);
prod(8) <= outS(61);
prod(9) <= outS(62);
prod(10) <= outS(63);
prod(11) <= outS(64);
prod(12) <= outS(65);
prod(13) <= outS(66);
prod(14) <= outS(67);
prod(15) <= outS(68) or outC(68);

end Behavioral;
