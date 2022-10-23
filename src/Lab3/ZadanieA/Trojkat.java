package Lab3.ZadanieA;

public class Trojkat implements Figura {

    double dlugoscBoku1;
    double dlugoscBoku2;
    double dlugoscBoku3;

    public Trojkat(double dlugoscBoku1, double dlugoscBoku2, double dlugoscBoku3) {
        this.dlugoscBoku1= dlugoscBoku1;
        this.dlugoscBoku2= dlugoscBoku2;
        this.dlugoscBoku3= dlugoscBoku3;
    }

    public void obliczObwod(){
        double obwod;
        obwod= dlugoscBoku1+dlugoscBoku2+dlugoscBoku3;
        System.out.println("obwod trojkata= "+ obwod);
    }

    public void obliczPole(){
        double p;
        double pole;
        p= (dlugoscBoku1+dlugoscBoku2+dlugoscBoku3)/2;
        pole= Math.sqrt(p*(p-dlugoscBoku1)*(p-dlugoscBoku2)*(p-dlugoscBoku3));
        System.out.println("pole trojkata= " + pole);

    }
}
