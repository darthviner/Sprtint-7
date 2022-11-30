package utils;

import models.Courier;

import java.util.Random;

public class CourierGenerator {
    public static Courier getUniqueCourier(){
        Random random = new Random();
        return new Courier("login"+random.nextInt(10000),"password","firstName");
    }
    public static Courier getCourierWithEmptyPassword(){
        return new Courier("login","","firstName");
    }
    public static Courier getCourierWithEmptyLogin(){
        return new Courier("","password","firstName");
    }
    public static Courier getCourierWithEmptyFirstName(){
        return new Courier("login","password","");
    }

}
