import java.util.*;

public class PlaneManagement {

    private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public static void main(String [] args){
        System.out.println("Welcome to the Plane Management System.");

        int[][] seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];

        while(true){
            System.out.println("*************************************************");
            System.out.println("*                MENU OPTIONS                   *");
            System.out.println("*************************************************");
            System.out.println("     1) Buy a seat");
            System.out.println("     2) Cancel a seat");
            System.out.println("     3) Find first available seat");
            System.out.println("     4) Show seating plan");
            System.out.println("     5) Print tickets information and total sales");
            System.out.println("     6) Search ticket");
            System.out.println("     0) Quit");
            System.out.println("*************************************************\n");

            Scanner input = new Scanner(System.in);
            System.out.print("Please select an option: ");
            int option = input.nextInt();
            while (option < 0 || option > 6) {
                System.out.println("Please enter valid option.\n");
                System.out.print("Please select an option: ");
                option = input.nextInt();
            }

            switch (option) {
                case 1:
                    seats = buy_seat(seats);
                    break;
                case 2:
                    seats = cancel_seat(seats);
                    break;
                case 3:
                    find_first_available(seats);
                    break;
                case 4:
                    show_seating_plan(seats);
                    break;
                case 5:
                    print_tickets_info();
                    break;
                case 6:
                    search_ticket();
                    break;
                case 0:
                    System.exit(0);
            }
            System.out.println();
        }
    }

    public static int[][] buy_seat(int[][] seats){
        int[] inputs = inputs();

        int row_number = inputs[0];
        int column_number = inputs[1];

        if(seats[row_number][column_number] == 0){
            seats[row_number][column_number] = 1;
            System.out.println("Your seat has been booked!");

            Scanner input = new Scanner(System.in);
            System.out.println("Enter your name: ");
            String name = input.next();
            System.out.println("Enter your surename: ");
            String surename = input.next();
            System.out.println("Enter your email: ");
            String email = input.next();

            //Actual row letter and colomn number
            char row_letter = (char) ('A' + row_number);
            column_number += 1;

            int price = 0;
            if(column_number<6){
                price = 200;
            }
            else if(column_number<10){
                price = 150;
            }
            else{
                price = 180;
            }

            Person person = new Person(name, surename, email);
            Ticket ticket =  new Ticket(row_letter, column_number, price, person);

            tickets.add(ticket);
            ticket.save();
        }
        else{
            System.out.println("Seat has already sold!");
        }

        return seats;
    }

    public static int[][] cancel_seat(int[][] seats){
        int[] inputs = inputs();
        int row_number = inputs[0];
        int column_number = inputs[1];

        if(seats[row_number][column_number] == 1){
            seats[row_number][column_number] = 0;
            System.out.println("Your seat has been successfully canceled!");

            //Actual row letter and colomn number
            char row_letter = (char) ('A' + row_number);
            column_number += 1;

            for(int i =0; i<tickets.size(); i++){
                Ticket ticket = tickets.get(i);
                if(ticket.getRow() == row_letter && ticket.getSeat() == column_number){
                    tickets.remove(ticket);
                }
            }
        }
        else{
            System.out.println("Seat has already unsold!");
        }

        return seats;
    }

    public static void find_first_available(int[][] seats){
        boolean selected = false;

        int row = -1;
        int column = -1;
        for(int i =0; i<seats.length && !selected; i++){
            for(int j =0; j<seats[i].length && !selected; j++){
                if(seats[i][j] == 0){
                    row = i;
                    column = j+1;
                    selected = true;
                }
            }
        }

        char row_letter = (char) (65 + row);

        System.out.print(row_letter);
        System.out.println(column);
    }

    public static void show_seating_plan(int[][] seats){
        for(int i =0; i<seats.length; i++){
            for(int j =0; j<seats[i].length; j++){
                if(seats[i][j] == 0){
                    System.out.print("O");
                }
                else{
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    public static int[] inputs(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the row letter: ");
        char row_letter = input.next().charAt(0);
        row_letter = Character.toUpperCase(row_letter);
        while(row_letter != 'A' && row_letter != 'B' && row_letter != 'C' && row_letter != 'D'){
            System.out.println("Please enter valid row letter.\n");
            System.out.print("Enter the row letter: ");
            row_letter = input.next().charAt(0);
        }

        System.out.print("Enter the column number: ");
        int column_number = input.nextInt();
        if(row_letter == 'A' || row_letter == 'D'){
            while(column_number<=0 || column_number>14){
                System.out.println("Please enter valid column number (This row has only 14 seats).\n");
                System.out.print("Enter the column number: ");
                column_number = input.nextInt();
            }
        }
        else{
            while(column_number<=0 || column_number>12){
                System.out.println("Please enter valid column number (This row has only 12 seats).\n");
                System.out.print("Enter the column number: ");
                column_number = input.nextInt();
            }
        }

        int[] return_values = new int[2];

        return_values[0] = row_letter - 65; // char is ASCII value So I subtract 97 because simple a value is 97
        return_values[1] = column_number -1;

        return return_values;
    }

    public static void search_ticket(){
        int[] inputs = inputs();
        char row_letter = (char) (65+ inputs[0]);
        int column_number = inputs[1] +1;

        for(Ticket ticket: tickets){
            if (ticket.getRow() == row_letter && ticket.getSeat() == column_number) {
                ticket.printTicketInfo();
            }
        }
    }

    public static void print_tickets_info(){
        double sum =0;
        String print = "";

        for(Ticket ticket: tickets){
            sum += ticket.getPrice();
            print += "" + ticket.getRow() + ticket.getSeat() + " = £" + ticket.getPrice() + " + ";
        }
        print = print.substring(0,print.length() -3);
        System.out.println("£" + sum + " (" + print + ")");

    }
}