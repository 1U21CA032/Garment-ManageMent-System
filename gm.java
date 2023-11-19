package m;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class n {
    static void weight() {
        Scanner in = new Scanner(System.in);
        String[] size = {"S", "M", "L", "XL","XXL"};
        int[] Weightp = new int[size.length];
        int[] Nop = new int[size.length];
        System.out.println("Please enter a Max capacity of the box Weight");
        int MaxB=in.nextInt();
        int BoxNo = 0; 
        for (int i = 0; i < size.length; i++) {
            System.out.println("Enter Weight of One Piece in the Size of  " + size[i] + "");
            Weightp[i] = in.nextInt();
            System.out.println("Enter Total Number of Pieces in the size of  " + size[i]);
            Nop[i] = in.nextInt();
            int count = 0;
            int BoxWeight = 0;
            int NoBalance = Nop[i];
            while (NoBalance > 0) {
                if (Weightp[i] + BoxWeight <= MaxB) {
                    count++;
                    BoxWeight += Weightp[i];
                    NoBalance--;
                } else {
                    BoxNo++;
                    System.out.println("Number of pieces in the box: " + count + " Size: " + size[i] + " Box Number: " + BoxNo+" BoxWeight "+BoxWeight);
                    count = 0;
                    BoxWeight = 0;
                }
            }
            if (count > 0) {
            	BoxNo=BoxNo+1;
                System.out.println("Number of pieces in the last box: " + count + "| Size: " + size[i] + " Box Number: " + BoxNo);
            }
        }
    }
    static void StockManagement(){
    	Scanner Scan=new Scanner(System.in);
    	String[][] garments=new String[10][5];
    	while(true){
    		System.out.println("1.Add a new Garment Stock");
    		System.out.println("2.Update A Garment");
    		System.out.println("3.Fetching a details of Garment");
    		System.out.println("4.Exit");
    		System.out.println("Enter Your Option");
    		int option=Scan.nextInt();
    		switch(option){
    		case 1:
    			AddGarment(garments,Scan);
    			break;
    		case 2:
    			UpdateGarment(garments,Scan);
    			break;
    		case 3:
    			Details(garments);
    			break;
    		case 4:
    			exit();
    			break;
    		default:
    			System.out.println("Enter a Valid Option");
    		}
    		break;
    	}
    }
    static void AddGarment(String garments [][],Scanner Scan){
    	 System.out.print("Enter garment name: ");
         String name =Scan.next();
         System.out.print("Enter initial stock quantity: ");
        int stock = Scan.nextInt();

         for (int i = 0; i < garments.length; i++) {
             if (garments[i][0] == null) {
                 garments[i][0] = name;
                 garments[i][1] = String.valueOf(stock);
                 System.out.println(name + " added to stock with initial quantity " + stock);
                 return;
             }
         }

         System.out.println("Garment stock is full. Cannot add more garments.");
    }
    static void UpdateGarment(String [][]garments,Scanner Scan){
    	System.out.print("Enter garment name to update stock: ");
        String name = Scan.next();

        for (int i = 0; i < garments.length; i++) {
            if (name.equals(garments[i][0])) {
                System.out.print("Enter new stock quantity: ");
                int newStock = Scan.nextInt();
                garments[i][1] = String.valueOf(newStock);
                System.out.println(name + " stock updated to " + newStock);
                return;
            }
        }

        System.out.println("Garment not found in stock.");
    }
    static void Details(String[][]garments){
    	System.out.println("\nCurrent Stock Status:");
        System.out.printf("%-20s %-20s\n", "Garment Name", "Stock Quantity");

        for (String[] garment : garments) {
            if (garment[0] != null) {
                System.out.printf("%-20s %-20s\n", garment[0], garment[1]);
            }
        }
    }
    static void EmployeDetails(){
    	System.out.println("----Employee Details-----");
		System.out.println("1.Add Employee Details");
		System.out.println("2.Fetching all Employee Details");
		System.out.println("3.Fetching Specific Employee Details");
		System.out.println("4.Exit");
		Scanner Sc=new Scanner(System.in);
		System.out.println("Enter our Choice");
		int ch=Sc.nextInt();
		
		switch(ch){
		case 1:
			employee();
			break;
		case 2:
			displayAllEmp();
			break;
		case 3:
			selectAnSE();
			break;
		case 4:
//			exit();
			break;
		}
    }
    static void employee(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a employee id");
		int empid=sc.nextInt();
		System.out.println("Enter a employee name");
		String empName=sc.next();
		System.out.println("Enter a number of days present");
		int empd=sc.nextInt();
		System.out.println("Enter a Salary for one day");
		int oneSl=sc.nextInt();
		int salary=empd*oneSl;
		
		sql(empid,empName,salary);
	}
    static void sql(int empid,String empName,int salary){
		try{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement st=con.createStatement();
			PreparedStatement ps=con.prepareStatement("INSERT INTO employes(id,name,salary)VALUES(?,?,?)");
			ps.setInt(1,empid);
			ps.setString(2,empName);
			ps.setInt(3,salary);
			ps.execute();
			System.out.println("SucessFully Added an Employe into An table Id:"+empid+" name:"+empName+" salary:"+salary);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
    static void displayAllEmp(){
		try{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from employes");
			while(rs.next()){
				System.out.print("id: "+rs.getInt("id"));
				System.out.print(" name: "+rs.getString("name"));
				System.out.println(" salary: "+rs.getInt("salary"));
			}
		}
		catch(Exception e){
			
		}
	}
    static void selectAnSE(){
		Scanner Sc=new Scanner(System.in);
		System.out.println("Enter an Employe Id");
		int ei=Sc.nextInt();
		try{
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement st=con.createStatement();
			PreparedStatement ps=con.prepareStatement("SELECT * FROM employes WHERE id=?");
			ps.setInt(1, ei);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				System.out.println("id :"+rs.getInt("id"));
				System.out.println("Name :"+rs.getString("name"));
				System.out.println("Salary"+rs.getInt("salary"));
			}
			else{
				System.out.println("ID Not Found");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
    }
    
    static void exit(){
    	System.out.println("Thankyou");
    }
    public static void main(String[] args) {
    	while(true){
    		Scanner scan=new Scanner(System.in);
    		System.out.println("\n Garment ManageMent System");
    		System.out.println("1.Stock ManageMent");
    		System.out.println("2.Making a Packing List");
    		System.out.println("3.Employe Details");
    		System.out.println("4.Exit");
    		System.out.println("Please Enter A Option");
    		int choice=scan.nextInt();
    		switch(choice){
    		case 1:
    			StockManagement();
    			break;
    		case 2:
    			weight();
    			break;
    		case 3:
    			EmployeDetails();
    			break;
    		case 4:
    			break;
    		}
        	System.out.println("Thanks for Using GramentManagent System App");
    	}
    	
    }
}
