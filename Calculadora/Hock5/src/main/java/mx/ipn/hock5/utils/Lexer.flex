package mx.ipn.hock5.utils;

import java_cup.runtime.Symbol;
%%
%public
%class Lexico
%cup
D = [0-9]
%%
"+" {return new Symbol(sym.PLUS); }
"-" {return new Symbol(sym.MINUS); }
"*" {return new Symbol(sym.TIMES); }
"/" {return new Symbol(sym.DIV); }
"(" {return new Symbol(sym.LPAREN); }
")" {return new Symbol(sym.RPAREN); }
{D}+(\.{D}+)? { return new Symbol(sym.NUMBER, new Double(yytext())); }
[ \t\r\f\n] {}
. { System.err.println("Illegal character: " + yytext()); }
