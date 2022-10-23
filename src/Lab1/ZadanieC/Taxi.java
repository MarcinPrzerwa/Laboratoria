package Lab1.ZadanieC;

import java.util.Random;

public class Taxi extends Auto{
    float[] zarobki = new float[12];

    Taxi(){
        Random generator = new Random();
        for (int i=0; i < 12; i++){
            zarobki[i]=generator.nextFloat()*5000;
        }
    }
    float srednia;
    public float srZarobki(){
        for(int i=0; i<12; i++){
            srednia+=zarobki[i];
        }
        return srednia/12;
    }
}
