import java.util.*;

public class OnlineReservationSystem {
        
    static Scanner sc=new Scanner(System.in);
    //Creating Object for class Tickets
    static Tickets[] tickets=new Tickets[50]; //Storing 50 tickets
    static int ticketCount=0; //counting number of tickets

    //Train DataBase 
    static int[] trainNumbers ={101,102,103,104}; // Specified Trains
    static String[] trainNames={"Chennai Exp","Mumbai Exp","Howrah Exp","Vande Bharat"}; //Above 4 trains Names 

                //Now creating function for Reservation 
                static void reserveTicket()
                    {
                        System.out.println("---Reservation Centre---");
                        //New object To entered by the User
                    

                        System.out.print("Enter Name of Passenger: ");
                        String Name=sc.nextLine();
                        if(Name.isEmpty() || Name.matches("[a-zA-Z]+"))
                        {
                            System.out.println("Invalid Name . Reservation Cancelled");
                            return;
                        }

                        System.out.print("Enter Train Numbers from (101-104): ");
                        int TrainNumber;
                        try
                        {
                            TrainNumber=Integer.parseInt(sc.nextLine().trim());
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("Train number must be numeric.");
                            return;
                        }

                        //search train name according to Entered Number
                        String TrainName=null;
                        for(int i=0;i<trainNumbers.length;i++)
                        {
                            if(TrainNumber==trainNumbers[i])
                            {
                                TrainName=trainNames[i];
                                break;
                            }
                        } 

                        if(TrainName==null)
                        {
                            System.out.println("Invalid Train , Try again");
                            return;
                        }
                        System.out.print("Enter Class type (SLeeper / Ac): ");
                        String ClassType=sc.nextLine().trim();
                        if(!ClassType.equalsIgnoreCase("Sleeper") && !ClassType.equalsIgnoreCase("Ac"))
                        {
                            System.out.println("Invalid class Type . Reservation cancelled.");
                            return;
                        }

                        System.out.print("Enter Date of Journey(dd-mm-yyyy): ");
                        String DateOfJourney=sc.nextLine().trim();
                        if(DateOfJourney.isEmpty())
                        {
                            System.out.println("Date of Journey cannot be empty");
                            return;
                        }

                        System.out.print("From : ");
                        String From=sc.nextLine();
                        System.out.print("To : ");
                        String To=sc.nextLine();
                        if(From.isEmpty() || To.isEmpty() || From.equalsIgnoreCase(To))
                        {
                            System.out.println("Invalid From/To Details");
                            return;
                        }

                        int PNR=100000+new Random().nextInt(900000);

                        Tickets t = new Tickets(Name,TrainNumber,TrainName,ClassType,DateOfJourney,From,To,PNR);

                        tickets[ticketCount++]=t;
                        //Display Tickets
                        t.Display();
                        System.out.println("---Thank You---");
                   }

                //Cancellation 
                static void cancelTicket()
                   {
                        if(ticketCount==0)
                        {
                            System.out.println("!---No Tickets Found---!");
                            return;
                        }
                        System.out.print("Enter PNR to cancel the ticket : ");
                        int pnr=Integer.parseInt(sc.nextLine().trim());

                            int index=-1;
                            for(int i=0;i<ticketCount;i++)
                            {
                                if(tickets[i].PNR==pnr)
                                {
                                    index=i;
                                    break;
                                }
                            }
                            if(index==-1)
                            {
                                System.out.println("Invalid PNR");
                                return;
                            }

                            //Confirm Cancellation 
                            System.out.print("Are you sure want to cancel the ticket(yes/no): ");
                            String confirm=sc.nextLine().trim().toLowerCase();
                            if(!confirm.equals("yes") && !confirm.equals("no"))
                            {
                                System.out.println("Invalid . Please try 'yes' or 'no'");
                                return;
                            }

                                if(!confirm.equals("yes"))
                                {
                                    for(int i=index;i<ticketCount-1;i++)
                                    {
                                        tickets[i]=tickets[i+1];
                                    }
                                    tickets[ticketCount-1]=null; //Remove Last one
                                    ticketCount--;
                                    System.out.println("<---Ticket with PNR:"+pnr+" cancelled succesfully--->");
                                }
                                else
                                {
                                    System.out.println("!--Cancellation Aborted--!");
                                }

                   }

