%%
[\t ]+;
is |
am |
are |
were |
was |
be |
being |
been |
do |
does |
did |
will |
would |
should |
can |
could |
has |
have |
had |
go { printf("%s: is a verb\n",yytext);}

very |
simply |
gently |
quietly |
calmly |
angrily   { printf("%s: is an adverb\n",yytext);}

to |
from |
behind |
above |
between |
below   { printf("%s: is a preposition\n",yytext);}
 
if |
then |
and |
but |
or   { printf("%s: is a conjuntion \n",yytext); }

their |
my |
yout |
his |
her |
its   {printf("%s: is an adjective ",yytext);}

I |
you |
he |
she |
we |
they {printf("%s: is a pronoun\n",yytext);}

[a-zA-Z]+ { printf("%s: is not a verb\n",yytext); }

.|\n { ECHO; }
%%

int main(){
    yylex();
    return 0;
}
