package org.example.generators;

import org.example.Order;

import java.util.List;

public class OrderGenerator {
    public static Order colourBlackAndGray (){
        return new Order("Юрий", "Гагарин", "г.Королев, Лесная, 1", "Черкизовская", "89630000000", 2, "2022-10-10", "I'll be back", List.of("BLACK", "GRAY"));
    }
    public static Order colourBlack(){
        return new Order("ичиго","куросаки","Kamakura","Преображенская площадь", "899631111111", 2, "2022-10-10", "I'll be back",List.of("BLACK"));
    }
    public static Order colourGray(){
        return new Order("Sergey", "Lavrov", "г.Москва, Лубянка, д.1", "Сокольники", "89632222222", 2, "2022-10-10", "I'll be back",List.of("GRAY"));
    }
    public static Order colourNull(){
        return new Order("кучики", "рукия", "Сейритей", "Черкизовская", "89633333333", 2, "2022-10-10", "I'll be back",null);
    }
    public static Order colourEmpty(){
        return new Order("james","bond","London, Green str, 007","Преображенская площадь", "89634444444", 2, "2022-10-10", " I'll be back",List.of(""));
    }


}

