import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import Model.Customer;
import Model.IRoom;
import Model.Reservation;
import api.*;

public class MainMenu {


    public static void main() {
        HotelResource hotelResource = HotelResource.getHotelResource();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); // Match your desired format
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Welcome to hotel reservation ");

        System.out.println("Please Select one of below option number");
        boolean reccuring = true;
while(reccuring) {
    System.out.println(" 1. Find and reserve A room. \n 2. See my reservation \n 3.Create an account \n 4.Admin \n 5. exit ");

    int selection = scanner.nextInt();
    scanner.nextLine();
    String email, firstName, lastName;
    switch (selection) {
        case 1:
            String customerEmail ;
            System.out.println("Please Enter you email:");
            customerEmail =scanner.nextLine();
            System.out.println("Please enter the checkin date in(yyyy/mm/dd):");
            String checkInDateInput = scanner.nextLine();
            System.out.println("Please enter the checkOut date in(yyyy/mm/dd):");
            String checkOutDateInput = scanner.nextLine();
            LocalDate checkInDate,checkOutDate;
            try {
                 checkInDate = LocalDate.parse(checkInDateInput,formatter);
                 checkOutDate = LocalDate.parse(checkOutDateInput,formatter);
                 Collection<IRoom> rooms = hotelResource.findARoom(checkInDate,checkOutDate);
                 if(rooms.isEmpty()){  rooms = hotelResource.findARoom(checkInDate.plusDays(7),checkOutDate.plusDays(7)); checkInDate.plusDays(7); checkOutDate.plusDays(7);}
                 if(rooms.isEmpty()) { System.out.println("No rooms found Please try again");}
                 else {
                     System.out.println("Rooms Available: \n");
                     for (IRoom room : rooms) {
                         System.out.println(room + "\n");
                     }
                     System.out.println("Please enter a room number to select");
                     String roomNumberSelected = scanner.nextLine();
                     IRoom room = hotelResource.getRoom(roomNumberSelected);
                     Reservation reservation = hotelResource.bookARoom(customerEmail,room,checkInDate,checkOutDate);
                     System.out.println("Your reservation :\n");
                     System.out.println(reservation);
                 }
            } catch (DateTimeException e) {
                System.out.println("Invalid Date please try again");
            }
            catch (NullPointerException ne) {
                System.out.println("Invaidate Room selected");
            }


            break;
        case 2:
            System.out.println("Please enter your Email");
            email = scanner.nextLine();
            List<Reservation> reservations = hotelResource.getCustomerReservations(email);

            for (Reservation reservation : reservations) {
                System.out.println(reservation + "\n");
            }
            break;

        case 3:
            System.out.println("Please enter you firstName");
            firstName = scanner.nextLine();
            System.out.println("Please enter you lastName");
            lastName = scanner.nextLine();
            System.out.println("Please enter you email");
            email = scanner.nextLine();
            hotelResource.createACustomer(email, firstName, lastName);
            break;
        case 4:
            AdminMenu.main();
            break;

        case 5:
            reccuring=false;
            break;
    }

}
    }
}
