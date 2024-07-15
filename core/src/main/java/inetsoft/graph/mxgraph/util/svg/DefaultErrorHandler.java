/*
 * This file is part of StyleBI.
 * Copyright (C) 2024  InetSoft Technology
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package inetsoft.graph.mxgraph.util.svg;

/**
 * This class provides a default implementation of ErrorHandler.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 */

public class DefaultErrorHandler implements ErrorHandler {

   /**
    * Implements {@link ErrorHandler#error(ParseException)}.
    * Throws the given exception.
    */
   public void error(ParseException e) throws ParseException
   {
      throw e;
   }
}
