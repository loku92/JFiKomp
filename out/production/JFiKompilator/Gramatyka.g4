grammar Gramatyka;

prog: ( stat? NEWLINE  )*
    ;

stat: function
    | inside
   ;


expr0:  expr1			#single0
      | expr1 (ADD | MINUS) expr1		#add
;

expr1:  expr2			      #single1
      | expr2 (MULT | DIV ) expr2	#mult
;

expr2:   value			#valuue
       | '(' expr0 ')'		#par
;

value: ID
       | INT
       | REAL
   ;

function: 'function' call OPEN inside* CLOSE
;

call: ID args
;

args: '(' ((value | ID)(restvalue)*)* ')'
;

restvalue: ','(value|ID)
;

inside:	PRINT value		#print
    | ID EQUALS expr0       #expression
	| ID EQUALS value		#assign
	| READ ID   		#read
	| loop              #lop
	|cond                 #iff
   ;

cond: OIF value (lt|gt|eq) CIF (NEWLINE)* OPEN NEWLINE* inside+ NEWLINE* CLOSE
;

lt: '<' value
 ;
 
gt: '>' value
     ;
     
eq: '==' value
 ;

loop: 'loop('value ',' value ')' NEWLINE* OPEN NEWLINE* inside NEWLINE* CLOSE
 ;
 

PRINT:	'print'
   ;

READ:	'read'
   ;

ID:   ('a'..'z'|'A'..'Z')+
   ;

EQUALS: '='
    ;

INT:   '0'..'9'+
    ;

REAL: '0'..'9'+'.''0'..'9'+
       ;

ADD: '+'
    ;

MINUS: '-'
    ;

MULT: '*'
    ;

DIV: '/'
    ;

NEWLINE: '\r'?'n'
;

WS:   (' '|'\t' |'\r'? '\n' )+ -> skip
    ;

OIF: '<<'
    ;

CIF: '>>'
    ;

OPEN: '['
    ;

CLOSE: ']'
    ;
