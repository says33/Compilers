package mx.ipn.proyectoFinal.utils;

import java_cup.runtime.Symbol;
%%
%public
%class Lexer
%cup
L = [A-Z]
l = [a-z]
LC = [a-zA-Z]
D = [0-9]
%%
{L}({l}+) {return new Symbol(sym.TABLE,yytext()); }
{l}({LC}+) {return new Symbol(sym.CAMPO,yytext()); }
"\u03C0" {return new Symbol(sym.PROYECCION); }
"\u03C3" {return new Symbol(sym.SELECCION); }
"\u22C8" {return new Symbol(sym.JOIN); }
"\u0425" {return new Symbol(sym.CARTESIANO);}
"." {return new Symbol(sym.PUNTO); }
"(" {return new Symbol(sym.PAR_I); }
")" {return new Symbol(sym.PAR_D); }
"," {return new Symbol(sym.COMA); }
">" {return new Symbol(sym.GT); }
">=" {return new Symbol(sym.GE); }
"<" {return new Symbol(sym.LT); }
"<=" {return new Symbol(sym.LE); }
"=" {return new Symbol(sym.EQ); }
"like"|"LIKE" {return new Symbol(sym.LIKE); }
"OR"|"or" {return new Symbol(sym.OR); }
"AND"|"and" {return new Symbol(sym.AND); }
"NOT"|"not" {return new Symbol(sym.NOT); }
"!=" {return new Symbol(sym.NE); }
{D}+(\.{D}+)? {return new Symbol(sym.NUMBER,yytext()); }
[ \t\r\f] {}
. { System.err.println("Illegal character: " + yytext()); }