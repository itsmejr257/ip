import java.util.Scanner;
public class JunBot {

    public static void printList(String[] tasks, int tasksCount){
        int taskNumber = 1;
        String divider = "____________________________________________________________";

        System.out.println(divider);
        for(int i = 0; i < tasksCount; i++){
            System.out.println( taskNumber + ". " + tasks[i]);
            taskNumber += 1;
        }
        System.out.println(divider+"\n");
    }

    public static void addToList(String task, String[] tasks, int listPosition){
        String divider = "____________________________________________________________\n";
        tasks[listPosition] = task;
        System.out.println(divider + "added: " + task + "\n" + divider);
    }

    public static void main(String[] args) {
        String divider = "____________________________________________________________\n";
        String greeting = "Hello! I'm JunBot\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        String[] userInputsList = new String[100];


        System.out.println(divider + greeting + divider);

        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();
        int tasksCount = 0;

        while (!userInput.equals("bye")){
            switch (userInput) {
            case "list" : printList(userInputsList, tasksCount);
                break;
            default: addToList(userInput, userInputsList, tasksCount); tasksCount++;
                break;
            }
            userInput = userInputScanner.nextLine();
        }

        System.out.println(divider + goodbye + divider);
    }
}
