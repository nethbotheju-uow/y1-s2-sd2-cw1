import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char row;
    private int seat;

    private double price;

    private Person person;

    public Ticket(char row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public void setRow(char row){
        this.row = row;
    }

    public char getRow(){
        return row;
    }

    public void setSeat(int seat){
        this.seat = seat;
    }

    public int getSeat(){
        return seat;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public Person getPerson(){
        return person;
    }

    public void printTicketInfo(){
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);

        System.out.println("Person Details: ");
        person.printPersonInfo();
    }

    public void save(){
        String file_name = "Tickets\\"+row+seat+".txt";

        try {
            FileWriter myWriter = new FileWriter(file_name);
            myWriter.write("Row: " + row + "\nSeat: " + seat + "\nPrice: £" + price);

            myWriter.write("\n\nPerson Details: ");
            myWriter.write("\nName: " + person.getName() + "\nSurename: " + person.getSurename() + "\nEmail: " + person.getEmail());
            myWriter.close();
        }
        catch (IOException e){
            System.out.println("An error occurred." + e);
        }

    }
}
