# About

This is an implementation of Lamport's iterative (non-recursive) variant
of the quicksort sorting algorithm in Java.
Bertrand Meyer provides a description of the algorithm on his
[blog](https://bertrandmeyer.com/2014/12/07/lampsort/)
and references a
[video](http://channel9.msdn.com/Events/Build/2014/3-642)
showing Leslie Lamport's original description.

The idea is to maintain the bounds of the intervals to sort
in a set.
Initially, the set contains only the bounds of the whole interval
that is to be sorted.
In each iteration, interval bounds are removed from the set and if
the interval contains more than one element, quicksort's
partition algorithm is carried out on the elements within the interval.
Partition produces two subintervals which are added to the set.
When the set is empty, the algorithm terminates.

The partitioning part of the implementation simply picks the element with the
highest index as the pivot element.

# License

The code is licensed under the GNU LGPL, version 3.
Feel free to use and improve the code.

# Copyright

Copyright 2015 Fabian Foerg

