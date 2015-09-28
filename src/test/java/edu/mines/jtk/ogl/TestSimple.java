package edu.mines.jtk.ogl;

import java.awt.*;
import javax.swing.*;

import edu.mines.jtk.ogl.GlCanvas;

/**
 * Simple OpenGL test harness. 
 * Constructs a frame that contains the specified OpenGL canvas.
 * @author Dave Hale, Colorado School of Mines
 * @version 2006.07.08
 */
class TestSimple {
  public static void run(String[] args, GlCanvas canvas) {
    run(args,canvas,false);
  }
  public static void run(
    String[] args, GlCanvas canvas, boolean autoRepaint) 
  {
    run(canvas,autoRepaint);
  }
  public static void run(GlCanvas canvas, boolean autoRepaint) {
    run(canvas,autoRepaint,null);
  }
  public static void run(
    final GlCanvas canvas, final boolean autoRepaint, final String fileName) 
  {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        canvas.setAutoRepaint(autoRepaint);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(SIZE,SIZE));
        frame.getContentPane().add(canvas,BorderLayout.CENTER);
        frame.setVisible(true);
        if (fileName!=null)
          canvas.paintToFile(fileName);
      }
    });
  }
  private static final int SIZE = 600;
}
