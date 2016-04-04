# probability

Probability is very difficult to understand. It is easy to calculate wrong, and the mathematics for it are
above most peoples understanding. I myself keep picking the wrong answers if I just rely on what I read and
what would seem to be correct.

I like to verify things programmatically. It is usually easier to understand the logic in a test than in
mathematical equations. It does not prove anything, but it gives us an easy way of seeing some numbers. And it
is much easier to understand numbers than equations.

This repo will try to include a lot of typical probability questions an average person could think of.

## Lotto

This includes different lotto probability checks

### Two adjacent numbers

How often does a draw of lotto numbers include two adjacent numbers? It will change with the numbers you have
to pick from, and how many numbers you must pick, but here is a list from doing 10 millions draw each time

*Answer* LottoAdjacentNumber

For 34 numbers where you pick 7 (standard norwegian lotto)

run | percentage
--- | ---
1 | 77
2 | 78
3 | 78
4 | 78
5 | 77

For 48 numbers where you pick 6 (standard norwegian viking lotto)

run | percentage
--- | ---
1 | 64
2 | 64
3 | 64
4 | 64
5 | 64

### Picking same numbers vs picking different numbers

Is it smart to pick the same numbers each week, or should you pick different numbers? What is best?

*Answer* LottoSameNumbers

Each run tests 10000 rounds of first winner

run | same | different
--- | --- | ---
1 | 5036 | 4964
2 | 4978 | 5022

I guess it does not really matter what you do, there is no real difference.
