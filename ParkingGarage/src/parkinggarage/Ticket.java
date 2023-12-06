
package parkinggarage;

public class Ticket {
    private static int ticketCount = 1;
    private int ticketNumber;
    
    public Ticket() {
        this.ticketNumber = ticketCount++;
    }
    
    public int getTicketNumber() {
        return ticketNumber;
    }
}
