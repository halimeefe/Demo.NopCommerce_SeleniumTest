package Utility;

public class MyFunc {

    public static void BekleKapat(int sn) {


        {
            try {
                Thread.sleep(1000 * sn); // milisaniye  cinsinden beklediği
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
}



