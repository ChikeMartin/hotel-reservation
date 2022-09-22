package service;

import model.IRoom;
import model.Room;
import model.RoomType;

import java.io.File;
import java.util.Scanner;

public class Loader {

    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    public static void loadFakeData() throws Exception {
        loadFakeData(null);
    }

    // Add Fake customers downloaded from https://generatedata.com/generator
    public static void loadFakeData(String type) throws Exception {
        int counter = 0;
        String fileName = "";
        String confirmationMessage = "";

        switch (type){
            case "Customers" -> {
                fileName = "random_customers";
                confirmationMessage = " customers have been registered.";
            }
            case "Rooms" -> {
                fileName = "random_rooms";
                confirmationMessage = " rooms have been added.";
            }
        }

        File file = new File("/Users/chike/code/hotel reservation/" + fileName + ".csv");
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        scanner.useDelimiter(",");

        while (scanner.hasNext()){
            if (type == "Customers") {
                String[] entry = scanner.nextLine().split(",");
                String firstName = entry[0];
                String lastName = entry[1]; //.substring(0, entry[1].length());
                String email = entry[2]; //.substring(0, entry[2].length());

                try {
                    customerService.addCustomer(email, firstName, lastName);
                    counter++;
                } catch (IllegalArgumentException e) {
                    continue;
                }
            } else if (type == "Rooms" || type == null) {
                String[] entry = scanner.nextLine().split(",");
                String roomID = entry[0];
                String roomTypeString = entry[1]; //.substring(0, entry[1].length());
                double price = Double.valueOf(entry[2]);

                RoomType roomType;
                
                switch (roomTypeString){
                    case "Yes" -> roomType = RoomType.DOUBLE;
                    case "No" -> roomType = RoomType.SINGLE;
                    default -> throw new IllegalStateException("Unexpected value: " + roomTypeString);
                }

                IRoom room = new Room(roomID, price, roomType);
                reservationService.addRoom(room);
                counter ++;
            }

        }
        scanner.close();
        System.out.println(counter + confirmationMessage);
    }
}
