package edu.mines.jtk.awt;
 
import junit.framework.Test;
import junit.framework.TestSuite;
 
/**
 * Tests all classes in package edu.mines.jtk.awt.
 * @author Chris Engelsma
 * @version 2015.09.25
 */
public class AllTest extends TestSuite {
 
  public static Test suite() {
    TestSuite suite = new TestSuite();
 
    suite.addTestSuite(ColorMapTest.class);
 
    return suite;
  }
 
  /**
   * Self test.  Runs the test suite.
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
