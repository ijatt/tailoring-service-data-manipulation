import java.io.*;
import java.util.*;
//markah full madam ^_^
public class TestLinkedList {
    //declaring global variables
    static Scanner scan = new Scanner(System.in);
    static LinkedList serviceList = new LinkedList();
    static LinkedList completedList = new LinkedList();
    static String choice;

    public static void main(String args[]) throws InterruptedException {
        menu(); 
    }

    public static void menu() throws InterruptedException {
        System.out.print('\u000C');
        System.out.println("=============================================================");
        System.out.println("*                 ILYANA TAILORING SERVICE                  *");
        System.out.println("=============================================================");
        System.out.println("* A. Load all tailoring service data                        *");
        System.out.println("* B. Display all tailoring service data                     *");
        System.out.println("* C. Display all completed service data                     *");
        System.out.println("* D. Update tailoring service data                          *");
        System.out.println("* E. Move completed tailoring service to completedList      *");
        System.out.println("* F. Calculate total payment for all tailoring service      *");
        System.out.println("* G. Quit Program                                           *");
        System.out.println("=============================================================");
        System.out.println();
        System.out.println("Choose of the options. ");

        do {
            System.out.println();
            System.out.print("Choice : ");
            choice = scan.next();
            String selection = choice.toLowerCase();

            switch(selection) {
                case "a":
                    loadData();
                    break;
                case "b":
                    display();
                    break;
                case "c":
                    display();
                    break;
                case "d":
                    updateDetails();
                    break;
                case "e" :
                    removeOrder();
                    break;
                case "f":
                    calcPayment();
                    break;
                case "g":
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid input! Please re-enter");

            }

        }
        while(!(choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("b") || choice.equalsIgnoreCase("c") ||
            choice.equalsIgnoreCase("d") || choice.equalsIgnoreCase("e") || choice.equalsIgnoreCase("f") ||
            choice.equalsIgnoreCase("g"))); //loop will not stop until the user enter the right input
    }

