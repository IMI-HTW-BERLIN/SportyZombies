import java.util.HashMap;
/**
 * Write a description of class CommandWord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CommandWord
{
    HashMap<String, CommandWords> commands = new HashMap<>();

    /**
     * Constructor - initialise the command words.
     */
    CommandWord()
    {
        commands.put("go", CommandWords.GO);
        commands.put("help", CommandWords.HELP);
        commands.put("quit", CommandWords.QUIT);
        commands.put("look", CommandWords.LOOK);
        commands.put("open", CommandWords.OPEN);
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String cmd)
    {
        return commands.containsKey(cmd);
    }
}
