package core;

public class Car {
    public String number;
    public int height;
    public double weight;
    public boolean hasVehicle;
    public boolean isSpecial;

    public String toString() {
        String special = isSpecial()? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
                special + "Автомобиль с номером " + getNumber() +
                ":\n\tВысота: " + getHeight() + " мм\n\tМасса: " + getWeight() + " кг";
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean hasVehicle() {
        return hasVehicle;
    }

    public void hasVehicle(boolean hasVehicle) {
        this.hasVehicle = hasVehicle;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void isSpecial(boolean special) {
        isSpecial = special;
    }
}