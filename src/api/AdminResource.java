package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.Loader;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

import static service.Loader.loadFakeData;

public class AdminResource {

    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    public static Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public static void addRoom(List<IRoom> rooms){
        for (IRoom room:rooms) {reservationService.addRoom(room);}
    }

    public static Collection<IRoom> getAllRooms(){
        return reservationService.getRoomList().values();
    }

    public static Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    public static void displayAllReservations() {
        reservationService.printAllReservation();
    }

    public static void loadFakeCustomers() throws Exception { loadFakeData();}
}
