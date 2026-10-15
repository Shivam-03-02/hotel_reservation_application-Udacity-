package Service;

import Model.Customer;
import Model.IRoom;
import Model.Reservation;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class ReservationService {
    public  final Map<String, IRoom> ROOMS = new HashMap<>();
    public  final  List<Reservation> RESERVATIONS = new ArrayList<>();

    public static final ReservationService reservationService = new ReservationService();


    public void addRoom(IRoom room) {

//        int sizeOfHotel = ROOMS.size();
//        IRoom iRoom = new Room(Integer.toString(sizeOfHotel+1), 2*Double.parseDouble(Integer.toString(sizeOfHotel)));
//        ROOMS.put(iRoom.getRoomNumber(),iRoom);
        ROOMS.put(room.getRoomNumber(),room);
    }


    public Map<String,IRoom> getRooms() { return ROOMS;}
    public IRoom getARoom(String roomId) {
        return ROOMS.get(roomId);
    }

    public Reservation reserveARoom (Customer customer, IRoom iRoom, LocalDate checkInLocalDate, LocalDate checkOutLocalDate) {
        Reservation reservation =  new Reservation(customer,iRoom,checkInLocalDate,checkOutLocalDate);
        RESERVATIONS.add(reservation);
        updateFreeRoom();
        return reservation;
    }

    public Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate) {
            valiDate(checkInDate,checkOutDate);
        updateFreeRoom();
            List<IRoom> roomList = new ArrayList<>();

            boolean tr;
            for(IRoom room: ROOMS.values()) {
                tr=true;
                for(Reservation reservation: RESERVATIONS) {
                        if(reservation.getMyRoom() == room) {
                            if(reservation.getCheckInDate().getDayOfMonth() <checkOutDate.getDayOfMonth() ) {tr = false;}
                        }
                }
                if(tr) {
                    roomList.add(room);
                }
            }

            return roomList;
    }

    public void valiDate(LocalDate checkInDate, LocalDate checkOutDate) {


        LocalDate currentDate = LocalDate.now();
       Period  diff = Period.between(checkInDate,checkOutDate);
        Period diff2 = Period.between(currentDate,checkInDate);

//       if(diff.getDays()<1) {
//           return false;
//       }
//       if( diff2.getDays()<1) {
//           return false;
//       }

        if(diff.getDays()<1 || diff2.getDays()<1) {
            throw new DateTimeException("Invalid checkIN checkOUT Date"+ currentDate + " " + checkInDate + " " + checkOutDate);
        }
    }

    public Collection<Reservation> getCustomerReservation() {
        return RESERVATIONS;
    }
    public List<Reservation> getCustomerReservation(Customer customer) {
        updateFreeRoom();
        List <Reservation> reservationList= new ArrayList<>();
        for(Reservation reservation: RESERVATIONS) {
            if(reservation.getCustomer()== customer) {
                reservationList.add(reservation);
            }
        }
        return reservationList;
    }


    private  void updateFreeRoom() {
        for(Reservation reservation: RESERVATIONS) {
            IRoom room = reservation.getMyRoom();
            LocalDate date = LocalDate.now();
            if( date.isBefore(reservation.getCheckInDate()) && reservation.getCheckOutDate().isBefore(date) ) {
                room.setFreeRoom(false);
            }
            else {
                room.setFreeRoom(true);
            }
        }
    }

    public void printAllReservation() {}

    public static ReservationService getReservationService() {


        return reservationService;
    }

}
