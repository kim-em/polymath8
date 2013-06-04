polymath8
=========

Tools and code for studying admissible sequences for the polymath8 project.

First of all, try:

    ./sbt "run-main polymath.RichardsSequenceApp"

which should find the first Richards-Henley sequence of size 341,640, and
report that it has width 4,802,222. There's a long delay after it prints 'trying
m = 5500', as it verifies admissibility at all the remaining primes. The whole
things takes about 8 minutes to run on my elderly iMac. It's multithreaded,
and should be fairly efficient. You can read and improve on the code yourself!

You can ask for other sizes, e.g.

    ./sbt "run-main polymath.RichardsSequenceApp 1000"

Later, there will hopefully be some interesting code for sieving and
annealing.
