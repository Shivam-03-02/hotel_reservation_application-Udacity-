import Model.Customer;
import Model.IRoom;
import Model.Room;
import Model.RoomType;
import api.AdminResource;

import java.util.*;

public class AdminMenu {

    public static void main() {
        AdminResource adminResource = AdminResource.getAdminResource();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to admin menu \n please select what you want to do 1. See all customers \n 2. See all rooms \n 3.See all reservations \n 4.Add a room \n 5.Back to Main menu");

            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1:
                    Collection<Customer> customerCollections = adminResource.getAllCustomers();
                    for(Customer customer: customerCollections) {
                        System.out.println(customer + "\n");
                    }
                    break;
                case 2:
                    Collection<IRoom> roomCollection = adminResource.getAllRooms();
                    for(IRoom room: roomCollection) {
                        System.out.println(room + "\n");
                    }
                    break;
                case 3:
                    adminResource.displayAllReservations();
                    break;
                case 4:
                    System.out.println("Please enter the total Rooms you want to add:");
                    List<IRoom> rooms = new ArrayList<>();
                    int total = scanner.nextInt();
                    scanner.nextLine();
                    while(total>0) {
                        System.out.println("Please input details of room");
                        System.out.println("please enter room Number:");
                        String roomNumber = scanner.nextLine();
                        System.out.println("Please enter room price");
                        Double roomPrice = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Please enter room-type Single or Double");
                        String roomTypeInput = scanner.nextLine().toUpperCase();

                        RoomType roomType = RoomType.valueOf(roomTypeInput);
                        IRoom room = new Room(roomNumber, roomPrice, roomType);
                        rooms.add(room);
                        total--;
                    }
                    adminResource.addRoom(rooms);
                    break;
                case 5:
                    MainMenu.main();
                    break;
            }
        }


    }
}
