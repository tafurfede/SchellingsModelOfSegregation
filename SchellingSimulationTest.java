import student.micro.*;
import java.awt.Color;
import static org.assertj.core.api.Assertions.*;
import student.media.*;
import student.util.Random;

// -------------------------------------------------------------------------
/**
 *  // Virginia Tech Honor Code Pledge:
 *  //
 *  // As a Hokie, I will conduct myself with honor and integrity at all times.
 *  // I will not lie, cheat, or steal, nor will I accept the actions of those
 *  // who do.
 *  // -- Federico Tafur (fedetafur)
 *  
 *  in this i will test all my methods, giving assertions to find out if all 
 *  code ends up working as expected
 *  Summarize what your test objectives are.
 *
 *  @author Federico Tafur (fedetafur)
 *  @version (2024.10.03)
 */
public class SchellingSimulationTest
    extends TestCase
{
    //~ Fields ................................................................

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SchellingSimulationTest test object.
     */
    public SchellingSimulationTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
    }
    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * this method tests getSatisfactionThreshold() method 
     * while following this description... A getter method 
     * that returns the satisfaction threshold (a double).
     */
    public void testGetSatisfactionThreshold()
    {
        //initiate
        SchellingSimulation sim = new SchellingSimulation(1, 3);
        
        //call method
        sim.getSatisfactionThreshold();
        
        //check the conditions
        assertThat(sim.getSatisfactionThreshold()).isEqualTo(0.3);
    }
    
    /**
     * this method tests getSatisfactionThreshold() method 
     * while following this description... A getter method 
     * that returns the satisfaction threshold (a double).
     */
    public void testSetSatisfactionThreshold()
    {
        //initiate
        SchellingSimulation sim = new SchellingSimulation(10, 10);
        
        //check the conditions
        assertThat(sim.getSatisfactionThreshold()).isEqualTo(0.3);
    }
    
    /**
     * this method test getRedLine() method, while following this 
     * description... A getter method that returns the redline 
     * value (an int).
     */
    public void testGetRedLine() 
    {
        //initiate
        SchellingSimulation sim = new SchellingSimulation(10, 10);
        
        //call method
        sim.getRedLine();
        
        //check the conditions
        assertThat(sim.getRedLine()).isEqualTo(0);
        
    }
    
    /**
     * this method test setRedLine() method, while following this 
     * description... A setter method that takes an integer 
     * parameter and changes the redline to the specified value.
     */
    public void testSetRedLine()
    {
        SchellingSimulation sim = new SchellingSimulation(10, 10);
        
        sim.setRedLine(1);
        assertEquals(1, sim.getRedLine());
    }
    
    /**
     * this method test populate() method, while following this 
     * description... Takes two double parameters between 
     * 0.0 - 1.0 that represent percentages. This method will 
     * "paint" the image with a randomized collection of blue 
     * and orange pixels. The first parameter represents the 
     * probabilty of any pixel in the image being set to blue 
     * (representing an X agent). The second represents the 
     * probability of any pixel in the image (below the redline) 
     * being set to orange (representing an O agent). Other 
     * pixels will be left alone (empty, represented by  white ).
     */
    public void testPopulate()
    {
        //initiate
        SchellingSimulation sim = new SchellingSimulation(10, 10); 
    
        //call method
        Random.setNextDoubles(0.1, 0.4);
        sim.populate(0.3, 0.3);
        
        assertThat(sim.getPixel(0, 0).getColor()).isEqualTo(Color.BLUE);
    }
    
    /**
     * tests the populate method for the second
     * time
     */
    public void testPopulate02()
    {
        //initiate
        SchellingSimulation sim = new SchellingSimulation(1, 1); 
    
        //call method
        Random.setNextDoubles(0.9);
        sim.populate(0.1, 0.1);
        
        assertThat(sim.getPixel(0, 0).getColor()).isEqualTo(Color.WHITE);
    }
    
    /**
     * this method test areSameColor() method, while following this 
     * description... Takes two Pixel objects and returns a boolean 
     * value indicating whether the two pixels have the same color.
     */
    public void testAreSameColor()
    {
        //initiate
        SchellingSimulation sim = new SchellingSimulation(1, 3);
        
        Pixel pixel1 = sim.getPixel(0, 0);
        pixel1.setColor(Color.BLUE);
        Pixel pixel2 = sim.getPixel(0, 1);
        pixel2.setColor(Color.BLUE);
        Pixel pixel3 = sim.getPixel(0, 2);
        pixel3.setColor(Color.GREEN);

        
        //check the conditions
        assertThat(sim.areSameColor(pixel1, pixel2)).isTrue();
        assertThat(sim.areSameColor(pixel1, pixel3)).isFalse();
    }
    
    /**
     * Takes one Pixel object and returns true if its color is 
     * Color.WHITE, representing an empty location.
     */
    public void testIsEmpty()
    {
        SchellingSimulation sim = new SchellingSimulation(1, 2);
        
        Pixel pixel1 = sim.getPixel(0, 0);
        pixel1.setColor(Color.BLUE);
        
        Pixel pixel2 = sim.getPixel(0, 1);
        pixel2.setColor(Color.WHITE);
        
        assertThat(sim.isEmpty(pixel1)).isFalse();
        assertThat(sim.isEmpty(pixel2)).isTrue();

    }
    
    /**
     * this method test isSatisfied() method, while following this 
     * description... Takes one Pixel object and a Color value, 
     * and returns a boolean result indicating whether an agent 
     * of the specified Color would be satisfied at the given 
     * Pixel location. It should return true if there are no 
     * neighboring agents, or if the proportion of neighboring 
     * agents the same color as the parameter meets or exceeds 
     * the satisfaction threshold. Remember not to count empty 
     * neighboring pixels, only neighboring agents.
     */
    public void testIsSatisfied()
    {
        //initiaite
        SchellingSimulation sim = new SchellingSimulation(2, 2);
        
        Pixel p1 = sim.getPixel(0, 0);
        p1.setColor(Color.BLUE);
        
        Pixel p2 = sim.getPixel(0, 1);
        p2.setColor(Color.BLUE);
        
        Pixel p3 = sim.getPixel(1, 0);
        p3.setColor(Color.BLUE);
        
        Pixel p4 = sim.getPixel(1, 1);
        p4.setColor(Color.BLUE);
        
        //check the conditions
        assertThat(sim.isSatisfied(p1, p1.getColor())).isTrue();
        p1.setColor(Color.ORANGE);
        
        assertThat(sim.isSatisfied(p1, p1.getColor())).isFalse();
        for (Pixel pix : p2.getNeighborPixels())
        {
            pix.setColor(Color.WHITE);
        }
        assertThat(sim.isSatisfied(p2, p2.getColor())).isTrue();
        
    }
    
    /**
     * this method test maybeRelocate() method, while following this 
     * description... Takes one Pixel object as a parameter and returns 
     * a boolean result. This method tries to move the corresponding 
     * agent to a new pixel location. This involves picking a new random 
     * x/y location in the image (below the redline, if the current pixel 
     * is occupied by an orange agent). If the selected location is empty, 
     * and if the current agent would be satisfied at the new location, 
     * then "move" the agent by setting the new location's color to the 
     * current pixel's color and setting the current pixel's color to 
     * Color.WHITE. Otherwise, leave the current agent in place, if it 
     * cannot be moved to the selected destination. Return 
     * true if the agent is moved, or false if the attempt to move fails.
     * 
     */
    public void testMaybeRelocate()
    {
        SchellingSimulation sim = new SchellingSimulation(4, 4);
        
        for (Pixel pix : sim.getPixels())
        {
            pix.setColor(Color.ORANGE);
        }
        
        boolean value = sim.maybeRelocate(sim.getPixel(0, 0));
        Random.setNextInts(1, 1);
        assertThat(value).isFalse();
        
        for (Pixel pix : sim.getPixels())
        {
            pix.setColor(Color.WHITE);
        }
        
        value = sim.maybeRelocate(sim.getPixel(0, 0));
        Random.setNextInts(1, 1);
        assertThat(sim.getPixel(0, 0).getColor()).isEqualTo(Color.WHITE);
        assertThat(sim.getPixel(1, 1).getColor()).isEqualTo(Color.WHITE);
        
        sim.getPixel(0, 0).setColor(Color.BLUE);
        sim.getPixel(2, 2).setColor(Color.ORANGE);
        sim.getPixel(2, 3).setColor(Color.ORANGE);
        sim.getPixel(3, 2).setColor(Color.ORANGE);
        Random.setNextInts(3, 3);
        assertThat(sim.maybeRelocate(sim.getPixel(0, 0))).isFalse();
        sim.getPixel(3, 3).setColor(Color.BLUE);
        Random.setNextInts(3, 3);
        assertThat(sim.maybeRelocate(sim.getPixel(0, 0))).isFalse();
        
        for (Pixel pix : sim.getPixels())
        {
            pix.setColor(Color.WHITE);
        }
        
        sim.getPixel(1, 1).setColor(Color.BLUE);
        Random.setNextInts(1, 1);
        assertThat(sim.maybeRelocate(sim.getPixel(0, 0))).isFalse();
    }
    
    /**
     * test the method maybeReolcate(), more specifically 
     * the if statement with two conditions. 
     */
    public void testMaybeRelocate02()
    {
        SchellingSimulation sim = new SchellingSimulation(2, 2);
        
        /*for (Pixel pix : sim.getPixels())
        {
            pix.setColor(Color.WHITE);
        }
        
        sim.getPixel(0, 0).setColor(Color.BLUE);
        sim.getPixel(1, 0).setColor(Color.ORANGE);
        sim.getPixel(0, 1).setColor(Color.WHITE);
        sim.getPixel(1, 1).setColor(Color.WHITE);
        
        assertThat(sim.maybeRelocate(sim.getPixel(0, 0))).isTrue();*/
        
        for (Pixel pix : sim.getPixels())
        {
            pix.setColor(Color.WHITE);
        }
        
        sim.getPixel(0, 0).setColor(Color.ORANGE);
        sim.getPixel(1, 0).setColor(Color.BLUE);
        sim.getPixel(0, 1).setColor(Color.BLUE);
        sim.getPixel(1, 1).setColor(Color.BLUE);
        
        assertThat(sim.maybeRelocate(sim.getPixel(0, 0))).isFalse();
    }

    /**
     * this method cycleAgents() method, while following this 
     * description... Takes no parameters and returns an integer 
     * result. This method uses a loop over all pixels, checking 
     * each pixel in turn. If the pixel is empty, leave it alone. 
     * If it is occupied, check to see if the agent there is 
     * satisfied--if so, leave the agent alone. Otherwise, attempt 
     * to relocate it to a new position (which could succeed or fail, 
     * as described under maybeRelocate(). The method returns the total 
     * number of successful moves after processing all pixels.    
     */
    public void testCycleAgents()    
    {
        SchellingSimulation sim = new SchellingSimulation(1, 5);
        
        Pixel pixel1 = sim.getPixel(0, 0);
        pixel1.setColor(Color.WHITE);
        
        Pixel pixel2 = sim.getPixel(0, 1);
        pixel2.setColor(Color.BLUE);
        
        Pixel pixel3 = sim.getPixel(0, 2);
        pixel3.setColor(Color.BLUE);
        
        Pixel pixel4 = sim.getPixel(0, 3);
        pixel4.setColor(Color.ORANGE);
        
        Pixel pixel5 = sim.getPixel(0, 4);
        pixel5.setColor(Color.BLUE);
        
        Random.setNextInts(0, 0, 0, 0, 0);
        
        assertThat(sim.cycleAgents()).isEqualTo(1);
        
        assertThat(pixel1.getColor()).isEqualTo(Color.BLUE);
        assertThat(pixel2.getColor()).isEqualTo(Color.BLUE);
        assertThat(pixel3.getColor()).isEqualTo(Color.BLUE);
        assertThat(pixel4.getColor()).isEqualTo(Color.ORANGE);
        assertThat(pixel5.getColor()).isEqualTo(Color.WHITE);
        pixel4.setColor(Color.BLUE);
        pixel5.setColor(Color.ORANGE);
        
        assertThat(sim.cycleAgents()).isEqualTo(0);
    }
}
