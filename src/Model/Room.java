package Model;

public class Room implements IRoom{
    String roomNumber;
    Double roomPrice;
    RoomType roomtype;
    boolean free;


    public Room() {}
    public Room(String roomNumber, Double roomPrice, RoomType roomtype) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomtype = roomtype;
        free = true;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }


    @Override
    public RoomType getRoomType() {
        return roomtype;
    }

    @Override
    public boolean isFree() {
        return free;
    }

    @Override
    public void setFreeRoom(boolean free) {
        this.free = free;
    }


    @Override
    public String toString() {
        return "Room Number:" + roomNumber + " Room Price" + roomPrice.toString() + " Room Type: " + roomtype.name();
    }
}
