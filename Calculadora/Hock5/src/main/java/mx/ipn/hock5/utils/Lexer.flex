package mx.ipn.hock5.utils;

import java_cup.runtime.Symbol;
%%
%public
%class Lexico
%cup
D = [0-9]
L = [a-z]
%%
"+" {return new Symbol(sym.PLUS); }
"-" {return new Symbol(sym.MINUS); }
"*" {return new Symbol(sym.TIMES); }
"=" {return new Symbol(sym.ASSIGN); }
"/" {return new Symbol(sym.DIV); }
"(" {return new Symbol(sym.LPAREN); }
")" {return new Symbol(sym.RPAREN); }
"^" {return new Symbol(sym.POW); }
"print" {return new Symbol(sym.PRINT); }
"while" {return new Symbol(sym.WHILE); }
"if" {return new Symbol(sym.IF); }
"else" {return new Symbol(sym.ELSE); }
"{" {return new Symbol(sym.LKEY); }
"}" {return new Symbol(sym.RKEY); }
">" {return new Symbol(sym.GT); }
">=" {return new Symbol(sym.GE); }
"<" {return new Symbol(sym.LT); }
"<=" {return new Symbol(sym.LE); }
"==" {return new Symbol(sym.EQ); }
"!=" {return new Symbol(sym.NE); }	
[\n] {return new Symbol(sym.SALTOLINEA); }
{L} {return new Symbol(sym.VAR,yytext().charAt(0));}
{D}+(\.{D}+)? { return new Symbol(sym.NUMBER, new Double(yytext())); }
";" {return new Symbol(sym.PUNTOYCOMA); }
[ \t\r\f] {}
. { System.err.println("Illegal character: " + yytext()); }

