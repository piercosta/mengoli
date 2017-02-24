numofsonars(0).
nextsonarid(1).
dmin(70).
drobotdetected(40).
lastdetectedsonar(0).

checkdetected :-
	lastdetectedsonar(N),
	SID is (N + 1),
	lastreceivedsonar(SID),
	sonar(SID, Distance),
	drobotdetected(D),
	Distance < D.

allsonarspresent :-
	numofsonars(N),
	count(N, N).
	
count(SID, R) :-
	SID > 0,
	sonar(SID, _),
	P is (SID - 1),
	count(P, B),
	R is (B + 1).
	
count(SID, R) :-
	SID < 1,
	R is 0.
	
expression(R) :-
	numofsonars(N),
	sum(N, S),
	lastdetectedsonar(O),
	K is O + 1,
	R is S / (N - K + 1).
	
sum(N, R) :-
	lastdetectedsonar(K),
	N > K,
	sonar(N, V),
	P is (N - 1),
	sum(P, B),
	R is (B + V).
	
sum(N, R) :-
	lastdetectedsonar(K),
	N =< K,
	R is 0.

checkexpressionvalue :- %%True if the average value is less than the threshold
	allsonarspresent,
	dmin(T),
	expression(R),
	R < T.

allsonarcrossed :-
	lastdetectedsonar(N),
	numofsonars(N).



initialize :- actorPrintln("initializing the extendedWorldTheory ...").
:- initialization(initialize).