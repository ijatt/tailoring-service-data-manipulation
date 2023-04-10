
public class TailoringService {
    private int custID;
    private String name;
    private String contactNo;
    private char textiles;
    private char design;
    private String orderDate;
    private char progress;
    private char payStatus;

    public TailoringService(int custID, String name, String contactNo, char textiles, char design, String orderDate, char progress, char payStatus){
        this.custID = custID;
        this.name = name;
        this.contactNo = contactNo;
        this.textiles = textiles;
        this.design = design;
        this.orderDate = orderDate;
        this.progress = progress;
        this.payStatus = payStatus;
    }

    public int getCustID() {return custID;}

    public String getName() {return name;}

    public String getContact() {return contactNo;}

    public char getTextiles() {return textiles;}

    public char getDesign() {return design;}

    public String getDate() {return orderDate;}

    public char getProgress() {return progress;}

    public char getPayStatus() {return payStatus;}

    public void setProgress(char progress) {this.progress = progress;}

    public void setPayStatus(char payStatus) {this.payStatus = payStatus;}

    public double payment() {
        double payment = 0.0;
        if(textiles == 'C' || textiles == 'c')
            payment += 40.0;
        else if(textiles == 'P' || textiles == 'p')
            payment += 30.0;
        else if(textiles == 'S' || textiles == 's')
            payment += 50.0;

        if(design == 'S' || design == 's')
            payment += 100.0;
        else if(design == 'P' || design == 'p')
            payment += 50.0;

        return payment;
    }

    public String textile() {
        if(textiles == 'C' || textiles == 'c')
            return "Cotton   ";
        else if(textiles == 'P' || textiles == 'p')
            return "Polyester";
        else if(textiles == 'S' || textiles == 's')
            return "Satin    ";
        else 
            return null;
    }

    public String design() {
        if(design == 'S' || design == 's')
            return "Shirt";
        else if(design == 'P' || design == 'p')
            return "Pants";
        else
            return null;
    }

    public String progress() {
        if(progress == 'A')
            return "Assigned   ";
        else if(progress == 'P' || progress == 'p')
            return "In Progress";
        else if(progress == 'C' || progress == 'c')
            return "Completed  ";
        else
            return null;
    }

    public String payStatus() {
        if(payStatus == 'N' || payStatus == 'n')
            return "Not Paid";
        else if(payStatus == 'P' || payStatus == 'p')
            return "Paid    ";
        else
            return null;
    }

    public String toString() {
        return custID + "\t\t" + name + "\t" + contactNo + "\t" + textile() + "\t" +
        design() + "\t" + orderDate + "\t" + progress() + "\tRM" + payment() + "\t\t" + payStatus();    
    }
}
