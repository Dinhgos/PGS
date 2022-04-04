public class Barrier {
    private int citac = 0;
    private double suma = 0;
    private int pocetVlaken;
    private boolean uspat = true;

    public Barrier(int pocetVlaken) {
        this.pocetVlaken = pocetVlaken;
    }

    synchronized public void synchronizujBezpecne(/*Vlakno v*/) {
//        suma += v.getSoucet();
//
//        while (uspat == false) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        citac++;
//
//        if (citac == pocetVlaken) {
//            System.out.println("Cislo pi po souctu " + v.getAktualniClen() * pocetVlaken + ". clenu: " + 4 * suma);
//
//            suma = 0;
//            uspat = false;
//
//            notifyAll();
//        }
//
//        while (uspat == true) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        citac--;
//
//        if (citac == 0) {
//            uspat = true;
//            notifyAll();
//        }
    }
}