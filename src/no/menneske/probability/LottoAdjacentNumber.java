package no.menneske.probability;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by vhanssen on 04/04/16.
 *
 * Check the probability for that a draw of lotto numbers has two numbers that are adjacent. Eg 1 and 2, or 29 and 30
 *
 * This class will draw 10 millions times and check if there are adjacent numbers. It uses as standard the norwegian
 * numbers of 34 and 7, but you can change that to whatevery you want.
 *
 */
public class LottoAdjacentNumber {
    final int RUNCOUNT = 10000000;
    final int NUMBERS = 48;
    final int PICKCOUNT = 6;

    // All the numbers. Easy way to pick the numbers
    Integer[] numbers = new Integer[NUMBERS];

    // The draw
    Integer[] draw = new Integer[PICKCOUNT];

    // Random generator
    Random rnd = new Random();

    int adjacentCount = 0;

    public static void main(String[] args) {
        LottoAdjacentNumber lottoAdjacentNumber = new LottoAdjacentNumber();
        lottoAdjacentNumber.run();
    }

    public void run() {
        for(int i=0;i<numbers.length;i++) {
            numbers[i] = i;
        }
        for(int i=0;i<RUNCOUNT;i++) {
            oneRun();
        }

        int percent = (adjacentCount*100)/RUNCOUNT;
        System.out.println("Run: "+RUNCOUNT+"\n"+"Adjacent: "+adjacentCount+"\n"+"Percentage: "+percent);
    }


    public void oneRun() {
        fill(draw);
        for(int i=1;i<PICKCOUNT;i++) {
            if (draw[i]==(draw[i-1]+1)) {
                adjacentCount++;
                return;
            }
        }
    }

    /*
 * This fills an array with PICKCOUNT different numbers from the numbers array
 */
    public void fill(Integer[] array) {
        int pos = 0;
        while(pos<PICKCOUNT) {
            int num =rnd.nextInt(NUMBERS);
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
