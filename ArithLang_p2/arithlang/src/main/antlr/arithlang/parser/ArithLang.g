grammar ArithLang;

 // Grammar of this Programming Language
 //  - grammar rules start with lowercase
 program returns [Program ast] :
		e=exp { $ast = new Program($e.ast); }
		;

 exp returns [Exp ast]:
		n=numexp { $ast = $n.ast; }
        | a=addexp { $ast = $a.ast; }
        | m=multexp { $ast = $m.ast; }
        ;

 numexp returns [NumExp ast]:
 		n0=Num { $ast = new NumExp($n0.text); }
  		;

 addexp returns [AddExp ast]
        :
 		'(' '+'
 		    e1 =exp
 		    e2 =exp
 		')' { $ast = new AddExp($e1.ast, $e2.ast); }
 		;

 multexp returns [MultExp ast]
        :
 		'(' '*'
 		    e1 =exp
            e2 =exp
 		')' { $ast = new MultExp($e1.ast, $e2.ast); }
 		;

 // Lexical Specification of this Programming Language
 //  - lexical specification rules start with uppercase

 Define : 'define' ;
 Dot : '.' ;

 Number : 'e' | 'o' | 'u' ;
 WS  :  [ \t\r\n\u000C]+ -> skip;
