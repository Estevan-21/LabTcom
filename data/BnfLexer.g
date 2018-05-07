lexer grammar BnfLexer;

// A partir de este archivo, mediante ANTLR, se genera el fuente
// BnfLexer.java
// Autor: R. Lopez

@header{
package tcom.bnf;
}

NONTERMINAL: '<'[a-zA-Z]('a'..'z'|'A'..'Z'|'-'|'0'..'9'|' ')*'>';
TERMINAL : '\'' (~['"] | DQSTR)* '\'';
LPAR:'(';
RPAR:')';
LCURL:'{';
RCURL:'}';
LBRACK:'[';
RBRACK:']';
BAR:'|';
BNFEQ:'::=';
SEMICOLON:';';
WS: [ \n\t\r]+ -> skip;

fragment
DQSTR : '"'  (~['"] | TERMINAL)* '"'; 
