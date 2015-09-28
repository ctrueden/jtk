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
package edu.mines.jtk.util;

/**
 * Utilities for XML formatting.
 * @author Dave Hale, Colorado School of Mines
 * @version 06/25/1998, 08/24/2006.
 */
class XmlUtil {

  /**
   * Quotes an XML attribute value.
   * Replaces special characters and XML entities.
   * Encloses string in double or single quotes, depending on what's inside.
   * @param s attribute value to be quoted.
   * @return quoted attribute value. 
   */
  public static String quoteAttributeValue(String s) {
    if (s==null) return null;
    s = replaceAll("&","&amp;",s);
    s = replaceAll("<","&lt;",s);
    s = replaceAll("\r","&#13;",s);
    s = replaceAll("\n","&#10;",s);
    s = replaceAll("\t","&#9;",s);
    String quote = "\"";
    if (s.contains("\"") && !s.contains("\'")) {
      quote = "\'";
    } else {
      s = replaceAll("\"","&quot;",s);
    }
    return quote+s+quote;
  }

  /**
   * Quotes character data.
   * Replaces special characters and XML entities.
   * Encloses string in double quotes if it contains whitespace.
   * @param s character data to be quoted.
   * @return quoted character data.
   */
  public static String quoteCharacterData(String s) {
    final char space = '\u0020';
    if (s==null) return null;
    s = replaceAll("&","&amp;",s);
    s = replaceAll("<","&lt;",s);
    s = replaceAll("\\","\\\\",s);
    s = replaceAll("\r","\\r",s);
    s = replaceAll("\n","\\n",s);
    s = replaceAll("\t","\\t",s);
    s = replaceAll("\"","\\\"",s);
    s = replaceAll("\'","\\\'",s);
    String quote = "";
    if (s.length()==0) {
      quote = "\"";
    } else {
      for (int i=0; i<s.length(); ++i) {
        if (s.charAt(i)<=space) {
          quote = "\"";
          break;
        }
      }
    }
    return quote+s+quote;
  }

  /**
   * Replaces all occurences in string s of string x with string y.
   * @param x string to be replaced.
   * @param y string replacing each occurence of x.
   * @param s string containing zero or more x to be replaced.
   * @return string s with all x replaced with y.
   */
  private static String replaceAll(String x, String y, String s) {
    if (s==null) return null;
    int from = 0;
    int to = s.indexOf(x,from);
    if (to<0) return s;
    StringBuilder d = new StringBuilder(s.length()+32);
    while (to>=0) {
      d.append(s.substring(from,to));
      d.append(y);
      from = to+x.length();
      to = s.indexOf(x,from);
    }
    return d.append(s.substring(from)).toString();
  }
}
