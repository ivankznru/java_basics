
public class Cat {
    private double originWeight;
    private double weight;
    // private double minWeight;
    // private double maxWeight;
    private static int count;
    private boolean isAlive;
    private double sumAllFood;


    private final int EYES_COUNT = 2;


    private final double MIN_WEIGHT = 1000;
    private final double MAX_WEIGHT = 9000;
    private CatColour colour = CatColour.BLACK;


    // Конструктор для генерации кошек со случайным весом
    public Cat() {
        this.weight = 1500.0 + 3000.0 * Math.random();
        originWeight = this.weight;
        count++;
    }

    // Конструктор глубокого клонирования кошек
    public Cat(Cat that) {
        //  this(that.getWeight(), that.isAlive(), that.minWeight, that.maxWeight);
        weight = that.getWeight();
        isAlive = that.isAlive();
        setColour(that.getColour());
        if (isAlive) {
            count++;
        } else System.out.println("Кошка создана мертвой");

    }

    // Конструтор для создания котят
    public Cat(double kittenWeight) {
        weight = kittenWeight;
    }

    public void setColour(CatColour colour) {
        this.colour = colour;
    }

    public CatColour getColour() {
        return colour;
    }

    public void meow() {
        if (isAlive()) {
            weight = weight - 1;
            if (weight < MIN_WEIGHT) {
                count = count - 1;
            }
            System.out.println("Meow");
        } else {
            System.out.println("Cat is dead and can not meow");
        }
    }


    public void feed(Double amount) {
        if (isAlive()) {
            weight = weight + amount;
            sumAllFood = sumAllFood + amount;
            if (weight > MAX_WEIGHT) {
                count = count - 1;
            }
        } else {
            System.out.println("Cat is dead and can not eat");
        }

    }

    public void drink(Double amount) {
        if (isAlive()) {
            weight = weight + amount;
            sumAllFood = sumAllFood + amount;
            if (weight > MAX_WEIGHT) {
                count = count - 1;
            }
        } else {
            System.out.println("Cat is dead and can not drink");
        }
    }


    public Double getWeight() {

        return weight;
    }

    public String getStatus() {

        if (weight < MIN_WEIGHT) {
            return "Dead";
        } else if (weight > MAX_WEIGHT) {
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
            if (weight < MIN_WEIGHT) {
                count = count - 1;
            }
            System.out.println("Cat is peeing now");
        } else {
            System.out.println("Cat ia dead and can not pee");
        }
    }

    public double getSumAllFood() {

        return sumAllFood;
    }


    public boolean isAlive() {
        if ((weight > MAX_WEIGHT) | (MIN_WEIGHT > weight)) {
            isAlive = false;
        } else {
            isAlive = true;
        }
        return isAlive;
    }

    public double getMIN_WEIGHT() {
        return MIN_WEIGHT;
    }

    public double getMAX_WEIGHT() {
        return MAX_WEIGHT;
    }

    public int getEYES_COUNT() {
        return EYES_COUNT;
    }

    public static int getCount() {

        return count;
    }


}