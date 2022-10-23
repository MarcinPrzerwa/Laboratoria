package Lab1;

public class ZadanieB {
    public static void main(String[] args)
    {
        if(args.length<4){
            System.out.println("za malo argumentow");
            System.exit(0);
        }
        String[] wyrazy= new String[4];
        for(int i=0; i<4; i++) {
            wyrazy[i]=args[i];
            System.out.println(wyrazy[i]);
        }
    }
}
