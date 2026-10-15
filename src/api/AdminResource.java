package api;

import Model.Customer;
import Model.IRoom;
import Model.Reservation;
import Service.CustomerService;
import Service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private static final AdminResource adminResource = new AdminResource();

    public final ReservationService RESERVATION_SERVICE = ReservationService.getReservationService();
    public final CustomerService CUSTOMER_SERVICE = CustomerService.getCustomerService();


    AdminResource() {}

    public Customer getCustomer(String email) {
        return CUSTOMER_SERVICE.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for(IRoom room: rooms) {
            RESERVATION_SERVICE.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return RESERVATION_SERVICE.getRooms().values();
    }

    public Collection<Customer> getAllCustomers() {
        return CUSTOMER_SERVICE.getAllCustomer();
    }

    public void displayAllReservations() {
       for(Reservation reservation: RESERVATION_SERVICE.getCustomerReservation()) {
           System.out.println(reservation);
       }
    }

    public static AdminResource getAdminResource() { return adminResource;}
}