                //View All Tickets
                static void viewTickets()
                   {
                        if(ticketCount==0)
                        {
                            System.out.println("!---No tickets Booked---!");
                            return;
                        }
                        System.out.println("---All Reserved Tickets---");
                        for(int i=0;i<ticketCount;i++)
                        {
                            tickets[i].Display();
                        }
                   }
                //Login Form
                static boolean Login()
            {
                String username = "admin";
                String password= "1234";

                int attempts=3;
                while(attempts>0)
                {
                System.out.print("Enter Username: ");
                String u = sc.nextLine();
                System.out.print("Enter Password: ");
                String p = sc.nextLine();
                
                    if(u.isEmpty() || p.isEmpty())
                    {
                        System.out.println("Note : Username / Password cannot be empty.");
                    }
                    else if(u.equals(username) && p.equals(password))
                    {
                        System.out.println("<--Login Succesfull-->");
                        return true;
                    }
                    else
                    {
                        attempts--;
                        System.out.println("!--Login Failed--!"+"Attempts left : "+attempts);
                        
                    }
                }
                System.out.println("To many failed attempts");
                return false;
             
            }

            
           
    public static void main (String[] args)
            {
                System.out.println("---Online Reservation System---");

                if(!Login())
                {
                    System.out.println("!--Exiting User--!");
                    return;
                }
                
                    int choice;
                    do
                    {
                    System.out.println("\n---Main Menu---");
                    System.out.println("1.Reservation");
                    System.out.println("2.Cancellation");
                    System.out.println("3.View all Tickets");
                    System.out.println("4.Exit");
                    System.out.println("Enter Choice number: ");
                    choice=Integer.parseInt(sc.nextLine().trim());

                    switch(choice)
                    {
                        case 1:
                        reserveTicket();
                        break;
                        case 2:
                        cancelTicket();
                        break;
                        case 3:
                        viewTickets();
                        break;
                        case 4:
                        System.out.println("Exiting Thank You");
                        break;
                        default:
                        System.out.println("---Invalid Choice , Try again---");
                    }
                    }
                    while(choice!=4);
                    
                
            }
}
/*Necessary details such as
their basic details, train number, train name will automatically come in the box, 
class type, date of journey, from (place) to destination*/
class Tickets
{
   //Strings PersonName , TrainName , CLassType Like Ac,Sleeper , Date of Journey , From LOcation , To Location
   // Ints TrainNumber , PNR
   String Name, TrainName , ClassType , DateOfJourney , From , To;
   int TrainNumber , PNR;
   public Tickets(String Name , int TrainNumber , String TrainName , String ClassType ,String DateOfJourney , String From , String To , int PNR)
   {
    this.Name=Name;
    this.TrainNumber=TrainNumber;
    this.TrainName=TrainName;
    this.ClassType=ClassType;
    this.DateOfJourney=DateOfJourney;
    this.From=From;
    this.To=To;
    this.PNR=PNR;
   }

   //To Display these all information in Main Function using Function called Display() Instead Writing S.O.P everytime .
   public void Display()
   {
    System.out.println("\n --- Ticket Details --- ");
    System.out.println("PNR : "+PNR);
    System.out.println("Name of the Candidate : "+Name);
    System.out.println("Train number : "+TrainNumber);
    System.out.println("Train Name : "+TrainName);
    System.out.println("Class : "+ClassType);
    System.out.println("Date of Journey : "+DateOfJourney);
    System.out.println("From : "+From);
    System.out.println("To : "+To);
    
   }
}