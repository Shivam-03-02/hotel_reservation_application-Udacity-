package api;

import Model.Customer;
import Model.IRoom;
import Model.Reservation;
import Service.CustomerService;
import Service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class HotelResource {
    public static final HotelResource hotelresource = new HotelResource();
    public final ReservationService RESERVATION_SERVICE = ReservationService.getReservationService();
    public final CustomerService CUSTOMER_SERVICE = CustomerService.getCustomerService();

    HotelResource() {}


    public Customer getCustomer(String email) {
        CustomerService customerService = CustomerService.getCustomerService();
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        CustomerService customerService = CustomerService.getCustomerService();
        customerService.addCustomer(email,firstName,lastName);
    }
    public IRoom getRoom(String roomNumber) {
        return RESERVATION_SERVICE.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {

        Customer customer = CUSTOMER_SERVICE.getCustomer(customerEmail);
        return RESERVATION_SERVICE.reserveARoom(customer,room,checkInDate,checkOutDate);
    }

    public  List<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = CUSTOMER_SERVICE.getCustomer(customerEmail);
        return RESERVATION_SERVICE.getCustomerReservation(customer);
    }

    public Collection<IRoom> findARoom(LocalDate checkIn, LocalDate checkout) {
        return RESERVATION_SERVICE.findRooms(checkIn,checkout);
    }
    public void printAllReservation() {
        for(Reservation reservation: RESERVATION_SERVICE.RESERVATIONS) {
            System.out.println(reservation);
        }
    }

    public static  HotelResource getHotelResource() {
        return hotelresource;
    }
}
