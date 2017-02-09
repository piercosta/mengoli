obstacleDistance(Distance, SID) :- msg(E,'event',S,none,p(Distance, SID),N), Distance<70.

initialize :- actorPrintln("initializing the extendedWorldTheory ...").
:- initialization(initialize).