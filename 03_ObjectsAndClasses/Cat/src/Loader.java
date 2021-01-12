
public class Loader {
    //Fields

    public static void main(String[] args) {


// Created one cat (object of class Cat)
        Cat snowFlake = new Cat();
        // Fed Snowflake with 150.0 grams of fish.

        snowFlake.feed(150.0);//параметр нужно вводить в граммах
        // Fed Snowflake with 97.0 ml of the milk after 1 hour //

        snowFlake.drink(100.0); // параметр нужно вводить в граммах 97 мл молоко примерно 100 гр
        System.out.println("Snowflake has weight:"+ snowFlake.getWeight());

        // Snowflake has peed several times during the day
        snowFlake.pee();
        snowFlake.pee();
        snowFlake.pee();
        snowFlake.pee();
        System.out.println("Snowflake has weight after several peeing:"+ snowFlake.getWeight());
        System.out.println("Snowflake has eaten " + snowFlake.getSumAllFood() +" grams of the food");
    }
}