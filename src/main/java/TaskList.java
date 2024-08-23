package ip.derrick ;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> output() {
        return this.tasks;
    }

    public void list() {
        if (this.tasks.isEmpty()) {
            System.out.println("You have nothing in your list.");
        } else {
            System.out.println(("Here are the items in your list:"));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void delete(String input) throws MissingPositionException, MissingItemException, EmptyListException {
        int position;
        try {
            position = Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing a specific position. Please try again.");
        } catch (NumberFormatException e) {
            throw new MissingPositionException("Position must be an integer. Please try again.");
        }

        if (this.tasks.isEmpty()) {
            throw new EmptyListException("You are trying to delete from an empty list.");
        } else if (position <= 0 || position > this.tasks.size()) {
            throw new MissingItemException("Item does not exist in the list. Please try again.");
        }
        Task task = this.tasks.get(position - 1);
        this.tasks.remove(task);
        System.out.println("I have removed this task:");
        System.out.println(task);
        System.out.println("You have " + this.tasks.size() + " items in your list.");
    }


    public void addTodo(String input) throws InvalidDescriptionException {
        Todo todo;
        try {
            todo = new Todo(input.split(" ", 2)[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You have not added any description for the todo task. Please try again.");
        }
        this.tasks.add(todo);
        System.out.println("Got it. I have added this todo.");
        System.out.println(todo);
        System.out.println("You have " + this.tasks.size() + " items in your list.");

    }


    public void addDeadline(String input) throws InvalidDescriptionException {
        String time;
        String description;

        try {
            time = input.split("/by")[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You have not added any deadline for the todo task. Please try again.");
        }

        try {
            description = input.split("/by")[0].split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You have not added any description for the todo task. Please try again.");
        }

        Deadline deadline = new Deadline(description, time);
        this.tasks.add(deadline);
        System.out.println("Got it. I have added this deadline.");
        System.out.println(deadline);
        System.out.println("You have " + this.tasks.size() + " items in your list");
    }


    public void addEvent(String input) throws InvalidDescriptionException {
        String start;
        String end;
        String description;

        try {
            description = input.split("/from")[0].split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You are missing the description for the event. Please try again.");
        }

        try {
            start = input.split("/from")[1].split("/to")[0];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You are missing the start time for the event. Please try again.");
        }

        try {
            end = input.split("/from")[1].split("/to")[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You are missing the end time for the event. Please try again.");
        }



        Event event = new Event(description, start, end);
        this.tasks.add(event);
        System.out.println("Got it. I have added this event.");
        System.out.println(event);
        System.out.println("You have " + this.tasks.size() + " items in your list.");
    }


    public void markItem(String input) throws MissingPositionException, MissingItemException {
        int position;
        try {
            position = Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing a specific position. Please try again.");
        } catch (NumberFormatException e) {
            throw new MissingPositionException("Position must be an integer. Please try again.");
        }

        if (position <= 0 || position > this.tasks.size()) {
            throw new MissingItemException("Item does not exist in the list. Please try again.");
        }

        Task task = this.tasks.get(position - 1);
        task.markStatus();
        System.out.println("I have marked this task as done!");
        System.out.println(task);
    }

    public void unmarkItem(String input) throws MissingPositionException, MissingItemException {
        int position;
        try {
            position = Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing a specific position. Please try again.");
        } catch (NumberFormatException e) {
            throw new MissingPositionException("Position must be an integer. Please try again.");
        }

        if (position <= 0 || position > this.tasks.size()) {
            throw new MissingItemException("Item does not exist in the list. Please try again.");
        }

        Task task = this.tasks.get(position - 1);
        task.unmarkStatus();
        System.out.println("I have marked this task as not done yet!");
        System.out.println(task);
    }

}
