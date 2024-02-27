package junbot.common;
import junbot.parser.Parser;
import junbot.tasks.Deadline;
import junbot.tasks.Event;
import junbot.tasks.Todo;
import junbot.error.InvalidInputException;
import junbot.tasks.Task;
import junbot.ui.Ui;
import java.time.LocalDate;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected Ui ui;
    protected Parser parser;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
        ui = new Ui();
        parser = new Parser();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        ui = new Ui();
        parser = new Parser();
    }

    public ArrayList<Task> getTasksList() {
        return this.tasks;
    }

    public int getSize(){
        return getTasksList().size();
    }


    public Task addDeadline(String description) throws InvalidInputException {

        description = description.replace("deadline", "").trim();

        if (!description.contains("/by")) {
            throw new InvalidInputException("Include a /by for deadline");
        }

        String[] details = new String[2];
        details = description.split("/by", 2);

        String deadlineDescription = details[0].trim();
        String deadlineDateTimeString = details[1].trim();

        LocalDate deadlineDate = parser.parseDate(deadlineDateTimeString);

        Task userTask = new Deadline(deadlineDescription, deadlineDate);
        tasks.add(userTask);
        return userTask;
    }

    public Task addEvent(String description) throws InvalidInputException {
        description = description.replace("event", "").trim();
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new InvalidInputException("Include a /from and /to for event");
        }

        String[] details = new String[3];
        details = description.split("/from|/to", 3);

        String eventDescription = details[0].trim();
        String eventStartDateString = details[1].trim();
        String eventEndDateString = details[2].trim();

        LocalDate startDate = parser.parseDate(eventStartDateString);
        LocalDate endDate = parser.parseDate(eventEndDateString);

        Task userTask = new Event(eventDescription, startDate, endDate);
        tasks.add(userTask);
        return userTask;
    }

    public Task addTodo(String description) throws InvalidInputException {
        description = description.replace("todo", "").trim();
        if(description.isEmpty()){
            throw new InvalidInputException("Please state a task : todo <task>");
        }

        Task userTask = new Todo(description);
        tasks.add(userTask);
        return userTask;
    }


    public Task markTaskInList(int listPosition) {
        Task task = this.getTasksList().get(listPosition);
        task.markTask();
        return task;
    }

    public Task unmarkTaskInList(int listPosition) {
        Task task = this.getTasksList().get(listPosition);
        task.unmarkTask();
        return task;
    }

    public ArrayList<Task> findTasks(String keyword) {

        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size()-1; i++) {
            String taskDescription = tasks.get(i).getDescription();

            if (taskDescription.contains(keyword)){
                foundTasks.add(tasks.get(i));
            }
        }

        return foundTasks;

    }

    public void printMatchingTasks(String keyword){
        int taskNumber = 1;
        ArrayList<Task> foundTasks = findTasks(keyword);

        System.out.println("Here are the matching tasks in your list: ");
        ui.printDivider();
        for(int i = 0; i < foundTasks.size(); i++){
            System.out.print( taskNumber + ". ");
            System.out.println(foundTasks.get(i));
            taskNumber += 1;
        }
        ui.printDivider();
    }

}
