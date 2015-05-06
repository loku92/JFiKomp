grammar Gramatyka;

prog: ( stat? END )*
    ;

stat: function #foo
    | inside    #operation
   ;

expr0:  expr1			#single0
      | expr1 ADD expr1		#add
      | expr1 MINUS expr1		#sub
;

expr1:  expr2			      #single1
      | expr2 MULT  expr2	#mult
      | expr2  DIV  expr2	#div
;

expr2:   value			#valuue
       | TOINT expr2		#toint
       | TOREAL expr2		#toreal
       | '(' expr0 ')'		#par
;

value: ID   #id
       | INT    #int
       | REAL   #real
   ;

function: 'function' call OPEN inside+ CLOSE
;

call: ID args
;

args: '(' ((value | ID)(restvalue)*)* ')'
;

restvalue: ','(value|ID)
;

inif:(inside)+
;

inside:	PRINT ID		#print
    | ID '=' expr0       #assign
	| READ ID   		#read
	| loop              #lop
	|cond                 #iff
	|call               #kol
   ;

cond: OIF (lt|gt|eq) CIF (NEWLINE)* OPEN NEWLINE* inif NEWLINE* CLOSE
;

lt: ID '<' INT
 ;
 
gt: ID '>' INT
     ;
     
eq: ID '==' INT
 ;

loop: 'loop('value ',' value ')' NEWLINE* OPEN NEWLINE* inside+ NEWLINE* CLOSE
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

TOINT: '(int)'
    ;

TOREAL: '(real)'
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

END: ';'
    ;
