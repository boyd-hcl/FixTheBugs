import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*System.out.println("Hello World!");*/
        System.out.println("\n**************************************\n");
        System.out.println("\tWelcome to TheDesk \n");
        System.out.println("**************************************");
        optionsSelection();

    }
    private static void optionsSelection() {
    	
    	
    	//Display menu options
    	String menu = ("1. I wish to review my expenditure\n"
                +"2. I wish to add my expenditure\n"
                +"3. I wish to delete my expenditure\n"
                +"4. I wish to sort the expenditures\n"
                +"5. I wish to search for a particular expenditure\n"
                +"6. Close the application\n");
        
        
        //Allocate array for expenses
        ArrayList<Integer> expenses = new ArrayList<Integer>();
        
        //Add existing expenses to record
        expenses.add(1000);
        expenses.add(2300);
        expenses.add(45000);
        expenses.add(32000);
        expenses.add(110);
        
        //Request input from user
        System.out.println("\nEnter your choice:\t");
        
        //Declare scanner for user input and scan until user sends kill signal (6)
        Scanner sc = new Scanner(System.in);
        boolean failed = false;
        int options = -1;
        while(options != 6) {
			System.out.println(menu);
			failed = false;
	        do {
				failed = false;
		        try {
					options =  sc.nextInt();
		        }
		        catch(Exception e) {
		        	failed = true;
		        	System.out.println("Improper input. Please enter a valid input. \n"
							+ "This would be the single number character associated with your desired action");
					String temp = sc.nextLine();
		        }
	        }while(failed);
	        switch (options){
	            case 1:
	                System.out.println("Your saved expenses are listed below: \n");
	                System.out.println(expenses+"\n");
	                break;
	            case 2:
	                System.out.println("Enter the value to add your Expense: \n");
	                int value = sc.nextInt();
	                expenses.add(value);
	                System.out.println("Your value is updated\n");
	                System.out.println(expenses+"\n");
	                break;
	            case 3:
	                System.out.println("You are about the delete all your expenses! \nConfirm again by selecting the same option...\n");
	                int con_choice = sc.nextInt();
	                if(con_choice==options){
	                       expenses.clear();
	                    System.out.println(expenses+"\n");
	                    System.out.println("All your expenses are erased!\n");
	                } else {
	                    System.out.println("Oops... try again!");
	                }
	                break;
	            case 4:
	                sortExpenses(expenses);
	                break;
				case 5:
					int searchValue = -1;
					System.out.println("Please input the value of the expense you would like to search:");
					do {
						failed = false;
						try {
							searchValue =  sc.nextInt();
						}
						catch(Exception e) {
							failed = true;
							System.out.println("Improper input. Please enter a valid input. \n"
									+ "This would be any integer value.");
							String temp = sc.nextLine();
						}
					}while(failed);
	                searchExpenses(expenses, searchValue);
	                break;
	            case 6:
	                closeApp();
	                break;
	            default:
	                System.out.println("The range of the menu is 1 to 6, inclusive. Please input a number within that range.");
	                break;
	        }
		}
		sc.close();
    }

    private static void closeApp() {
        System.out.println("Closing your application... \nThank you!");
    }
    
    
    private static void searchExpenses(ArrayList<Integer> arrayList, int value) {
        int leng = arrayList.size();
        for(int i = 0; i < leng; i++) {
        	if(arrayList.get(i)==value) {
        		System.out.println("Value found in expense list");
				return;
			}
		}
		System.out.println("Result not found in list");
		return;
    }
    
    //implemented tree sort
    private static void sortExpenses(ArrayList<Integer> arrayList) {
        class Node{
        	int value;
        	Node left;
        	Node right;
        	Node(int val){
        		value = val;
        	}
        	//result is a NEW arrayList
        	void AddRecursive(ArrayList<Integer> result){
        		
				if(this!=null){
					if(this.left!=null) {
        				left.AddRecursive(result);
					}
					result.add(value);
					}
        			if(this.right!=null) {
        				right.AddRecursive(result);
					}
				}
        	}
        if(arrayList.size() <= 1) {
        	return;
        }
        Node head = new Node(arrayList.get(0));
        for(int i = 0; i < arrayList.size(); i++) {
        	Node current = new Node(arrayList.get(i));
        	Node temp = head;
        	Node prev = null;
        	while(temp!=null) {
        		prev = temp;
        		if(current.value < temp.value) {
        			temp = temp.left;
        		}
        		else {
        			temp = temp.right;
				}
			}
			if(current.value < prev.value){
				prev.left = current;
			}
			else{
				prev.right = current;
			}
        }
        arrayList.clear();
		head.AddRecursive(arrayList);
		System.out.println("\nList sorted. Returning to main menu...\n");
    }
}
