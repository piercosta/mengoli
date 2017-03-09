dmin(70).
drobotdetected(40).

checkdetected :-
	robotposition(N),
	SID is (N + 1),
	lastsonarid(SID),
	sonar(Distance, SID),
	drobotdetected(D),
	Distance < D.

allsonarspresent :-
	numofsonars(N),
	count(N, N).
	
count(SID, R) :-
	SID > 0,
	sonar(_ , SID),
	P is (SID - 1),
	count(P, B),
	R is (B + 1).
	
count(SID, R) :-
	SID < 1,
	R is 0.
	
expression(R) :-
	numofsonars(N),
	sum(N, S),
	robotposition(O),
	K is O + 1,
	R is S / (N - K + 1).
	
sum(N, R) :-
	robotposition(K),
	N > K,
	sonar(V, N),
	P is (N - 1),
	sum(P, B),
	R is (B + V).
	
sum(N, R) :-
	robotposition(K),
	N =< K,
	R is 0.

checkexpressionvalue :- %%True if the average value is less than the threshold
	allsonarspresent,
	dmin(T),
	expression(R),
	R < T.

lastsonar(Distance, R):-
	lastsonarid(SID),
	sonar(Distance,SID),
	R is (SID * 30).

allsonarcrossed :-
	robotposition(N),
	numofsonars(N).
	
robotpositioninc(X) :-
	robotposition(N),
	P is (N+X),
	retract(robotposition(N)),
	assert(robotposition(P)).



initialize :- actorPrintln("initializing the extendedWorldTheory ...").
:- initialization(initialize).