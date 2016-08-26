package com.example.moataz.cleanram;

import android.util.Log;

import java.util.Random;


public class values {
   static Long avBefore = 0L;
   static Long total=0L;
   static Long avAfter = 0L;
    static int saved=15;
   static Long Avilable = 0L;
    Random rn = new Random();

    public static int getSaved() {
        return saved;
    }

    public static void setSaved(int saved) {
        values.saved = saved;
    }

    static int loss;
    public values (){

}
    public static int getLoss() {
        return loss;
    }

    public static void setLoss(int loss) {
        values.loss = loss;
    }

    public String getAvilable() {
        return Avilable.toString();
    }
    public Long getLAvilable() {
        return Avilable;
    }
    public void setAvilable(Long avilable) {
        this.Avilable= avilable;}

    public String getAvAfter() {
        return avAfter.toString();
    }
    public Long getLAvAfter() {
        return avAfter;
    }
    public void setAvAfter(Long avilable) {
        this.avAfter = avilable;
    }


    public String getAvBefore() {
        return avBefore.toString();
    }
    public Long getLAvBefore() {
        return avBefore;
    }
    public void setAvBefore(Long avilable) {
        this.avBefore = avilable;
    }


    public String getTotal() {
        return total.toString();
    }
    public Long getLTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


}
