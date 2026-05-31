public class CarDemo {
    static class Car {
        String make, model;
        int year;
        Car(String make, String model, int year) { this.make = make; this.model = model; this.year = year; }
        void displayDetails() { System.out.println(year + " " + make + " " + model); }
    }

    public static void main(String[] args) {
        Car c1 = new Car("Toyota", "Corolla", 2015);
        Car c2 = new Car("Honda", "Civic", 2020);
        c1.displayDetails();
        c2.displayDetails();
    }
}

/*

Output:
2015 Toyota Corolla
2020 Honda Civic
*/
