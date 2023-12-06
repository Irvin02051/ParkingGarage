
package parkinggarage;


public class Car<T> {
    private Ticket ticket;
    private T owner;
    
    public Car(Ticket ticket, T owner) {
        this.ticket = ticket;
        this.owner = owner;
    }
    public Ticket getTicket() {
        return ticket;
    }
    
    public T getOwner() {
        return owner;
    }
}
