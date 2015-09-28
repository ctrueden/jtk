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
package edu.mines.jtk.bench;

import java.util.ArrayList;

import edu.mines.jtk.util.Stopwatch;

/**
 * Benchmark ArrayList of primitives.
 * @author Dave Hale, Colorado School of Mines
 * @version 2005.02.17
 */
public class ArrayListBench {

  private static final int INITIAL_CAPACITY = 8;

  public static class FloatList {
    public int n;
    public float[] a = new float[INITIAL_CAPACITY];
    public void add(float f) {
      if (n==a.length) {
        float[] t = new float[2*a.length];
        System.arraycopy(a,0,t,0,n);
        a = t;
      }
      a[n++] = f;
    }
    public float[] trim() {
      float[] t = new float[n];
      System.arraycopy(a,0,t,0,n);
      return t;
    }
  }

  public static void main(String[] args) {
    double maxtime = 2;
    int n = 10000;
    double rate;
    for (int niter=0; niter<5; ++niter) {
      rate = benchArrayList(maxtime,n);
      System.out.println("ArrayList<Float> rate="+rate);
      rate = benchFloatList(maxtime,n);
      System.out.println("FloatList        rate="+rate);
    }
  }

  interface ListMaker {
    public void makeList(int n);
  }

  static double benchList(double maxtime, int n, ListMaker lm) {
    Stopwatch sw = new Stopwatch();
    sw.start();
    int niter;
    for (niter=0; sw.time()<maxtime; ++niter)
      lm.makeList(n);
    sw.stop();
    return (double)n*(double)niter/sw.time()*1.0e-6;
  }

  static double benchArrayList(double maxtime, int n) {
    return benchList(maxtime,n,new ListMaker() {
      public void makeList(int n) {
        ArrayList<Float> list = new ArrayList<Float>(INITIAL_CAPACITY);
        float f = 0.0f;
        for (int i=0; i<n; ++i) {
          list.add(f);
          f += 1.0f;
        }
      }
    });
  }

  static double benchFloatList(double maxtime, int n) {
    return benchList(maxtime,n,new ListMaker() {
      public void makeList(int n) {
        FloatList list = new FloatList();
        float f = 0.0f;
        for (int i=0; i<n; ++i) {
          list.add(f);
          f += 1.0f;
        }
      }
    });
  }
}
