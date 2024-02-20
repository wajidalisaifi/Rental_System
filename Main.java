import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Car{
    private String carName;
    private String carId;
    private String carModel;
    private boolean isAvailable;
    private int BasePrice;
    Car(String carName,String carId,String carModel,int BasePrice){
        this.carName = carName;
        this.carId = carId;
        this.carModel = carModel;
        this.isAvailable = true;
        this.BasePrice = BasePrice;
    }
    public String getCarName(){
        return carName;
    }
    public String getCarId(){
        return carId;
    }
    public String getCarModel(){
        return carModel;
    }
    public  int getBasePrice(int day){
        return  BasePrice*day;
    }
    public Boolean isAvailable(){
        return isAvailable;
    }
    public void isNotAvailable(){
        isAvailable = false;
    }
    public void getReturnCar(){
        isAvailable  = true;
    }
}
class Customer{
    private String customerName;
    private String customerId;
    private String customerAge;
    Customer(String customerName,String customerId,String customerAge){
        this.customerName = customerName;
        this.customerId = customerId;
        this.customerAge = customerAge;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getCustomerId(){
        return customerId;
    }
    public String getCustomerAge(){
        return customerAge;
    }
}
class Rental{
    private Car car;
    private Customer customer;
    int Days;
    Rental(Car car,Customer customer,int days){
        this.car = car;
        this.customer = customer;
        this.Days = days;
    }
    public Car getCar(){
        return car;
    }
    public Customer getCustomer(){
        return customer;
    }
    public int getDay(){
        return Days;
    }
}
class RentalSystem {
    private ArrayList<Car> cars;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;

    RentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addRental(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            rentals.add(new Rental(car, customer, days));
            car.isNotAvailable();
            System.out.println("-------Car Rental successfully--------- ");
        } else {
            System.out.println("Car was rented");
        }
    }

    public void ReturnCar(Car car) {
        Rental RemoveRental = null;
        for (Rental rental : rentals) {
            if (rental.getCar().equals(car)) {
                RemoveRental = rental;
                break;
            }
        }
        if (RemoveRental != null) {
            rentals.remove(RemoveRental);
            car.getReturnCar();
            System.out.println(" car successfully return");
        } else {
            System.out.println("Car not return");
        }
    }

    public void menu() {
        while (true) {

            System.out.println("-------Welcome to Rental System-----");
            System.out.println("[1] Rent a Car");
            System.out.println("[2] Return a Car");
            System.out.println("[3] Exit");
            System.out.println("Enter Your Choice");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.println("Enter your Name");
                String customerName = sc.nextLine();
                System.out.println("<<<<<<< Car is Available >>>>>>>>");
                ArrayList<Car> carGone = new ArrayList<>();
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(".........................................................");
                        System.out.println("Car Name         Car Id          Car Model");
                        System.out.println(car.getCarName() + "             " + car.getCarId() + "           " + car.getCarModel());
                    } else {
                        carGone.add(car);
                    }
                }
                for (Car cars_i : carGone) {
                    System.out.println(cars_i.getCarName() + " has gone Rent");
                }

                        System.out.println("Enter Car ID That you want Rent");
                        String CarId2 = sc.next();
                        System.out.println("Enter day for Rent");
                        int day = sc.nextInt();

                        Car selectCar = null;
                        for (Car car : cars) {
                            if (car.getCarId().equals(CarId2) && car.isAvailable()) {
                                selectCar = car;
                            }
                        }

                        Customer customer1 = new Customer(customerName, "0011", "35");
                        addCustomer(customer1);
                        if (selectCar != null) {
                            int totalPrice = selectCar.getBasePrice(day);
                            System.out.println("carName: " + selectCar.getCarName());
                            System.out.println("Car ID: " + selectCar.getCarId());
                            System.out.println("Car Model: " + selectCar.getCarModel());
                            System.out.println("Customer Name: " + customerName);
                            System.out.println("customer ID: " + customer1.getCustomerId());
                            System.out.println("customer Age: " + customer1.getCustomerAge());
                            System.out.println("Total Price: " + totalPrice);

                            System.out.println("confirm Y/N");
                            String confirm = sc.next();
                            if (confirm.equalsIgnoreCase("y")) {
                                addRental(selectCar, customer1, day);
                            } else {
                                System.out.println("Car Rent cancel");
                            }
                        } else {
                            System.out.println("invalid selected");
                        }
            }
            if (choice == 2){
                System.out.println("Enter Car Id that you want to return");
                String carr  = sc.next();
                Car returnCar = null;
                for (Car car : cars){
                    if(car.getCarId().equals(carr)){
                        returnCar = car;
                        break;
                    }
                }
                if (returnCar != null){
                    Customer customerID  = null;
                    for (Rental rental : rentals){
                        if (rental.getCar() == returnCar){
                            customerID = rental.getCustomer();
                            break;
                        }
                    }
                    if (customerID != null){
                        ReturnCar(returnCar);
                    }
                    else {
                        System.out.println("car did not rent");
                    }
                }
                else {
                    System.out.println("Car return id not match");
                }
            }
            if (choice == 3){
                break;
            }
            else if (choice >= 4){
                System.out.println("invalid choice");
            }
        }
        System.out.println("Thank using for Car Rental System");
    }
}
class Main{
    public static void main(String[] args) {
        Car car1 = new Car("Thar", "0011", "2024", 400);
        Car car2 = new Car("BMW", "1122", "2024", 500);
        Car car3 = new Car("Rolls_Royal", "0022", "2024", 800);
        RentalSystem sc = new RentalSystem();
        sc.addCar(car1);
        sc.addCar(car2);
        sc.addCar(car3);
        sc.menu();
    }
}