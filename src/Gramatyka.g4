grammar Gramatyka;

prog: ( stat? END comment?  )*
    ;

stat: function comment? #foo
    | inside   comment? #operation
   ;

expr:   expr ADD expr		#add
      | expr MINUS expr		#sub
      | expr MULT  expr	    #mult
      | expr  DIV  expr	    #div
      | value               #valuue
      | TOINT expr	        #toint
      | TOREAL expr		    #toreal
      | '(' expr ')'		#par
;

comment: '#'(value|'-'|'+'|'*'|'/'|'#'|'$')*
;

value: ID   #id
       | INT    #int
       | REAL   #real
   ;

function: 'function' call OPEN (inside comment?| comment )+ CLOSE
;

call: ID args
;

args: '(' ((value | ID)(restvalue)*)* ')'
;

restvalue: ','(value|ID)
;

inif:(inside comment?| comment )+
;

inside:	PRINT ID		#print
    | ID '=' expr       #assign
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

loop: 'loop('value ',' value ')' NEWLINE* OPEN NEWLINE* (inside comment?| comment )+ NEWLINE* CLOSE
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
