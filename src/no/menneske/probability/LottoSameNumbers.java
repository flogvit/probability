package no.menneske.probability;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by vhanssen on 04/04/16.
 *
 * Check the difference between picking the same numbers each time and different numbers each time in Lotto
 * This program uses the norwegian setup of picking 7 numbers out of 34. But the amount of numbers does not
 * change anything for the outcome, so you can change them to whatever you want.
 *
 * The cycle is:
 * 1. Pick 7 numbers to use for the same pick
 * 2. For each draw pick 7 numbers for different pick, and 7 numbers as the draw
 * 3. If no one has won, go back to 2.
 * 4. If someone wins, add to the counter and go back to 1.
 *
 * As you see, we stop the run when someone wins. And we pick new 7 numbers for each run. The reasoning for this
 * is that you usually do not live long enough to win twice, so this is set up as 10000 different persons trying
 * these two methods to win.
 *
 * The result is, as predicted, there is no difference.
 */

public class LottoSameNumbers {
    final int RUNCOUNT = 10000;
    final int NUMBERS = 34;
    final int PICKCOUNT = 7;

    // All the numbers. Easy way to pick the numbers
    Integer[] numbers = new Integer[NUMBERS];

    // The pick of same numbers each draw
    Integer[] pickSame = new Integer[PICKCOUNT];

    // The pick of different numbers each draw
    Integer[] pickDifferent = new Integer[PICKCOUNT];

    // The draw
    Integer[] draw = new Integer[PICKCOUNT];

    // Random generator
    Random rnd = new Random();

    // Sum of pickSame count
    int pickSameCount = 0;

    // Sum of pickDifferent count
    int pickDifferentCount = 0;

    public static void main(String[] args) {
        LottoSameNumbers lottoSameNumbers = new LottoSameNumbers();
        lottoSameNumbers.run();
    }

    /*
     * This will run the number of draws we want to check.
     * Each run will draw until it finds a winner
     */
    public void run() {
        for(int i=0;i<numbers.length;i++) {
            numbers[i] = i;
        }
        for(int i=0;i<RUNCOUNT;i++) {
            oneRun();
            print();
        }
    }

    /*
     * Print the statistics
     */
    public void print(){
        System.out.print("Same: "+ pickSameCount);
        System.out.println("    Different: "+ pickDifferentCount);

    }

    /*
     * This will do one run, finding the pick which win first
     */
    public void oneRun() {
        // First we fill the pickSame for the whole run
        fill(pickSame);

        boolean found = false;
        long count = 0;
        do {
            count++;
            // For each run we fill the pickDifferent and the draw of numbers
            fill(pickDifferent);
            fill(draw);

            // If the draw is the same as pickSame, add to the counter and prepare to end loop
            if (isPickSame()) {
                pickSameCount++;
                found=true;
            }
            // If the draw is the same as pickDifferent, add to the counter and prepare to end loop
            if (isPickDifferent()) {
                pickDifferentCount++;
                found=true;
            }
        } while(!found);
    }

    public boolean isPickDifferent() {
        for(int i=0;i<PICKCOUNT;i++) {
            if (pickDifferent[i]!= draw[i]) return false;
        }
        return true;
    }

    public boolean isPickSame() {
        for(int i=0;i<PICKCOUNT;i++) {
            if (pickSame[i]!= draw[i]) return false;
        }
        return true;
    }

    /*
     * This fills an array with PICKCOUNT different numbers from the numbers array
     */
    public void fill(Integer[] array) {
        int pos = 0;
        while(pos<PICKCOUNT) {
            int num =rnd.nextInt(34);
            // If it is already picked, try again
            if (numbers[num]==-1) continue;

            // Add number to the array
            array[pos] = numbers[num];
            // Set the number to -1 to check for already picked
            numbers[num]= -1;
            pos++;
        }
        // We need to add the numbers back to the numbers array
        for(int i=0;i<PICKCOUNT;i++) {
            numbers[array[i]] = array[i];
        }
        // Sort the array so we can easily check for equality
        Arrays.sort(array);
    }
}
