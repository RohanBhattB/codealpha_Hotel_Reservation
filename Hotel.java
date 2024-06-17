import java.util.Date;

public class Hotel {
    private Room[] rooms;
    private Reservation[] reservations;
    private int roomCount;
    private int reservationCount;
    private static final int MAX_ROOMS = 100;
    private static final int MAX_RESERVATIONS = 100;

    public Hotel() {
        rooms = new Room[MAX_ROOMS];
        reservations = new Reservation[MAX_RESERVATIONS];
        roomCount = 0;
        reservationCount = 0;

        // Add some rooms to the hotel
        addRoom(new Room(101, "Single", 100));
        addRoom(new Room(102, "Double", 150));
        addRoom(new Room(103, "Suite", 300));
    }

    public void addRoom(Room room) {
        if (roomCount < MAX_ROOMS) {
            rooms[roomCount] = room;
            roomCount++;
        } else {
            System.out.println("Cannot add more rooms. Limit reached.");
        }
    }

    public Room[] searchRooms(String type) {
        Room[] availableRooms = new Room[MAX_ROOMS];
        int count = 0;
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].getType().equalsIgnoreCase(type) && rooms[i].isAvailable()) {
                availableRooms[count] = rooms[i];
                count++;
            }
        }
        return availableRooms;
    }

    public boolean makeReservation(String guestName, int roomNumber, Date checkInDate, Date checkOutDate) {
        Room roomToBook = null;
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].getRoomNumber() == roomNumber && rooms[i].isAvailable()) {
                roomToBook = rooms[i];
                break;
            }
        }

        if (roomToBook != null) {
            roomToBook.setAvailable(false);
            Reservation reservation = new Reservation(guestName, roomToBook, checkInDate, checkOutDate);
            reservations[reservationCount] = reservation;
            reservationCount++;
            return true;
        } else {
            return false;
        }
    }

    public Reservation[] getReservations() {
        return reservations;
    }
}
