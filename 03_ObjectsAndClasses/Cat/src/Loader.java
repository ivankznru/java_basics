
public class Loader {

    public static void main(String[] args) {
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
        System.out.println("Numbers of cats are:" + Cat.getCount());

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
        System.out.println("Numbers of cats are:" + Cat.getCount());



        Cat catOne = new Cat();
        catOne.setColour(CatColour.BLUE);
        System.out.println(catOne.getColour());
        System.out.println(catOne.getWeight());
        System.out.println(catOne.isAlive());

        System.out.println("Deep copy");
        Cat deepCopy = new Cat(catOne);
        System.out.println(deepCopy.getWeight());
        System.out.println(deepCopy.isAlive());
        System.out.println(deepCopy.getColour());

        System.out.println("Numbers of cats are:" + Cat.getCount());
        catOne.feed(100d);
        System.out.println(catOne.getWeight());
        System.out.println("Status of cat one is:" + catOne.getStatus());
        catOne.feed(150d);
        catOne.feed(15000d);
        catOne.feed(15000d);
        System.out.println(catOne.getWeight());
        catOne.pee();

        catOne.meow();
        System.out.println(catOne.getWeight());
        System.out.println("Status of cat one is:" + catOne.getStatus());


        System.out.println("Numbers of alive cats are:" + Cat.getCount());
        System.out.println(catOne.getSumAllFood());
        System.out.println(catOne.getWeight());
        System.out.println("Cat one has eyes :" + catOne.getEYES_COUNT());

        Cat kittenOne = getKitten();
        Cat kittenTwo = getKitten();
        Cat kittenThree = getKitten();
        System.out.println("Kitten one has weight:" + kittenOne.getWeight());
        System.out.println("Kitten two has weight:" + kittenTwo.getWeight());
        System.out.println("Kitten three has weight:" + kittenThree.getWeight());
    }

    private static Cat getKitten() {
        Cat kitten = new Cat(1100d);
        return kitten;
    }
}

