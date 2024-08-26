package ip.derrick ;
public class CommandHandler {

    private Commands command;
    private boolean isExit;
    public CommandHandler(Commands command) {
        this.isExit = false;
        this.command = command;
    }

    public boolean checkExit() {
        return this.isExit;
    }

    public void execute(String input, TaskList tasks,Storage storage, Ui ui) {
        switch (command) {
        case BYE:
            this.isExit = true;
            break;
        case LIST:
            tasks.list();
            break;
        case MARK:
            try {
                tasks.markItem(input);
            } catch (MissingPositionException | MissingItemException e) {
                System.out.println(e.getMessage());
            }
            break;
        case UNMARK: {
            try {
                tasks.unmarkItem(input);
            } catch (MissingPositionException | MissingItemException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case FIND: {
            try {
                tasks.findItem(input);
            } catch (MissingPositionException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case DELETE: {
            try {
                tasks.delete(input);
            } catch (MissingItemException | MissingPositionException | EmptyListException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case TODO: {
            try {
                tasks.addTodo(input);
            } catch (InvalidDescriptionException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case DEADLINE: {
            try {
                tasks.addDeadline(input);

            } catch (InvalidDescriptionException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case EVENT: {
            try {
                tasks.addEvent(input);

            } catch (InvalidDescriptionException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case UNKNOWN: {
            System.out.println("Please specify the type of item that you wish to add ( Todo / Event / Deadline )");
            break;
        }
        }
    }
}
