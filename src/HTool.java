/*
  Part of the hAPI_Fisica library - http://www.ricardmarxer.com/hAPI_Fisica

  Fisica Copyright (c) 2009 - 2010 Ricard Marxer  
  hAPI_Fisica Copyright (c) 2017 Colin Gallacher

  hAPI_Fisica is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.
  
  You should have received a copy of the GNU Lesser General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

//package hAPI_Fisica; 

import org.jbox2d.common.*;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.dynamics.*;

import processing.core.*;

/**
 * Represents a Haptic Tool body that can be added to a world. The haptic tool represents the physical representation of the haptic tool (end-effector). 
 * <pre>
 * {@code
 * HTool myTool = new HTool(2.0);
 * world.add(myTool);
 * }
 * </pre>
 *
 * @usage Bodies
 * @see FBox
 * @see FBlob
 * @see FPoly
 * @see FLine
 * @see HVirtualCoupling
 */
public class HTool extends FBody {
  protected float m_size;

  protected ShapeDef getShapeDef() {
    CircleDef pd = new CircleDef();
    pd.radius = m_size/2.0f;
    pd.density = m_density;
    pd.friction = m_friction;
    pd.restitution = m_restitution;
    pd.isSensor = m_sensor;
    return pd;
  }

  protected ShapeDef getTransformedShapeDef() {
    CircleDef pd = (CircleDef)getShapeDef();
    pd.localPosition.set(m_position);
    return pd;
  }

  /**
   * Constructs a circular haptic tool body that can be added to a world.
   *
   * @param size  the size of the circle
   */
  public HTool(float size){
    super();
    m_size = (size);
    this.setSensor(true); 
    this.setAllowSleeping(false); 
    this.m_gravityScale = 0.0f; 
     this.setFillColor(10);
    this.setStrokeColor(0);
    this.setDrawable(false); 
  }

  /**
   * Returns the size of the circle.
   *
   * @usage Bodies
   * @return the size of the circle
   */
  public float getSize(){
    return (m_size);
  }

  /**
   * Sets the size of the circle.
   * Under the hood the body is removed and readded to the world.
   *
   * @usage Bodies
   * @param size the size of the circle
   */
  public void setSize(float size){
    m_size = (size);

    this.recreateInWorld();
  }


  public void draw(PGraphics applet) {
    preDraw(applet);

    if (m_image != null ) {
      drawImage(applet);
    } else {
      applet.ellipse(0, 0, hAPI_Fisica.worldToScreen(getSize()), hAPI_Fisica.worldToScreen(getSize()));
    }

    postDraw(applet);
  }
  
  public void drawDebug(PGraphics applet) {
    preDrawDebug(applet);
        
    applet.ellipse(0, 0, hAPI_Fisica.worldToScreen(getSize()), hAPI_Fisica.worldToScreen(getSize()));
    applet.line(0, 0, hAPI_Fisica.worldToScreen(getSize()/2), hAPI_Fisica.worldToScreen(0));

    postDrawDebug(applet);
  }


}