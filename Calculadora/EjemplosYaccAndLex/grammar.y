%{
    #include <stdio.h>
    #include <string.h>
    
    int yylex();
    int yyerror(const char*);
    int yywrap();  
%}

%token NOUN PRONOUN VERB ADVERB ADJECTIVE PREPOSITION CONJUNCTION

%%
sentence: subject VERB object { printf("Sentence is valid.\n");};

subject:    NOUN
	|   PRONOUN;

object:    NOUN;
%%

int yyerror(const char *s){
    fprintf(stderr,"%s\n",s);
    return 0;
}

int yywrap(){
    return 0;
}

int main(){
    yyparse();
    return 1;
}
