obstacleDistance(D,A) :- msg(E,'event',S,none,p(D,A),N), D<70.

initialize :- actorPrintln("initializing the extendedWorldTheory ...").
:- initialization(initialize).