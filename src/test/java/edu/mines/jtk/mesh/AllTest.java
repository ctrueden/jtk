package edu.mines.jtk.mesh;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests all classes in package edu.mines.jtk.mesh.
 * @author Dave Hale
 * @version 2006.08.02
 */
public class AllTest extends TestSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTestSuite(GeometryTest.class);
    suite.addTestSuite(TetMeshTest.class);
    suite.addTestSuite(TriMeshTest.class);

    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
