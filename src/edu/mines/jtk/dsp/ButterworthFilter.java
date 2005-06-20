/****************************************************************************
Copyright (c) 2005, Colorado School of Mines and others. All rights reserved.
This program and accompanying materials are made available under the terms of
the Common Public License - v1.0, which accompanies this distribution, and is 
available at http://www.eclipse.org/legal/cpl-v10.html
****************************************************************************/
package edu.mines.jtk.dsp;

import edu.mines.jtk.util.*;
import static edu.mines.jtk.util.MathPlus.*;

/**
 * Butterworth filter.
 * @author Dave Hale, Colorado School of Mines
 * @version 2005.04.10
 */
public class ButterworthFilter extends RecursiveCascadeFilter {

  /**
   * Filter type.
   */
  public enum Type {LOW_PASS, HIGH_PASS}

  /**
   * Construct a Butterworth filter with specified parameters.
   * The filter is specified by amplitudes at two frequencies. The
   * frequencies are in normalized units of cycles/sample. Either a 
   * low-pass or high-pass filter is constructed, depending on which 
   * of the corresponding two amplitudes is smaller. The filter is 
   * designed to match the larger (pass band) amplitude exactly, but 
   * may have amplitude lower than the smaller (reject band) amplitude.
   * @param fl the low frequency at which the amplitude al is specified.
   *  The low frequency fl must be greater than 0.0 and less than fh.
   * @param al the amplitude at the specified low frequency fl.
   *  The amplitude al must be greater than 0.0, less than 1.0, and
   *  not equal to the amplitude ah.
   * @param fh the high frequency at which the amplitude ah is is specified.
   *  The high frequency fh must be less than 0.5 and greater than fl.
   * @param ah the amplitude at the specified high frequency fh.
   *  The amplitude ah must be greater than 0.0, less than 1.0, and
   *  not equal to the amplitude al.
   */
  public ButterworthFilter(double fl, double al, double fh, double ah) {
    Check.argument(0.0<fl,"0.0<fl");
    Check.argument(fl<fh,"fl<fh");
    Check.argument(fh<0.5,"fh<0.5");
    Check.argument(0.0<al,"0.0<al");
    Check.argument(al<1.0,"al<1.0");
    Check.argument(al!=ah,"al!=ah");
    Check.argument(0.0<ah,"0.0<ah");
    Check.argument(ah<1.0,"ah<1.0");
    double wl = 2.0*DBL_PI*fl;
    double wh = 2.0*DBL_PI*fh;
    double xl = 2.0*tan(wl/2.0);
    double xh = 2.0*tan(wh/2.0);
    double pl = al*al;
    double ph = ah*ah;
    if (al>=ah) {
      int np = (int)ceil(0.5*log((pl*(1-ph))/(ph*(1-pl)))/log(xh/xl));
      double xc = xl*pow(pl/(1-pl),0.5/np);
      double wc = 2.0*atan(xc/2.0);
      double fc = 0.5*wc/DBL_PI;
      makePolesZerosGain(fc,np,Type.LOW_PASS);
    } else {
      int np = (int)ceil(0.5*log((ph*(1-pl))/(pl*(1-ph)))/log(xh/xl));
      double xc = xh*pow((1-ph)/ph,0.5/np);
      double wc = 2.0*atan(xc/2.0);
      double fc = 0.5*wc/DBL_PI;
      makePolesZerosGain(fc,np,Type.HIGH_PASS);
    }
    init(_poles,_zeros,_gain);
  }

  /**
   * Construct Butterworth filter with specified parameters.
   * @param fc the cutoff (half-power) frequency, in cycles per sample.
   *  At this cutuff frequency, the filter amplitude squared equals 0.5.
   *  The cutoff frequency must be greater than 0.0 and less than 0.5.
   * @param np the number of poles in the recursive filter.
   * @param type the filter type.
   */
  public ButterworthFilter(double fc, int np, Type type) {
    Check.argument(0.0<fc,"0.0<fc");
    Check.argument(fc<0.5,"fc<0.5");
    Check.argument(np>0,"np>0");
    makePolesZerosGain(fc,np,type);
    init(_poles,_zeros,_gain);
  }

  ///////////////////////////////////////////////////////////////////////////
  // private

  private Complex[] _poles;
  private Complex[] _zeros;
  private float _gain;

  private void makePolesZerosGain(double fc, int np, Type type) {
    boolean lowpass = type==Type.LOW_PASS;
    float omegac = (float)(2.0f*tan(DBL_PI*fc));
    float dtheta = FLT_PI/(float)np;
    float ftheta = 0.5f*dtheta*(float)(np+1);
    _poles = new Complex[np];
    _zeros = new Complex[np];
    Complex c1 = new Complex(1.0f,0.0f);
    Complex c2 = new Complex(2.0f,0.0f);
    Complex zj = (lowpass)?c1.neg():c1;
    Complex gain = new Complex(c1);
    for (int j=0,k=np-1; j<np; ++j,--k) {
      float theta = ftheta+(float)j*dtheta;
      Complex sj = Complex.polar(omegac,theta);
      _zeros[j] = zj;
      if (j==k) {
        _poles[j] = (c2.plus(sj)).over(c2.minus(sj));
        _poles[j].i = 0.0f;
      } else if (j<k) {
        _poles[j] = (c2.plus(sj)).over(c2.minus(sj));
        _poles[k] = _poles[j].conj();
      }
      if (lowpass) {
        gain.timesEquals(sj.over(sj.minus(c2)));
      } else {
        gain.timesEquals(c2.over(c2.minus(sj)));
      }
    }
    _gain = gain.r;
  }
}