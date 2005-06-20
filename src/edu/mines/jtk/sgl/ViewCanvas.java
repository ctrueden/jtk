/****************************************************************************
Copyright (c) 2004, Colorado School of Mines and others. All rights reserved.
This program and accompanying materials are made available under the terms of
the Common Public License - v1.0, which accompanies this distribution, and is 
available at http://www.eclipse.org/legal/cpl-v10.html
****************************************************************************/
package edu.mines.jtk.sgl;

import edu.mines.jtk.opengl.*;
import static edu.mines.jtk.opengl.Gl.*;

/**
 * An OpenGL canvas on which a view draws its world.
 * <p>
 * The relationship between views and view canvases is one-to-many. A view 
 * canvas is managed by only one view, but that view may draw its world on
 * on one or more view canvases.
 * @author Dave Hale, Colorado School of Mines
 * @version 2005.05.24
 */
public class ViewCanvas extends GlCanvas {

  /**
   * Constructs a canvas with no view.
   */
  public ViewCanvas() {
  }

  /**
   * Constructs a canvas for the specified view.
   * @param view the view.
   */
  public ViewCanvas(View view) {
    setView(view);
  }

  /**
   * Sets the view that draws on this canvas.
   * @param view the view; null, if none.
   */
  public void setView(View view) {
    if (_view!=null)
      _view.removeCanvas(this);
    _view = view;
    if (_view!=null) {
      _view.addCanvas(this);
      _view.updateTransforms();
      _view.repaint();
    }
  }

  /**
   * Gets the view that draws on this canvas.
   * @return the view; null, if none.
   */
  public View getView() {
    return _view;
  }

  /**
   * Sets the view-to-cube transform for this canvas.
   * Typically, the view sets the view-to-cube transform.
   * @param viewToCube the view-to-cube transform; copied, not referenced.
   */
  public void setViewToCube(Matrix44 viewToCube) {
    _viewToCube = viewToCube.clone();
    repaint();
  }

  /**
   * Gets the view-to-cube transform for this canvas.
   * @return the view-to-cube transform; by copy, not by reference.
   */
  public Matrix44 getViewToCube() {
    return _viewToCube.clone();
  }

  /**
   * Sets the cube-to-pixel transform for this canvas.
   * Typically, the view sets the cube-to-pixel transform.
   * @param cubeToPixel the cube-to-pixel transform; copied, not referenced.
   */
  public void setCubeToPixel(Matrix44 cubeToPixel) {
    _cubeToPixel = cubeToPixel.clone();
    repaint();
  }

  /**
   * Gets the cube-to-pixel transform for this canvas.
   * @return the cube-to-pixel transform; by copy, not by reference.
   */
  public Matrix44 getCubeToPixel() {
    return _cubeToPixel.clone();
  }

  /**
   * Gets the pixel z coordinate at the specified pixel x and y coordinates.
   * Reads the front depth buffer of this canvas at the specified pixel 
   * coordinates (xp,yp) to compute (approximately) the pixel z coordinate zp. 
   * <p>
   * The pixel z coordinate is a floating point number between 0.0 and 1.0. 
   * The value zp = 0.0 corresponds to the near clipping plane, and the value 
   * zp = 1.0 corresponds to the far clipping plane.
   * @param xp the pixel x coordinate.
   * @param yp the pixel y coordinate.
   * @return the pixel z coordinate.
   */
  public double getPixelZ(int xp, int yp) {
    int hp = getHeight();
    GlContext context = getContext();
    double zp = 0.0;
    context.lock();
    try {
      glPushAttrib(GL_PIXEL_MODE_BIT);
      glReadBuffer(GL_FRONT);
      float[] pixels = {0.0f};
      glReadPixels(xp,hp-1-yp,1,1,GL_DEPTH_COMPONENT,GL_FLOAT,pixels);
      zp = pixels[0];
      glPopAttrib();
    } finally {
      context.unlock();
    }
    return zp;
  }

  public void glPaint() {
    if (_view!=null)
      _view.draw(this);
  }

  public void glResize(
    int width, int height, int widthBefore, int heightBefore)
  {
    if (_view!=null)
      _view.updateTransforms(this);
  }

  ///////////////////////////////////////////////////////////////////////////
  // private

  private View _view;
  private Matrix44 _viewToCube = Matrix44.identity();
  private Matrix44 _cubeToPixel = Matrix44.identity();
}