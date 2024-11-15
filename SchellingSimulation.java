import student.media.*;
import student.util.Random;
import java.awt.Color;

//-------------------------------------------------------------------------
/**
 *  // Virginia Tech Honor Code Pledge:
 *  //
 *  // As a Hokie, I will conduct myself with honor and integrity at all times.
 *  // I will not lie, cheat, or steal, nor will I accept the actions of those
 *  // who do.
 *  // -- Federico Tafur (fedetafur)
 *  
 *  In this program we will be creating a shellingSimulation, in this section
 *  we will be writing all the methods with stubs, formating the coding for next
 *  project. 
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Federico Tafur (fedetafur)
 *  @version (2024.10.03)
 */
public class SchellingSimulation
    extends Picture
{
    //~ Fields ................................................................
    private double satisfactionThreshold;
    private int redLine;
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created SchellingSimulation object.
     * 
     * @param width , in this parameter we will be determining 
     * the width of the picture using in the shellingSimulation
     * 
     * @param height , in this parameter we will be determining 
     * the height of the picture using in the shellingSimulation
     */
    public SchellingSimulation(int width, int height)
    {
        super(width, height);
        /*# Do any work to initialize your class here. */
        satisfactionThreshold = 0.3;
        redLine = 0;
        
    }

    //~ Methods ...............................................................
    /**
     * A getter method that returns the satisfaction threshold 
     * (a double).
     * 
     * @return (double) that represents the satisfaction 
     * threshold
     */
    public double getSatisfactionThreshold()
    {
        return this.satisfactionThreshold;
    }
    
    /**
     * A setter method that takes a double parameter and changes 
     * the satisfaction threshold to the specified value.
     * 
     * @param newThreshold (double) represents a number 
     * that will change the satisfaction threshold. 
     */
    public void setSatisfactionThreshold(double newThreshold)
    {
        this.satisfactionThreshold = newThreshold;
    }
    
    /**
     * A getter method that returns the redline value (an int).
     * 
     * @return (int) that represents the integer that represents
     * the standard for the RedLine. 
     */
    public int getRedLine()
    {
        return this.redLine;
    }
    
    /**
     * A setter method that takes an integer parameter and changes 
     * the redline to the specified value.
     * 
     * @param changeRedLine represents the specified value for the RedLine
     */
    public void setRedLine(int changeRedLine)
    {
        redLine = changeRedLine;
    }
    
    /**
     *     Takes two double parameters between 0.0 - 1.0 that represent 
     *     percentages. This method will "paint" the image with a randomized 
     *     collection of blue and orange pixels. The first parameter represents 
     *     the probabilty of any pixel in the image being set to blue 
     *     (representing an X agent). The second represents the probability 
     *     of any pixel in the image (below the redline) being set to orange 
     *     (representing an O agent). Other pixels will be left alone 
     *     (empty, represented by white ).
     *     
     *     @param blue The first parameter represents the probabilty of 
     *     any pixel in the image being set to blue (representing an X 
     *     agent). 
     *     
     *     @param orange The second represents the probability of any 
     *     pixel in the image (below the redline) being set to orange 
     *     (representing an O agent).
     */
    public void populate(double blue, double orange)
    {   
        Random random = new Random();

        for (Pixel pixel : this.getPixels())
        {
            double rand = random.nextDouble();
    
            if (rand <= blue) 
            {
                pixel.setColor(Color.BLUE);
            } 
            else if (rand <= blue + orange) 
            {
                pixel.setColor(Color.ORANGE);
            }
        }
        /*for (int x = 0; x < getWidth(); x++) 
        {
            for (int y = 0; y < getHeight(); y++) 
            {
                double rand = random.nextDouble();
    
                if (rand <= blue) 
                {
                    Pixel pixel = getPixel(x, y);
                    pixel.setColor(Color.BLUE);
                } 
                else if (rand <= blue + orange) 
                {
                    Pixel pixel = getPixel(x, y);
                    pixel.setColor(Color.ORANGE);
                }
            }
        }*/
    }
    
    /**
     * Takes two Pixel objects and returns a boolean value indicating 
     * whether the two pixels have the same color.
     * 
     * @return boolean , in this return we will know if the case ends
     * up being true or false through boolean values
     * 
     * @param pixel1 checks the color for the first pixel
     * 
     * @param pixel2 checks the color for the second pixel
     */
    public boolean areSameColor(Pixel pixel1, Pixel pixel2)
    {
        Color color1 = pixel1.getColor();
        Color color2 = pixel2.getColor();
        
        return color1.equals(color2);
    }
    
    /**
     * Takes one Pixel object and returns true if its color is Color.WHITE, 
     * representing an empty location.
     * 
     * @return boolean , in this return we will know if the case ends
     * up being true or false through boolean values
     * 
     * @param pixel checks the color for the first pixel, more specifically
     * to check if the pixel is empty.
     */
    public boolean isEmpty(Pixel pixel)
    {
        return pixel.getColor().equals(Color.WHITE);
    }
    
    /**
     *  Takes one Pixel object and a Color value, and returns a 
     *  boolean result indicating whether an agent of the specified 
     *  Color would be satisfied at the given Pixel location. It 
     *  should return true if there are no neighboring agents, or if 
     *  the proportion of neighboring agents the same color as the 
     *  parameter meets or exceeds the satisfaction threshold. Remember 
     *  not to count empty neighboring pixels, only neighboring agents.
     *     
     *  @return boolean , in this return we will know if the case ends
     *  up being true or false through boolean values
     * 
     *  @param pixel checks the pixel and it's qualities
     *  
     *  @param agentColor checks the color of the pixel, specially
     *  to see if it satifies it's requisites
     */
    public boolean isSatisfied(Pixel pixel, Color agentColor)
    {
        int totalNeighbors = 0; 
        int goodNeighbours = 0; 
        
        for (Pixel neighbours : pixel.getNeighborPixels())
        {
            if (!neighbours.getColor().equals(Color.WHITE))
            {
                totalNeighbors++;
                if (neighbours.getColor().equals(agentColor))
                {
                    goodNeighbours++;
                }
            }
        }
        if (totalNeighbors == 0)
        {
            return true;
        }

        double proportion = (goodNeighbours * 1.0) / totalNeighbors;
        return proportion >= satisfactionThreshold;
    }
    
    /**
     * Takes one Pixel object as a parameter and returns a boolean result. 
     * This method tries to move the corresponding agent to a new pixel 
     * location. This involves picking a new random x/y location in the 
     * image (below the redline, if the current pixel is occupied by an 
     * orange agent). If the selected location is empty, and if the current 
     * agent would be satisfied at the new location, then "move" the agent by 
     * setting the new location's color to the current pixel's color and 
     * setting the current pixel's color to Color.WHITE. Otherwise, leave the 
     * current agent in place, if it cannot be moved to the selected 
     * destination. Return true if the agent is moved, or false if the attempt
     * to move fails.
     * 
     * @return boolean , in this return we will know if the case ends
     *  up being true or false through boolean values
     * 
     * @param pixel checks the pixel and it's qualities, and if nesearry
     * to relacate to another space in the image.
     */
    public boolean maybeRelocate(Pixel pixel)
    {
        Random generator = Random.generator();
        
        int x = generator.nextInt(this.getWidth());
        int y = generator.nextInt(this.getHeight());
        
        if (pixel.getColor().equals(Color.ORANGE))
        {
            while (y < redLine)
            {
                y = generator.nextInt(this.getWidth());
            }
        }
        
        Pixel p1 = this.getPixel(x, y);
        Color initialCol = pixel.getColor();
        
        if (p1.getColor().equals(Color.WHITE) && 
           this.isSatisfied(p1, initialCol))
        {
            this.getPixel(x, y).setColor(initialCol);
            this.getPixel(pixel.getX(), pixel.getY()).setColor(Color.WHITE);
            return true;
        }
        return false;
        
        
        /*Random generator = Random.generator();
        int w = generator.nextInt(this.getWidth());
        int w1;
        
        if (pixel.getColor().equals(Color.ORANGE))
        {
            w = redLine + generator.nextInt(this.getHeight() - redLine);
        }
        else 
        {
            w1 = generator.nextInt(this.getHeight());
        }
        
        Pixel p1 = this.getPixel(w, w1);
        Color initialCol = pixel.getColor();
        p1.setColor(Color.WHITE);
        
        if (this.isEmpty(p1) && isSatisfied(p1, initialCol))
        {
            p1.setColor(initialCol);
            return true;
        }
        else 
        {
            pixel.setColor(initialCol);
            return false;
        }*/
    }
    
    /**
     * Takes no parameters and returns an integer result. This 
     * method uses a loop over all pixels, checking each pixel in 
     * turn. If the pixel is empty, leave it alone. If it is occupied, 
     * check to see if the agent there is satisfied--if so, leave the 
     * agent alone. Otherwise, attempt to relocate it to a new position 
     * (which could succeed or fail, as described under maybeRelocate(). 
     * The method returns the total number of successful moves after 
     * processing all pixels.
     * 
     * @return (int) this return gives back the amonut of cycles done 
     * in the system.
     */
    public int cycleAgents()
    {
        int numberOfMoves = 0;
        
        for (Pixel pixel : this.getPixels())
        {
            if (!isEmpty(pixel) && !isSatisfied(pixel, pixel.getColor())
                && maybeRelocate(pixel))
            {
                numberOfMoves += 1; 
            }
        }
        return numberOfMoves;
    }
}
