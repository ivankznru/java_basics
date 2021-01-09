
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
// Created five cats and got theirs weights
        Cat Murka  = new Cat();
        System.out.println("Murka has weight:"+ Murka.getWeight());
        Cat Vasja  = new Cat();
        System.out.println("Vasja has weight:"+ Vasja.getWeight());
        Cat Boris  = new Cat();
        System.out.println("Boris has weight:"+ Boris.getWeight());
        Cat Spring  = new Cat();
        System.out.println("Spring has weight:"+ Spring.getWeight());
        Cat Lucky  = new Cat();
        System.out.println("Lucky has weight:"+ Lucky.getWeight());

// Fed Murka with 100 of chicken in the morning
        Murka.feed(100.0);
        System.out.println ("Murka has weight after morning feeding:" + Murka.getWeight());
// Fed Lucky with 300 of fish in the morning
        Lucky.feed(300.0);
        System.out.println ("Lucky has weight after morning feeding:" + Lucky.getWeight());

// Fed Boris with a lot of beans.....Oh No. It has exploded. Poor Boris.
        while ( Boris.getStatus() != "Exploded")
        {
            Boris.feed(300.0);
        }
        System.out.println ("Boris has weight after morning feeding:" + Boris.getWeight());
        System.out.println ("Boris has status after morning feeding:" + Boris.getStatus());

// Unlucky Vasja lost his weight to death after made a lof of meowing this evening
        while ( Vasja.getStatus() != "Dead")
        {
            Vasja.meow();
        }
        System.out.println ("Vasja lost his weight after meowing this evening:" + Vasja.getWeight());
        System.out.println ("Vasja has status after meowing this evening :" + Vasja.getStatus());
        System.out.println("RIP Vasja and Boris. We will miss you.");

        //System.out.println(cat.getStatus());
    }
}