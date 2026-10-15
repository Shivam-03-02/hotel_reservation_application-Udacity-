package Model;

import java.time.LocalDate;

public class Reservation {

    Customer customer;
    IRoom myRoom;
    LocalDate checkInDate;
    LocalDate checkOutDate;


    public  Reservation(Customer customer, IRoom iRoom, LocalDate checkInDate, LocalDate checkOutDate) {


        this.customer= customer;
        this.myRoom = iRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getMyRoom() {
        return myRoom;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public  LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    @Override
    public String toString() {


        return "Customer: " + customer.toString() + "MY Reserved Room: " + myRoom.toString() + "Check-in LocalDate: " + checkInDate.toString() + "Check-out LocalDate: " + checkOutDate.toString();
    }
}
