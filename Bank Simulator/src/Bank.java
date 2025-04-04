import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;



public class Bank{
static double amt = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File myFile = new File("Account.txt");
        int regOrLog;
        int selection;
        boolean running = true;

        //Creation of bank file
        if(myFile.exists())
            System.out.println("Using Created File\n");
        else{
            System.out.println("File Doesn't Exist");
            System.exit(1);
        }

        //Creation for account and login for bank account
        System.out.print("Would You like to Create an Account Or Login (1: Create, 2: Login): ");
        regOrLog = scanner.nextInt();
        scanner.nextLine();

        if(regOrLog == 1)
            Register(scanner, myFile);
        else if(regOrLog == 2)
            Login(scanner, myFile);

        System.out.println("*************************");
        System.out.println("Welcome To Cherritte Bank");
        System.out.println("*************************");

        System.out.println("*****************");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawl");
        System.out.println("3. Check Amount");
        System.out.println("4. Exit");
        System.out.println("*****************");

        //Selection options
        while(running){
            System.out.println("\n*************************");
            System.out.print("Your Selection: ");
            selection = scanner.nextInt();
            System.out.println("\n*************************");

            if(selection == 1)
                Deposit(scanner);
            else if(selection == 2)
                Withdrawl(scanner);
            else if(selection == 3){
                Amount();
            }
            else if(selection == 4){
                System.out.println("*****************************************");
                System.out.println("You have succesfully left Cherritte Bank");
                System.out.println("Have A Great Day!");
                System.out.println("*****************************************");

                running = false;
            }
            else {
                System.err.println("*************************");
                System.err.println("Invalid Choice, Try Again");
                System.err.println("*************************");
            }
        }
        scanner.close();
    }

    //Registration method
    public static void Register(Scanner scanner, File myFile) {
        String email, password;

        System.out.print("\nEnter Your Email: ");
        email = scanner.nextLine();
        System.out.print("Enter Your Password: ");
        password = scanner.nextLine();

        try(FileWriter myWriter = new FileWriter(myFile, true)){
            myWriter.write(email + "\n");
            myWriter.write(password + "\n");

        } catch (IOException e) {
            System.out.println("Error Writing to file");
        }
    }

    //Login method
    public static void Login(Scanner scanner, File myFile) throws IOException{
        String email, password;
        boolean proceed = true;

        while(proceed){
            System.out.print("\nEnter Your Email: ");
            email = scanner.nextLine();

            try(Scanner reader = new Scanner(myFile)){
                while(reader.hasNextLine()){
                    if(Objects.equals(email, reader.nextLine())){
                        proceed = false;
                        System.out.println("\nEmail Correct");
                    }
                }
                if(proceed){
                    System.err.println("Wrong Email, Try Again");
                }
            }
        }
        proceed = true;

        while(proceed){
            System.out.print("\nEnter Your Password: ");
            password = scanner.nextLine();

            try(Scanner reader = new Scanner(myFile)){
                while(reader.hasNextLine()){
                    if(Objects.equals(password, reader.nextLine())){
                        proceed = false;
                        System.out.println("\nPassword Correct");
                    }
                }
                if(proceed){
                    System.err.println("Wrong Password, Try Again");
                }
            }
        }
    }

    //For deposits
    public static void Deposit(Scanner scanner){
        double total = 0;
        System.out.println("***************************************");
        System.out.print("How Much Would You Like To Deposit: ");
        total = scanner.nextInt();
        System.out.println("***************************************");

        System.out.println("\n*****************************");
        System.out.printf("You Have Deposited: $" + total);
        System.out.println("\n*****************************");

        amt += total;

    }

    //For withdrawl
    public static void Withdrawl(Scanner scanner){
        int num = 0;
        boolean worked = true;

        while(worked){
            System.out.println("***********************************************");
            System.out.println("How Much Money Would You Like To Withdrawl: ");
            num = scanner.nextInt();
            System.out.println("***********************************************");

            if ((amt - num <= 0) || (num <= 0)){
                System.err.println("Error, Please Try Again");
            }
            else if((amt - num > 0) && (num > 0)){
                System.out.println("Transaction succesfull");
                worked = false;
            }
        }

        System.out.println("\n**********************************");
        System.out.println("You Have Withdrawled: $" + num);
        System.out.println("**********************************");

        amt -= num;

    }

    //For checking account
    public static void Amount(){
        System.out.println("************************");
        System.out.println("You Currently Have: $" + amt);
        System.out.println("************************");
    }
}