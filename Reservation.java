import java.util.Date;

public class Reservation {
    private String guestName;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalAmount;

    public Reservation(String guestName, Room room, Date checkInDate, Date checkOutDate) {
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = calculateTotalAmount();
    }

    private double calculateTotalAmount() {
        long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
        long diff = diffInMillies / (1000 * 60 * 60 * 24);
        return diff * room.getPrice();
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
