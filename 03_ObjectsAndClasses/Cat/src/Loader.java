
public class Loader {

    public static void main(String[] args) {

        Cat catOne = new Cat();
        catOne.setColour("Black");
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

