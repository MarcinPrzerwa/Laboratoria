package Lab3.ZadanieA;

public class Kolo implements Figura {

    private double promien;

    public Kolo(double promien){
        this.promien=promien;
    }

    public void obliczObwod(){
        double obwod;
        obwod=2*promien*PI;
        System.out.println("Obwod kola: " + obwod);
    }

    public void obliczPole(){
        double pole;
        pole=PI*promien*promien;
        System.out.println("Pole kola: " + pole);
    }
}