    public static void loadData() throws InterruptedException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("tailoringInfo.txt"));
            String inData = null;

            while((inData = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inData, ";");
                int ID = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                String contact = st.nextToken();
                char textile = st.nextToken().charAt(0);
                char design = st.nextToken().charAt(0);
                String order = st.nextToken();
                char progress = st.nextToken().charAt(0);
                char payStatus = st.nextToken().charAt(0);

                serviceList.addBack(new TailoringService(ID, name, contact, textile, design, order, progress, payStatus));
            }
            br.close();
        }
        catch(FileNotFoundException fnfe) {
            System.out.println("File not found!");
        }
        catch(IOException ioe) {

        }
        System.out.println();
        load();
        menu();
    }

    public static void table() {
        System.out.println("\nCustomer ID\tCustomer Name\tContact Number\tTextile\t\tDesign\tDate of Order\tProgress\tAmount Due\tPayment Status");
        System.out.println("======================================================================================================================================");
    }
    //display element is the linkedlist
    public static void display() throws InterruptedException {
        System.out.print('\u000C');
        table();
        if(choice.equalsIgnoreCase("B")) {
            serviceList.print();
            System.out.println("\nTotal of all service in the list: " + serviceList.size());
        }
        else if(choice.equalsIgnoreCase("C")) {
            completedList.print();
            System.out.println("\nTotal of all service in the list: " + completedList.size());
        }
        System.out.println();
        mainMenu();
    }

    public static void updateDetails() throws InterruptedException {
        System.out.print('\u000C');
        int choice2;
        System.out.println("\nPlease select category to be updated.");
        System.out.println("=============================================================");
        System.out.println("* 1. Tailoring Progress                                     *");
        System.out.println("* 2. Payment Status                                         *");
        System.out.println("=============================================================");
        System.out.println();
        do {
            System.out.print("Update Choice : "); //prompt user to choose which attribute to update
            choice2 = scan.nextInt();

            switch (choice2) {
                case 1:
                    progressUpdate();
                    break;
                case 2:
                    payUpdate();
                    break;
                default:
                    System.out.println("Wrong input! Ple re-enter.");
            }
        }
        while(!(choice2 == 1 || choice2 == 2));
        mainMenu();
    }
    //search and update the progress
    public static void progressUpdate() throws InterruptedException{
        System.out.print('\u000C');
        table();
        serviceList.print();
        boolean found = false;
        System.out.print("\nPlease enter the customer ID to update: ");
        int ID = scan.nextInt();
        TailoringService temp = null;
        TailoringService t = serviceList.getHead();
        while(t != null){
            if(t.getCustID() == ID) {
                temp = t;
                found = true;    
            }
            t = serviceList.getNext();
        }
        if(found) {
            System.out.print("Please enter the new progress: ");
            char progress = scan.next().charAt(0);
            temp.setProgress(progress);
            System.out.println("The progress has been updated!");
        }
        else {
            System.out.println("Customer is not on the service list! Please re-enter."); Thread.sleep(3000);
            progressUpdate(); //call the method untill the user enter the right cust ID
        }    
    }
    //search and update the payment status
    public static void payUpdate() throws InterruptedException{
        System.out.print('\u000C');
        table();
        serviceList.print();
        boolean found = false;
        System.out.print("\nPlease enter the customer ID to update: ");
        int ID = scan.nextInt();
        TailoringService temp = null;

        TailoringService t = serviceList.getHead();
        while(t != null){
            if(t.getCustID() == ID) {
                temp = t;
                found = true;    
            }
            t = serviceList.getNext();
        }
        if(found) {
            System.out.print("Please enter the new payment status");
            char payment = scan.next().charAt(0);
            temp.setPayStatus(payment);
            System.out.println("The payment status has been updated!");
        }
        else{
            System.out.println("Customer is not on the service list! Please re-enter."); Thread.sleep(3000);
            payUpdate(); //call the method untill the user enter the right cust ID
        }
    }
    // remove completed orders from serviceList and store into completedList
    public static void removeOrder() throws InterruptedException {
        System.out.print('\u000C');
        TailoringService temp = null;
        TailoringService t = serviceList.getHead();
        while(t != null) {
            if(t.getProgress() == 'C' || t.getProgress() == 'c') {
                temp = serviceList.remove(t);
                completedList.addFront(temp);
            }
            t = serviceList.getNext();
        }
        System.out.println("Deleting completed orders...");
        for(int i = 0; i < 70; i++) {
            System.out.print("*");
            Thread.sleep(50);
        }
        System.out.println("\nCompleted Orders have Been Removed!");
        System.out.println();
        mainMenu();
    }
    
    public static void calcPayment() throws InterruptedException {
        System.out.print('\u000C');
        double sPay = 0.0; int satin = 0;
        double pPay = 0.0; int poly = 0;
        double cPay = 0.0; int cot = 0;
        double paid = 0.0; double nPaid = 0.0;
        double tPay = 0.0;
        
        TailoringService t = serviceList.getHead();
        while(t != null) {
            if(t.getTextiles() == 'S'){
                sPay += t.payment();
                satin++;
            }
            else if(t.getTextiles() == 'P'){
                poly++;
                pPay += t.payment();
            }
            else if(t.getTextiles() == 'C'){
                cot++;
                cPay += t.payment();
            }
            if(t.getPayStatus() == 'N' || t.getPayStatus() == 'n'){ 
                nPaid += t.payment();//doing in serviceList because it may has not paid orders while completedList all orders must paid
            } 
            t = serviceList.getNext();
        }
        
        t = completedList.getHead(); 
        while(t != null) {
            if(t.getTextiles() == 'S'){
                sPay += t.payment();
                satin++;
            }
            else if(t.getTextiles() == 'P'){
                poly++;
                pPay += t.payment();
            }
            else if(t.getTextiles() == 'C'){
                cot++;
                cPay += t.payment();
            }
            t = completedList.getNext();
        }
        tPay = sPay + pPay + cPay;
        paid = tPay - nPaid; 
        System.out.println("+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println("|  DESCRIPTION  |  QUANTITY  |  TOTAL AMOUNT  |"); Thread.sleep(50);
        System.out.println("+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println("|     SATIN     |      " + satin + "     |    RM" + sPay + "     |"); Thread.sleep(50);
        System.out.println("+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println("|   POLYESTER   |      " + poly + "    |    RM" + pPay + "    |"); Thread.sleep(50);
        System.out.println("+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println("|    COTTON     |      " + cot + "     |    RM" + cPay + "     |"); Thread.sleep(50);
        System.out.println("+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println("|                      Total    : RM" + tPay + "    |"); Thread.sleep(50);
        System.out.println("|                      Paid     : RM" + paid + "    |"); Thread.sleep(50);
        System.out.println("|                      Not Paid : RM" + nPaid + "     |"); Thread.sleep(50);
        System.out.println("-----------------------------------------------"); Thread.sleep(50);
        mainMenu();
    }
    // saje bagi nampak lawa
    public static void load() throws InterruptedException {
        System.out.println("Loading all tailoring services information...");
        for(int i = 0; i < 70; i++){
            System.out.print("*");
            Thread.sleep(50);
        }
        System.out.print("\nDone!");
        Thread.sleep(2000);
    }

    public static void mainMenu() throws InterruptedException{
        System.out.print("Back to main menu? (y/n)");
        char back = scan.next().charAt(0);
        if(back == 'y' || back == 'Y')
            menu();
        else
            System.out.print("\nProgram Terminated.");
    }
}