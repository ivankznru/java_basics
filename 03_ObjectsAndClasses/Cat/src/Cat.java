
public class Cat {
    private double originWeight;
    private double weight;
    private double minWeight;
    private double maxWeight;
    private static int count;
    private boolean isAlive;
    private double sumAllFood;
    private static final int EYES_COUNT = 2;
    private static final double MIN_WEIGHT = 1000;
    private static final double MAX_WEIGHT = 9000;
    private String colour = "Окрас не определен";

    // Конструктор для генерации кошек со случайным весом
    public Cat() {
        this.weight = 1500.0 + 3000.0 * Math.random();
        originWeight = this.weight;
        minWeight = 1000.0;//grams
        maxWeight = 9000.0;//grams
        count++;
    }

    // Конструктор глубокого клонирования кошек
    public Cat(Cat that) {
        //  this(that.getWeight(), that.isAlive(), that.minWeight, that.maxWeight);
        weight = that.getWeight();
        isAlive = that.isAlive();
        minWeight = that.minWeight;
        maxWeight = that.maxWeight;
        setColour(that.getColour());
        if (isAlive) {
            count++;
        } else System.out.println("Кошка создана мертвой");

    }

    // Конструтор для создания котят
    public Cat(double kittenWeight) {
        weight = kittenWeight;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void meow() {
        if (isAlive()) {
            weight = weight - 1;
            if (weight < minWeight) {
                count = count - 1;
            }
            System.out.println("Meow");
        } else {
            System.out.println("Кошка мертва и не может мяукать");
        }
    }


    public void feed(Double amount) {
        if (isAlive()) {
            weight = weight + amount;
            sumAllFood = sumAllFood + amount;
            if (weight > maxWeight) {
                count = count - 1;
            }
        } else {
            System.out.println("Кошка мертва и не может есть");
        }

    }

    public void drink(Double amount) {
        if (isAlive()) {
            weight = weight + amount;
            sumAllFood = sumAllFood + amount;
            if (weight > maxWeight) {
                count = count - 1;
            }
        } else {
            System.out.println("Кошка мертва и не может пить");
        }
    }


    public Double getWeight() {

        return weight;
    }

    public String getStatus() {

        if (weight < minWeight) {
            return "Dead";
        } else if (weight > maxWeight) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }

    public void pee() {
        if (isAlive()) {
            weight = weight - 20;
            if (weight < minWeight) {
                count = count - 1;
            }
            System.out.println("Cat is peeing now");
        } else {
            System.out.println("Кошка мертва и не может ходить в туалет");
        }
    }

    public double getSumAllFood() {

        return sumAllFood;
    }


    public boolean isAlive() {
        if ((weight > maxWeight) | (minWeight > weight)) {
            isAlive = false;
        } else {
            isAlive = true;
        }
        return isAlive;
    }


    public static int getCount() {

        return count;
    }


}