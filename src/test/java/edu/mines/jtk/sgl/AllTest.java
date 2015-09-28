package edu.mines.jtk.sgl;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests all classes in package edu.mines.jtk.sgl.
 * @author Dave Hale
 * @version 2005.05.20
 */
public class AllTest extends TestSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTestSuite(BoundingTest.class);
    suite.addTestSuite(BoundingBoxTreeTest.class);
    suite.addTestSuite(MatrixPointVectorTest.class);

    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
