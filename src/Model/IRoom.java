package Model;

public interface IRoom {
    abstract public String  getRoomNumber();
    abstract public Double getRoomPrice();
    abstract public RoomType getRoomType();
    abstract public boolean isFree();
    abstract public void setFreeRoom(boolean free);
}
