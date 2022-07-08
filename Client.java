//eathquake1
//Jamie Shannon
//04-28-22
//This project reads earthquake data from a csv file and then prompts users to enter parameters
//which the program will use to create a custom txt file based on parameters provided



//This is my first project in java after learning about computer science using Python
//Sorry if my formating is off, I know I can do better writing methods and I look forward to learning how to write cleaner code :)


package eathquake1;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.util.Set;
import java.io.FileWriter;

public class Client {

	public static void main(String[] args) {
		
		//This is the file for the earthquake data saved on my computer
		File file = new File("earthquake.csv");
		
        try {
        	//file scanner for earthquake csv data
            Scanner fileScanner = new Scanner(file); 

            //set to collect dates with no repeats
            Set<String> dates = new HashSet<>(); 
            
            //set to collect locations with no repeats
            Set<String> locations = new HashSet<>(); 
            
            //Arraylist to collect earthquake data from CSV file
            ArrayList<EathquakeData1> list = new ArrayList<EathquakeData1>(); 
            
            //Arraylist to collect user earthquake data
            ArrayList<UserEarthquakeData> userList = new ArrayList<UserEarthquakeData>(); 

            fileScanner.nextLine();
                     
            //while loop that collects the data from csv file and puts it into the Earthquake data array list
            while (fileScanner.hasNextLine()) { 
                String line = fileScanner.nextLine();
                
                //tokenizer for collecting just the date without the time
                StringTokenizer tokenizerT = new StringTokenizer(line, "T");
                
                //collected everything after a comma
                StringTokenizer tokenizer = new StringTokenizer(line, ","); 

                
                String date = tokenizerT.nextToken();
                
                //adding dates to set for latter use
                dates.add(date); 
                
                //used this to skip over the date so we could use the tokenizer method to collect magnitude and location
                String dateNull = tokenizer.nextToken(); 
                
                
                float magnitude = Float.parseFloat(tokenizer.nextToken());
                
                
                String location = tokenizer.nextToken();

                
                if (tokenizer.hasMoreTokens()) {
                	//did this because there were multiple commas in the location
                    location = tokenizer.nextToken();
                }
                
                //I did this to clean up the location section
                location = location.replaceAll("^\"|\"$", ""); 
                if (location.startsWith(" ")) {
                    location = location.replaceFirst(" ", "");
                }
                
                //adding locations to a set for later use
                locations.add(location); 
                
                //creating new earthquake data object
                EathquakeData1 earthquakeData = new EathquakeData1(date, magnitude, location);
                
                //adding earthquake data object to array list
                list.add(earthquakeData);            
            }
            
            fileScanner.close();

            Boolean cont = true;
            
            //while loop that collects user input and creates an array list of data based on parameters and subsequently saves that data as .txt file
            while (cont) {
                Scanner userData = new Scanner(System.in);
                
                //asking for from date
                System.out.print("Date [yyyy-mm-dd][from]:");
                String fromDate = userData.nextLine();
                
                //makes sure the date is in correct form
                if (!dates.contains(fromDate)){ 
                    System.out.println("From date not found");
                    continue;
                }
                
                //this essentially does the same as above
                System.out.print("Date [yyyy-mm-dd][to]:");
                String toDate = userData.nextLine();
                if (!dates.contains(toDate)){
                    System.out.println("To date not found");
                    continue;
                }
                
                //making sure the date range is put in correctly
                if (fromDate.compareTo(toDate) > 0) {
                    System.out.println("Inaccurate date range entered");
                    continue;
                }
                
                
                System.out.println(locations);
                
                //displaying states to choose from so user doesn't have to guess the format               
                System.out.print("Choose a state from the list above:"); 
                
                
                String userState = userData.nextLine();
                
                //making sure the state entered is one we have
                if (!locations.contains(userState)) {
                    System.out.println("State entered not found, enter another state");
                    continue;
                }

                //getting magnitude data
                System.out.print("Magnitude [min]:"); 
                float userMagnitude = Float.parseFloat(userData.nextLine());
                
                //count method for displaying how many hits we get later
                Integer count = 0; 

                //for loop that adds data to user list if it meets user parameters
                for (int i = 0; i < list.size() - 1; i++) {
                	
                	//specifying parameters and adding to user list if they are meet
                    if ((userMagnitude <= list.get(i).getMagnitude())
                            && (userState.equals(list.get(i).getLocation()))
                            && (list.get(i).getDate().compareTo(fromDate) >= 0)
                            && (list.get(i).getDate().compareTo(toDate) <= 0)) {
                        UserEarthquakeData userParameters = new UserEarthquakeData(list.get(i).getDate(), list.get(i).getMagnitude(), list.get(i).getLocation());
                        userList.add(userParameters);
                        count += 1;
                    }
                }

                //no records found error displayed
                if (userList.size() == 0) {
                    System.out.println("No records found");
                } 
                
                
                else {
                    for (int i = 0; i < userList.size(); i++) {
                    	
                    	//displaying the data we found according to user parameters
                        System.out.println(userList.get(i));
                    }
                    
                    
                    System.out.println("Results found: " + count);
                    
                    
                    File output = new File("Earthquakes_<="+userMagnitude+"_In_"+userState+"_From_"+fromDate+"_To_"+toDate+".txt");
                    
                    //creating filewriter object to save data
                    FileWriter userFile = new FileWriter(output);
                    
                    //for loop goes through user list and adds all data to our custom txt file
                    for (Integer i = 0; i<userList.size(); i++){ 
                        userFile.write(String.valueOf(userList.get(i)));
                    }
                    
                    
                    userFile.close();                                       
                }
                
                //ask user if they want to continue, if "n" the while loop terminates
                System.out.print("Do you want to continue?(y/n)");
                
                
                String answer = userData.nextLine();
                
                
                if (answer.equals("y")) {
                    cont = true;
                }
                
                
                if (answer.equals("n")) {
                    cont = false;
                    userData.close();
                }
                
                
            }

        } catch (FileNotFoundException e) { 
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }		

	}

}
