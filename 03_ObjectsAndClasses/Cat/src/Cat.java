
public class Cat
{
    private double originWeight;
    private double weight;
    private double minWeight;
    private double maxWeight;
    private double sumAllFood;
    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;//grams
        maxWeight = 9000.0;//grams

    }

    public void meow()
    {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)

    {
        weight = weight + amount;
        sumAllFood=sumAllFood+amount;
    }

    public void drink(Double amount)

    {
        weight = weight + amount;
        sumAllFood=sumAllFood+amount;
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    // Method (Getter) with return value double sumAllFood
    public double getSumAllFood() {

        return sumAllFood;
    }
    // Method without return value
    public void pee() {
        weight = weight - 20;
        System.out.println("Cat is peeing now");
    }


}