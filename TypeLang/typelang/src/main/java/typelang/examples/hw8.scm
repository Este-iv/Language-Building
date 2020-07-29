# This is going over the examples provided in the PDF as well as some that
# have been made up for test reasons.
1.
    a.
        $ (ref : num 45)
        loc:0
        $ (ref : bool 45)
        Type error: The Ref expression expect type bool found number in (ref 45.0)

        $ (ref : num 13)
        loc:1
        $ (ref : bool 13)
        Type error: The Ref expression expect type bool found number in (ref 13.0)
    b.
        $ (set! (ref : num 0) #t)
        Type error:  The inner type of the reference type is number the rhs type is  in (set! (ref 0.0) #t)
        $ (set! (ref : bool #t)(list : num 1 2 3 4 5 6 ))
        Type error:  The inner type of the reference type is bool the rhs type is  in (set! (ref #t) (list 1.0 2.0 3.0 4.0 5.0 6.0 ))

        $ (set! (ref : num 1) #t)
        Type error: The inner type of the reference type is number the rhs type is bool in (set! (ref 1.0) #t)

2.
    a.
        $ (cdr 2)
        Type error: The cdr expect an expression of type Pair, found number in (cdr 2.0)
        $ (cdr 4)
        Type error: The cdr expect an expression of type Pair, found number in (cdr 4.0)
        $ (cdr (cdr 2))
        Type error: The cdr expect an expression of type Pair, found number in (cdr 2.0)

    b.
          $ (list : bool 1 2 3  4  5 6 7)
          Type error: The 0 expression should have type bool found number in (list 1.0 2.0 3.0 4.0 5.0 6.0 7.0 )
          $ (list : num  1 2 3 4 5 #t 6 7 8 )
          Type error: The 5 expression should have type number found bool in (list 1.0 2.0 3.0 4.0 5.0 #t 6.0 7.0 8.0 )

3.
        $ (+ #t 6)
        Type error: Expected number found bool in (+ #t 6.0 )
        $ (+ 5 6 7 #t 56)
        Type error: Expected number found bool in (+ 5.0 6.0 7.0 #t 56.0 )
        $ (* 45.0 #t)
        Type error: Expected number found bool in (* 45.0 #t )
        $ (/ (list : num 3 4 5 6 7) 45)
        Type error: Expected number found List<number> in (/ (list 3.0 4.0 5.0 6.0 7.0 ) 45.0 )

        $ (+ #f 6)
        Type error: Expected number found bool in (+ #f 6.0 )
        $ (+ 7 8 9 #f 55)
        Type error: Expected number found bool in (+ 7.0 8.0 9.0 #f 55.0 )
        $ (* 13.3 #f)
        Type error: Expected number found bool in (* 13.3 #f )

4.
    $ (< #t #t)
    Type error: The first argument of a binary expression should be num Type, found bool in (< #t #t)
    $ (> (list: num 1 2 3 4 5 13) 44)
    Type error: The first argument of a binary expression should be num Type, found List<number> in (> (list 1.0 2.0 3.0 4.0 5.0 13.0 ) 4.0)

5.
    $ (if 5 56 67)
    Type error: The condition should have boolean type, found number in (if 5.0 56.0 67.0)
    $ (if #t #t 56)
    Type error: The then and else expressions should have the same type, then has type bool else has type number in (if #t #t 56.0)



6.
    $ (let((x: num34)(y: num45)(cond: bool#t))(ifx(+xy)(/xy)))
    line 1:9 no viable alternative at input 'num34'
    line 1:19 no viable alternative at input 'num45'
    line 1:48 extraneous input ')' expecting {'-', '(', '#t', '#f', Number, Identifier, StrLiteral}
    line 1:53 mismatched input ')' expecting {'-', '(', '#t', '#f', Number, Identifier, StrLiteral}
    Type error: Variable num34 has not been declared in num34

    $ (let ((x: num #t) (y: bool 8)) x)
    Type error: The declared type of the 0 let variable and the actual type mismatch, expect number found bool in (let ( (x #t) (y 8.0)) x )

    (let ((x: num 13) (y: num 45) (cond: bool #f)) (if x (* xy) (- xy)))
    Type error: The condition should have boolean type, found number in (if x (* x y ) (- x y )

7.
    $(define add: (num num num − > num) (lambda (x: num y: num z: num) (+ x (+ y z))))
    $ (add 5 56 #t)
    Type error: The expected type of the third actual parameter is number, found bool in (add 5 56 #t)
    $ (3 4)
    Type error: Expect a function type in the call expression, found number in (3 4)