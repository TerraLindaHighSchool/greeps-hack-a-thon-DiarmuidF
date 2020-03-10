import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author (Your Name Here)
 * @version 0.1
 */
public class Greep extends Creature
{
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
    final static int HOW_MUCH_STEPS = 10;
    final static int FULL_60= 60;
    
    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
    }

    
    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }
    

    /**
     * Do what a greep's gotta do
     * 
     * 
     */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act().
        
        if (carryingTomato()) {
            int remainingSteps = getMemory();
            if(remainingSteps == 0) {
                spit("Purple");
                turnHome();
            } else {
                remainingSteps--;
                setMemory(remainingSteps);
            }
            
            if(atShip()) {
                dropTomato();
                turn(180);
            }
            
            if(atWater() || atWorldEdge()) {
                setMemory(HOW_MUCH_STEPS + Greenfoot.getRandomNumber(HOW_MUCH_STEPS/1));
                turn(Greenfoot.getRandomNumber(FULL_60));
            }
            move();
        }
        else { 
            boolean hasPurple = seePaint("Purple");
            if(hasPurple) {
                turnHome();
                turn(120);
            }
            
            if(atWater() || atWorldEdge()) {
                turn(Greenfoot.getRandomNumber(FULL_60));
            }
            move();
            checkFood();
        }
    }

    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if(tomatoes != null) {
            loadTomato();
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
    }


    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Ruhaan";  // write your name here!
    }


    /**
     * This method specifies the image we want displayed at any time. (No need 
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if(carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }
}