import java.util.Scanner;
import java.util.InputMismatchException;

public class Task10 {

    public static void main(String[] args) {
        double a, b, c;

        // вводим стороны треугольника:
        System.out.println("Введите длину сторон треугольника:");
        System.out.print("Сторона a = ");
        do a = getSide(); while (a==0);
        System.out.print("Сторона b = ");
        do b = getSide(); while (b==0);
        System.out.print("Сторона c = ");
        do c = getSide(); while (c==0);

        // вычисление площади
        Triangle triangle = new Triangle(a,b,c);
        double sq = triangle.mathArea();
        if (sq>0) {
            System.out.println("Площадь треугольника = " + sq);
        }


    }

    // метод ввода значения c проверкой правильности ввода
    public static double getSide() {
        double side;
        Scanner scaner = new Scanner(System.in);
        try {
             side = scaner.nextDouble();
        }
        catch (InputMismatchException ex){
            System.out.print("Введено не число! Повторите ввод: ");
            side = 0;
        }
        return side;
    }

}



// класс Треугольника
class Triangle {
    private double a, b, c;

    // конструктор по умолчанию
    public Triangle() {
        a = b = c = 1;
    }

    // конструктор с проверкой длин сторон
    public Triangle(double a_bak, double b_bak, double c_bak) {
        try {
            // проверяем - можно ли из переданных длин сторон создать треугольник
            if (((a_bak + b_bak) < c_bak) || ((a_bak + c_bak) < b_bak) || ((b_bak + c_bak) < a_bak))
                throw new AreaException();
        } catch (AreaException e) {
            System.out.println("Ошибка: " + e.toString());
            return;
        }

        // если стороны треугольника введены корректно, передаем в переменные сторон класса Треугольника
        a = a_bak;
        b = b_bak;
        c = c_bak;
    }

    // собственный класс обработки исключения
    class AreaException extends Exception {
        public String toString() {
            return "Введены некорректные длины сторон!";
        }
    }

    // метод возвращающий площадь треугольника
    public double mathArea() {
        // если стороны треугольника имеют корректные размеры,
        // то проверку на корень из отрицательного числа делать не нужно
        double p, s;
        p = (a + b + c) / 2;
        s = Math.sqrt(p * (p - a) * (p - b) * (p - c)); // формула Герона
        return s;
    }

}