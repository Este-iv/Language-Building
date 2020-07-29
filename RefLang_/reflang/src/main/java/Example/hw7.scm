# a

$ (deref (ref 342))
342

$ (free (ref 342))
no output.

(let ((loc (ref 342))) (set! loc 541))
541

(let ((loc (ref 540))) (set! loc (deref loc)))
540

# b
$ (define a (ref 0))
$ a
loc:0
$ (let ( (x a)) a)
loc:0

# c
# the given code.
(define pairNode (lambda (fst snd) (lambda (op) (if op fst snd))))
(define node (lambda (x) (pairNode x (ref (list)))))
( define getFst (lambda (p) (p #t )))
( define getSnd (lambda (p) (p #f )))


# i


(define val (lambda (p) (p 1)))
(define right (lambda (p) (p 2 )))
(define left (lambda (p) (p 3)))

# ii
(define add (lambda (p which c)
    (if which c
         (if (null? (deref (left p)
              (set! (left p) c)
              (add (deref (left p)) which c ))
         (if (null? (deref (right p))))
             (set! (right p) c)
             (add (deref (right p)) which c))))))



