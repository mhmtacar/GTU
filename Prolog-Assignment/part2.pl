

% knowledge base

flight(canakkale,erzincan,6).
flight(erzincan,canakkale,6).

flight(erzincan,antalya,3).
flight(antalya,erzincan,3).

flight(istanbul,izmir,2).
flight(izmir,istanbul,2).

flight(istanbul,ankara,1).
flight(ankara,istanbul,1).

flight(istanbul,rize,4).
flight(rize,istanbul,4).

flight(izmir,ankara,6).
flight(ankara,izmir,6).

flight(izmir,antalya,2).
flight(antalya,izmir,2).

flight(antalya,diyarbakir,4).
flight(diyarbakir,antalya,4).

flight(rize,ankara,5).
flight(ankara,rize,5).

flight(ankara,van,4).
flight(van,ankara,4).

flight(ankara,diyarbakir,8).
flight(diyarbakir,ankara,8).

flight(van,gaziantep,3).
flight(gaziantep,van,3).


member(X,[X|_]).
member(X,[_|Tail]) :- member(X,Tail).

route(CITY1,CITY2,COST) :- route_with_cost(CITY1,CITY2,COST,[]).

route_with_cost(CITY1,CITY2,COST,VISITED) :- flight(CITY1,CITY2,COST),
                                             \+ member(CITY2,VISITED).

route_with_cost(CITY1,CITY2,COST,VISITED) :- flight(CITY1,CITY3,COST1),
                                             CITY3 \== CITY2,
                                             \+ member(CITY3,VISITED),
                                             route_with_cost(CITY3,CITY2,COST2,[CITY1|VISITED]),
                                             COST is COST1 + COST2.
                    