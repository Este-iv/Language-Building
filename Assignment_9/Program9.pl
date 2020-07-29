%% Question 1. 

mystery([],L2,L2).
	mystery([H|Tail],L2,[R|RTail]):-
	H=R,
	mystery(Tail,L2,RTail). 
	
%% Question 2a:

fib(0,0).
fib(1,1).
fib(X,R):-
	X > 1, 
	X2 is X - 2, fib(X2,Y2),
	X1 is X - 1, fib(X1,Y1),
	R is Y1 + Y2.	

%% Question 2b:

%% Found the Method is_list() on the Swi-prolog page, after some googling, 
%% For getting List Len !=0. To run the Program with at least 1 value. 
%% Link: https://www.swi-prolog.org/pldoc/doc_for?object=is_list/1
nestRev(L,R):- rev(L,[],R).
rev([],L,L).
rev([H|T],L,R):-
	(is_list(H),
        !,
        rev(H,[],X),
        rev(T,[X|L],R)
    ;
        rev(T,[H|L],R)
    ).


%% Question 3a.
%% B, C are in the output.txt File.  
%% Question 3
sentence(A):- gram(A).
sentence(A):- chkf(A).

chkf([I1,T3,E2,T4,TN1,S1]):-
    iff(I1),tvar(T3),eoper(E2),tvar(T4) ,thn(TN1),sentence(S1).

chkf([I2,T5,E3,T6,TN2,S2,E1,S3]):-
    iff(I2),tvar(T5),eoper(E3),tvar(T6) ,thn(TN2),sentence(S2),lse(E1),sentence(S3).

gram([T1,N1,T2]):-tvar(T1),noper(N1),tvar(T2).

iff(I):- (I = 'if').
lse(E):- (E = 'else').
thn(T):- (T = 'then').

tvar(A):- (A = x).
tvar(A):- (A = y).
tvar(A):- (A = z).
tvar(A):- (A = 0).
tvar(A):- (A = 1).

noper(A):- (A = '-').	%% N operator Sub 
noper(A):- (A = '=').	%% N operator Equal
noper(A):- (A = '+').	%% N operator Add
eoper(A):- (A = '<').	%% E operator Less
eoper(A):- (A = '>').	%% E operator Greater


%% Question 4. 
%% Given the facts to the following, who drinks water and who owns the zebra?
	%% Hos = HomeOwners. 

	zebra(Owner):-
		houses(Hos),
		person(()).

	water(Drink):-
		houses(Hos),
		person(()).
	houses(Hos):-
		%   Following the given rules. 
		%	houses, each of a different color and inhabited by men of different nationalities,
		%	with different pets, drinks, and cigarettes.
		%	h(nationalities,pets,Smoke,Drink,color) 
		% 	^ hs is for house.
		length(Hos,5), %% numbers of house. 
		person(h(eng,_,_,_,red), Hos),

		
