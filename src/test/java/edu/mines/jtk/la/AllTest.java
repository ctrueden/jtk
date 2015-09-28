package edu.mines.jtk.la;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests all classes in package edu.mines.jtk.la.
 * @author Dave Hale
 * @version 2005.12.07
 */
public class AllTest extends TestSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTestSuite(DMatrixTest.class);
    suite.addTestSuite(DMatrixEvdTest.class);
    suite.addTestSuite(DMatrixQrdTest.class);

    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
