package Utils;

import java.util.Random;

public class Utils {
    public static double randomRange(double low, double high){
        Random r = new Random();
    
        return low + (high - low) * r.nextDouble();
    }

    public static int randomRange(int low, int high){
        Random r = new Random();
    
        return r.nextInt(high - low + 1) + low;
    }
}
