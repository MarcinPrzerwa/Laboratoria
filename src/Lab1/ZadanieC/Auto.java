package Lab1.ZadanieC;

import java.util.Random;

class Auto {
    float[]przebieg = new float[12];

    Auto(){
        Random generator = new Random();
        for (int i=0; i < 12; i++){
            przebieg[i]=generator.nextFloat()*100;
        }
    }
    float srednia;
    public float srPrzebieg(){
        for(int i=0; i<12; i++){
            srednia+=przebieg[i];
        }
        return srednia/12;
    }
}