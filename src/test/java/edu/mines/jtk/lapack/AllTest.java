package edu.mines.jtk.lapack;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests all classes in package edu.mines.jtk.lapack.
 * @author Dave Hale
 * @version 2005.12.12
 */
public class AllTest extends TestSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTestSuite(DMatrixTest.class);
    suite.addTestSuite(DMatrixChdTest.class);
    suite.addTestSuite(DMatrixEvdTest.class);
    suite.addTestSuite(DMatrixLudTest.class);
    suite.addTestSuite(DMatrixQrdTest.class);
    suite.addTestSuite(DMatrixSvdTest.class);

    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
