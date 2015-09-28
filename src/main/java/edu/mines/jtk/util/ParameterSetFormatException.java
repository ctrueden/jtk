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
 * Exception thrown when the format of a parameter set is not valid.
 *
 * @author Dave Hale, Colorado School of Mines
 * @version 06/25/1998, 08/24/2006.
 */
public class ParameterSetFormatException extends RuntimeException {
  ParameterSetFormatException() {
    super();
  }
  ParameterSetFormatException(String s) {
    super(s);
  }
}
