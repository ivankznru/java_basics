public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        double circleSquare = Math.PI * Math.abs(radius * radius);
        return circleSquare;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        double sphereVolume = 4.00 / 3.00 * (Math.PI * Math.abs(radius*radius*radius));
        return sphereVolume;
    }

    public static boolean isTrianglePossible(double a, double b, double c) {
        boolean trianglePossible;
        if ((a > 0.0) & (b > 0.0) & (c > 0.0) & (a + b > c) & (a + c > b) & (b + c > a)) {
            trianglePossible = true;
        } else {
            trianglePossible = false;
        } ;
        return trianglePossible;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare ( double a, double b, double c){
        double triangleSquare;
        if ( isTrianglePossible(a,b,c)){
            double p = (a + b + c)/2.0;
            triangleSquare = Math.sqrt(p * (p-a) * (p-b) * (p-c));
        }
        else {  triangleSquare= -1.0;}
        return triangleSquare;
    }
}

