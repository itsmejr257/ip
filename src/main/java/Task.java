public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markTask(){
        this.isDone = true;

        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        System.out.println(divider);

    }

    public void unmarkTask(){
        this.isDone = false;

        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(this);
        System.out.println(divider);

    }

    public void printTask(){
        System.out.print("[" + this.getStatusIcon() + "] " + this.description + "\n");
    }

}
