package Assignments;
class Vehicle {

    // Method in base class
    void displayType() {
        System.out.println("This is a vehicle.");
    }
}

// Car inherits Vehicle
class Car extends Vehicle {

    // Method overriding
    @Override
    void displayType() {
        System.out.println("This is a car.");
    }
}

// Bike inherits Vehicle
class Bike extends Vehicle {

    // Method overriding
    @Override
    void displayType() {
        System.out.println("This is a bike.");
    }
}

public class VehicleDemo {
    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        Vehicle car = new Car();
        Vehicle bike = new Bike();

        vehicle.displayType();
        car.displayType();
        bike.displayType();
    }
}