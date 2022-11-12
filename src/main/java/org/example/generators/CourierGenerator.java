package org.example.generators;

import org.example.Courier;

public class CourierGenerator {
    public  static Courier getDefault(){
        return new Courier("Irina12345", "12345", "IRINA");
    }
    public static Courier getInUppercase(){
        return new Courier( "I007","007","IRINA");
    }
    public static Courier getWithoutLogin(){
        return new Courier(null,"12345",null);
    }
    public static Courier getWithoutPassword(){
        return  new Courier("Irina007",null,null);
    }
    public static Courier getEmptyPassword(){
        return new Courier("Irina007","",null);
    }
    public static Courier getEmptyLogin(){
        return new Courier("","12345",null);
    }
    public static Courier getEmptyLoginAndPassword(){
        return new Courier("","",null);
    }
    public static Courier  authorization(){
        return new Courier("Irina12345", "12345",null);
    }
    public static Courier authorizationWithInvalidPassword (){
        return new Courier("Irina12345", "F007",null);
    }


}
