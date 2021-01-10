
public class Loader {
    //Fields

    public static void main(String[] args) {


// Created one cat (object of class Cat)
        Cat snowFlake = new Cat();
   // Fed Snowflake with 150.0 grams of fish.
      snowFlake.sumAllFood(150.0);
   // Fed Snowflake with 100.0 grams of the can food after 1 hour.
    snowFlake.sumAllFood(100.0);

        System.out.println("Snowflake has weight:"+ snowFlake.getWeight());
  // Snowflake has peed several times during the day
  snowFlake.pee();
  snowFlake.pee();
  snowFlake.pee();
  snowFlake.pee();
        System.out.println("Snowflake has weight after several peeing:"+ snowFlake.getWeight());
        System.out.println("Snowflake has eaten " + snowFlake.sumAllFood(0.0) +" grams of the food");
    }
}