

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  Locations, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 * 
 * 
 * Guten Tag. Das ist ein Test
 */

public class Game 
{
    private Parser parser;
    private Location currentLocation;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createLocations();
        parser = new Parser();
    }

    /**
     * Create all the Locations and link their exits together.
     */
    private void createLocations()
    {
        Location trackN, trackW, trackS, trackE, soccer, longJump, shed;
      
        // create the Locations
        trackN = new Location("on the northern part of the tartan track");
        trackW = new Location("on the western part of the tartan track");
        trackS = new Location("on the southern part of the tartan track");
        trackE = new Location("on the eastern part of the tartan track");
        soccer = new Location("on the soccerfield");
        longJump = new Location("in the long jump area with a sandbox:)");
        shed = new Location("at the shed");
        
        // initialise Location exits
        trackN.addExit("west", trackW);
        trackN.addExit("east", trackE);
        trackN.addExit("south", soccer);
        
        trackW.addExit("north", trackN);
        trackW.addExit("east", soccer);
        trackW.addExit("south", trackS);
        
        trackE.addExit("north", trackN);
        trackE.addExit("west", soccer);
        trackE.addExit("south", trackS);
        
        trackS.addExit("north", soccer);
        trackS.addExit("east", trackE);
        trackS.addExit("west", trackW);
        trackS.addExit("south", longJump);
        
        soccer.addExit("north", trackN);
        soccer.addExit("east", trackE);
        soccer.addExit("west", trackW);
        soccer.addExit("south", trackS);
        
        longJump.addExit("north", trackS);
        longJump.addExit("east", shed);

        shed.addExit("west", longJump);

        currentLocation = soccer;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goLocation(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("look"))
            look();
        else if (commandWord.equals("open"))
            open(command);

        return wantToQuit;
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to SportyZombies!");
        System.out.println("SportyZombies is a new, incredible horror adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentLocation.getLongDescription());
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. But you are alone. A horde of zombies");
        System.out.println("is running towards you.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go  quit  help  look  open");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new Location, otherwise print an error message.
     */
    private void goLocation(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current Location.
        Location nextLocation = null;
        
        nextLocation = currentLocation.getExit(direction);

        if (nextLocation == null) {
            System.out.println("There is no door!");
        }
        else {
            currentLocation = nextLocation;
            System.out.println(currentLocation.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Look around. Prints out the long description of the current location.
     */
    private void look(){
        System.out.println(currentLocation.getLongDescription());
    }
    
    /**
     * Try to open something.
     * @param command The command that triggered this method call.
     */
    private void open(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("What do you want to open?");
        }
    }
}
