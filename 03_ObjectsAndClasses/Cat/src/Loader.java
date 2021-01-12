
public class Loader {

    public static void main(String[] args) {
// Created five cats and got theirs weights
        Cat murka = new Cat();
        System.out.println("Murka has weight:" + murka.getWeight());
        Cat vasja = new Cat();
        System.out.println("Vasja has weight:" + vasja.getWeight());
        Cat boris = new Cat();
        System.out.println("Boris has weight:" + boris.getWeight());
        Cat spring = new Cat();
        System.out.println("Spring has weight:" + spring.getWeight());
        Cat lucky = new Cat();
        System.out.println("Lucky has weight:" + lucky.getWeight());


// Fed Murka with 100 of chicken in the morning
        murka.feed(100.0);
        System.out.println("Murka has weight after morning feeding:" + murka.getWeight());
// Fed Lucky with 300 of fish in the morning
        lucky.feed(300.0);
        System.out.println("Lucky has weight after morning feeding:" + lucky.getWeight());

// Fed Boris with a lot of beans.....Oh No. It has exploded. Poor Boris.
        while (!boris.getStatus().equals("Exploded"))
        {
            boris.feed(300.0);
        }
        System.out.println("Boris has weight after morning feeding:" + boris.getWeight());
        System.out.println("Boris has status after morning feeding:" + boris.getStatus());

// Unlucky Vasja lost his weight to death after made a lof of meowing this evening
        while (!vasja.getStatus().equals("Dead"))
        {
            vasja.meow();
        }
        System.out.println("Vasja lost his weight after meowing this evening:" + vasja.getWeight());
        System.out.println("Vasja has status after meowing this evening :" + vasja.getStatus());
        System.out.println("RIP Vasja and Boris. We will miss you.");


    }
}