package junbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Todo {
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs an Event object with the provided description, startDate and endDate, along with its associated tag E
     *
     * @param description description of the task
     * @param startDate start date of the task
     * @param endDate end date of the task
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.tag = "E";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String startDateString = startDate.format(formatter);
        return startDateString;
    }

    @Override
    public String getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String endDateString = endDate.format(formatter);
        return endDateString;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        String formattedStartDate = startDate.format(outputFormatter);
        String formattedEndDate = endDate.format(outputFormatter);

        return super.toString() + " (from: " + formattedStartDate
                + " to: " + formattedEndDate + ")";
    }
}