package mx.ipn.proyectoFinal.utils;

import java_cup.runtime.Symbol;
%%
%public
%class Lexer
%cup
L = [A-Z]
l = [a-z]
%%
{L}({l}+) {return new Symbol(sym.TABLE,yytext()); }
