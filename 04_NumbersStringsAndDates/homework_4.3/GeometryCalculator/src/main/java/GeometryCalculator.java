public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {

        return Math.PI * Math.abs(radius * radius);
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {

        return  4.00 / 3.00 * (Math.PI * Math.pow(Math.abs(radius),3));
    }

    public static boolean isTrianglePossible(double a, double b, double c) {

        var maxSide = Math.max(a, Math.max(b,c));
       return (a + b + c) - maxSide > maxSide;

    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare ( double a, double b, double c){

        var p = (a + b + c)/2.0;
        return    isTrianglePossible(a,b,c) ? Math.sqrt(p * (p-a) * (p-b) * (p-c)) : -1.0;

    }
}

