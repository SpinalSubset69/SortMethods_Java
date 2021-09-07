package logic;

import java.util.Random;

public class RandomNumberGenerator {
    public int[] GenerateArrayWihtRandomNumbers(int nToGenerate){
        int[] array = new int[nToGenerate];
        
        for(int i = 0; i < nToGenerate; i++){
            //int a = (int)(Math.random()*(max-min+1) + min);
            Random random = new Random();
            int a = random.nextInt(999); //Generates random numbers wihthin 0 - 999
            array[i] = a;
        }

        return array;
    }
}
