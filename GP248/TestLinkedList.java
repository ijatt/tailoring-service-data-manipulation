import java.io.*;
import java.util.*;
//markah full madam ^_^
public class TestLinkedList {
    //declaring global variables
    static Scanner scan = new Scanner(System.in);
    static LinkedList serviceList = new LinkedList();
    static LinkedList completedList = new LinkedList();
    static String choice;
    static String s = "\t\t\t\t\t\t\t\t\t";
    static String t = "\t\t\t\t\t";
    
    public static void main(String args[]) throws InterruptedException {
        menu(); 
    }
    
    public static void banner(){
        System.out.println(t + "======================================================================================================================================");
        System.out.println(t + "*                                                    ILYANA TAILORING SERVICE                                                        *");
        System.out.println(t + "======================================================================================================================================");
        System.out.println();
    }
    
    public static void menu() throws InterruptedException {
        System.out.print('\u000C');
        banner();
        System.out.println(s + "=============================================================");
        System.out.println(s + "*                         MAIN MENU                         *");
        System.out.println(s + "=============================================================");
        System.out.println(s + "* A. Load all tailoring services data                       *");
        System.out.println(s + "* B. Display all tailoring services data                    *");
        System.out.println(s + "* C. Display all completed tailoring services data          *");
        System.out.println(s + "* D. Update tailoring services data                         *");
        System.out.println(s + "* E. Move completed tailoring services to completedList     *");
        System.out.println(s + "* F. Calculate total payment for all tailoring services     *");
        System.out.println(s + "* G. Quit Program                                           *");
        System.out.println(s + "=============================================================");
        System.out.println();
        System.out.println(s + "Choose of the options. ");

        do {
            System.out.println();
            System.out.print(s + "Choice : ");
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
                    System.out.println(s + "Invalid input! Please re-enter");

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
        System.out.println("\n" + t +"Customer ID\tCustomer Name\tContact Number\tTextile\t\tDesign\tDate of Order\tProgress\tAmount Due\tPayment Status");
        System.out.println(t + "======================================================================================================================================");
    }
    //display element is the linkedlist
    public static void display() throws InterruptedException {
        System.out.print('\u000C');
        banner();
        table();
        if(choice.equalsIgnoreCase("B")) {
            if(serviceList.size() == 0){
                System.out.println(t + "The list is empty. Please select option A in the menu first!"); Thread.sleep(2000);
            }
            else {
            serviceList.print();
            System.out.println("\n" + t + "Total of all service in the list: " + serviceList.size());
            }
        }
        else if(choice.equalsIgnoreCase("C")) {
            if(completedList.size() == 0){
                System.out.println(t + "The list is empty. Please select option E in the menu first!"); Thread.sleep(2000);
            }
            else{
            completedList.print();
            System.out.println("\n" + t + 
            "Total of all service in the list: " + completedList.size());
            }
        }
        System.out.println();
        mainMenu();
    }

    public static void updateDetails() throws InterruptedException {
        System.out.print('\u000C');
        banner();
        int choice2;
        System.out.println("\n" + s + "Please select category to be updated.");
        System.out.println(s + "=============================================================");
        System.out.println(s + "* 1. Tailoring Progress                                     *");
        System.out.println(s + "* 2. Payment Status                                         *");
        System.out.println(s + "=============================================================");
        System.out.println();
        do {
            System.out.print(s + "Update Choice : "); //prompt user to choose which attribute to update
            choice2 = scan.nextInt();

            switch (choice2) {
                case 1:
                    progressUpdate();
                    break;
                case 2:
                    payUpdate();
                    break;
                default:
                    System.out.println(s + "Wrong input! Ple re-enter.");
            }
        }
        while(!(choice2 == 1 || choice2 == 2));
        mainMenu();
    }
    //search and update the progress
    public static void progressUpdate() throws InterruptedException{
        System.out.print('\u000C');
        banner();
        table();
        serviceList.print();
        boolean found = false;
        System.out.print("\n" + t + "Please enter the customer ID to update: ");
        int ID = scan.nextInt();
        TailoringService temp = null;
        TailoringService ts = serviceList.getHead();
        while(ts != null){
            if(ts.getCustID() == ID) {
                temp = ts;
                found = true;    
            }
            ts = serviceList.getNext();
        }
        if(found) {
            System.out.print(t + "Please enter the new progress [A - Assigned | P - In Progress | C - Completed]: ");
            char progress = scan.next().charAt(0);
            temp.setProgress(progress);
            System.out.println(t + "The progress has been updated!");
        }
        else {
            System.out.println(t + "Customer is not on the service list! Please re-enter."); Thread.sleep(3000);
            progressUpdate(); //call the method untill the user enter the right cust ID
        }    
    }
    //search and update the payment status
    public static void payUpdate() throws InterruptedException{
        System.out.print('\u000C');
        banner();
        table();
        serviceList.print();
        boolean found = false;
        System.out.print("\n" + t + "Please enter the customer ID to update: ");
        int ID = scan.nextInt();
        TailoringService temp = null;

        TailoringService ts = serviceList.getHead();
        while(ts != null){
            if(ts.getCustID() == ID) {
                temp = ts;
                found = true;    
            }
            ts = serviceList.getNext();
        }
        if(found) {
            System.out.print(t + "Please enter the new payment status [P - Paid | N - Not Paid]: ");
            char payment = scan.next().charAt(0);
            temp.setPayStatus(payment);
            System.out.println(t + "The payment status has been updated!");
        }
        else{
            System.out.println(t + "Customer is not on the service list! Please re-enter."); Thread.sleep(3000);
            payUpdate(); //call the method untill the user enter the right cust ID
        }
    }
    // remove completed orders from serviceList and store into completedList
    public static void removeOrder() throws InterruptedException {
        System.out.print('\u000C');
        banner();
        TailoringService temp = null;
        TailoringService ts = serviceList.getHead();
        while(ts != null) {
            if(ts.getProgress() == 'C' || ts.getProgress() == 'c') {
                temp = serviceList.remove(ts);
                completedList.addFront(temp);
            }
            ts = serviceList.getNext();
        }
        System.out.println(s + "Deleting completed orders...");
        System.out.print(s);
        for(int i = 0; i < 70; i++) {
            System.out.print("*");
            Thread.sleep(50);
        }
        System.out.println("\n" + s + "Completed Orders have Been Removed!");
        System.out.println();
        mainMenu();
    }
    
    public static void calcPayment() throws InterruptedException {
        System.out.print('\u000C');
        banner();
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
        System.out.println(s + "\t+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println(s + "\t|  DESCRIPTION  |  QUANTITY  |  TOTAL AMOUNT  |"); Thread.sleep(50);
        System.out.println(s + "\t+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println(s + "\t|     SATIN     |      " + satin + "     |    RM" + sPay + "     |"); Thread.sleep(50);
        System.out.println(s + "\t+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println(s + "\t|   POLYESTER   |      " + poly + "    |    RM" + pPay + "    |"); Thread.sleep(50);
        System.out.println(s + "\t+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println(s + "\t|    COTTON     |      " + cot + "     |    RM" + cPay + "     |"); Thread.sleep(50);
        System.out.println(s + "\t+---------------+------------+----------------+"); Thread.sleep(50);
        System.out.println(s + "\t|                      Total    : RM" + tPay + "    |"); Thread.sleep(50);
        System.out.println(s + "\t|                      Paid     : RM" + paid + "    |"); Thread.sleep(50);
        System.out.println(s + "\t|                      Not Paid : RM" + nPaid + "     |"); Thread.sleep(50);
        System.out.println(s + "\t+---------------------------------------------+"); Thread.sleep(50);
        mainMenu();
    }
    // saje bagi nampak lawa
    public static void load() throws InterruptedException {
        System.out.println(s + "Loading all tailoring services information...");
        System.out.print(s);
        for(int i = 0; i < 61; i++){
            System.out.print("*");
            Thread.sleep(50);
        }
        System.out.print("\n" + s + "Done!");
        Thread.sleep(2000);
    }

    public static void mainMenu() throws InterruptedException{
        System.out.print(t + "Back to main menu? (y/n)");
        char back = scan.next().charAt(0);
        if(back == 'y' || back == 'Y')
            menu();
        else
            System.out.print("\n" + t + "Program Terminated.");
    }
}