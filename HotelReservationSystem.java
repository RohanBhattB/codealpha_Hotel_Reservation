import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room type (Single/Double/Suite): ");
                    String type = scanner.next();
                    Room[] availableRooms = hotel.searchRooms(type);
                    if (availableRooms.length == 0) {
                        System.out.println("No available rooms of type " + type);
                    } else {
                        for (Room room : availableRooms) {
                            if (room != null) {
                                System.out.println("Room Number: " + room.getRoomNumber() + ", Price: " + room.getPrice());
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String guestName = scanner.next();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter check-in date (dd/MM/yyyy): ");
                    String checkInDateStr = scanner.next();
                    System.out.print("Enter check-out date (dd/MM/yyyy): ");
                    String checkOutDateStr = scanner.next();

                    try {
                        Date checkInDate = dateFormat.parse(checkInDateStr);
                        Date checkOutDate = dateFormat.parse(checkOutDateStr);

                        if (hotel.makeReservation(guestName, roomNumber, checkInDate, checkOutDate)) {
                            System.out.println("Reservation successful!");
                        } else {
                            System.out.println("Failed to make reservation.");
                        }
                    } catch (ParseException e) {
                        System.out.println("Invalid date format.");
                    }
                    break;
                case 3:
                    Reservation[] reservations = hotel.getReservations();
                    for (Reservation reservation : reservations) {
                        if (reservation != null && reservation.getRoom() != null) {
                            System.out.println("Guest Name: " + reservation.getGuestName() +
                                    ", Room Number: " + reservation.getRoom().getRoomNumber() +
                                    ", Check-in Date: " + dateFormat.format(reservation.getCheckInDate()) +
                                    ", Check-out Date: " + dateFormat.format(reservation.getCheckOutDate()) +
                                    ", Total Amount: " + reservation.getTotalAmount());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
