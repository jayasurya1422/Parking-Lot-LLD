import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class AutomatedSystem {
    private int id;

    private ParkingLot parkingLot;
    HashMap<Integer,Ticket> currentTickets;

    public AutomatedSystem(int id){
        this.id = id;
        currentTickets = new HashMap<>();
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    private ParkingSpot fetchAvailableSpot(){
        return this.parkingLot.getAvailableSpot();
    }

    public Ticket generateTicket(Customer customer){
        ParkingSpot availableSpot = fetchAvailableSpot();
        Vehicle vehicle = customer.getVehicle();
        Ticket ticket = new Ticket(vehicle,availableSpot);
        currentTickets.put(ticket.getId(),ticket);
        return ticket;
    }
    public Ticket scanTicket(){
        return currentTickets.get(1234);
    }

    public double caluclateCharges(){
         Ticket ticket= scanTicket();
         long duration = Duration.between(LocalDateTime.now(),ticket.getArrivalTime()).toHours();
         double charges = duration * parkingLot.getParkingCharges();
         return charges;
    }

    public void openEntryBarrier(){
        this.parkingLot.getDisplayBoard().update(Status.FULL);
    }
    public void closeEntryBarrier(){
    }
    public void openExitBarrier(){
        this.parkingLot.getDisplayBoard().update(Status.AVAILABLE);
    }
    public void closeExitBarrier(){
    }

}