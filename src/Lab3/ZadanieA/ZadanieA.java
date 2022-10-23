package Lab3.ZadanieA;

public class ZadanieA {
    public static void main(String[] args) {
        Kolo k = new Kolo(5);
        k.obliczObwod();
        k.obliczPole();

        Trojkat t= new Trojkat(3, 4, 5);
        t.obliczObwod();
        t.obliczPole();
    }
}
