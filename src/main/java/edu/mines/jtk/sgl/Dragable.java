/**
 * Copyright 2003 - 2015, Colorado School of Mines and others.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.mines.jtk.sgl;

/**
 * An interface implemented by nodes that can be dragged with a mouse.
 * @author Dave Hale, Colorado School of Mines
 * @version 2005.06.30
 */
public interface Dragable {

  /**
   * Begins dragging.
   * @param dc the drag context.
   */
  public void dragBegin(DragContext dc);

  /**
   * During dragging, this method is called when the mouse moves.
   * @param dc the drag context.
   */
  public void drag(DragContext dc);

  /**
   * Ends dragging.
   * @param dc the drag context.
   */
  public void dragEnd(DragContext dc);
}
