import java.util.HashMap;
/**
 * Class Location - a Location in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Location" represents one location in the scenery of the game.  It is 
 * connected to other Locations via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the Location stores a reference
 * to the neighboring Location, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Location 
{
    private HashMap<String, Location> exits;
    private String description;

    /**
     * Create a Location described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The Location's description.
     */
    public Location(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this Location.  Every direction either leads
     */
    public void addExit(String name, Location exit) 
    {
        exits.put(name, exit);
    }

    /**
     * @return The description of the Location.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return The long description of the Location.
     */
    public String getLongDescription()
    {
        return "You are " + getDescription() + "\n" + getExits();
    }
    
    public String getExits(){
        String allExits = "Exits: ";
        for(String name : exits.keySet()){
            allExits += name + ", ";
        }
        allExits = allExits.replaceAll(", $","");
        
        return allExits;
    }
    
    public Location getExit(String exit){
        return exits.get(exit);
    }
}
