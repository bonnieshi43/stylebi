/*
 * inetsoft-core - StyleBI is a business intelligence web application.
 * Copyright © 2024 InetSoft Technology (info@inetsoft.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package inetsoft.graph.aesthetic;

import com.inetsoft.build.tern.TernMethod;
import inetsoft.graph.data.DataSet;
import inetsoft.graph.scale.Scale;

import java.lang.reflect.Method;

/**
 * This class defines a shape frame for numeric values.
 *
 * @version 10.0
 * @author InetSoft Technology
 */
public abstract class LinearShapeFrame extends ShapeFrame {
   /**
    * Get a shape at the relative scale.
    * @param ratio a value from 0 to 1.
    */
   protected abstract GShape getShape(double ratio);

   /**
    * Get a shape for the specified value.
    */
   @Override
   @TernMethod
   public GShape getShape(Object val) {
      Scale scale = getScale();

      if(scale == null) {
         return getShape(1);
      }

      double v = scale.map(val);

      if(Double.isNaN(v)) {
         return null;
      }

      double min = scale.getMin();
      double max = scale.getMax();

      return getShape(Math.min(1, (v - min) / (max - min)));
   }

   /**
    * Get the shape for the specified cell.
    * @param data the specified dataset.
    * @param col the name of the specified column.
    * @param row the specified row index.
    */
   @Override
   public GShape getShape(DataSet data, String col, int row) {
      Object obj = data.getData(getField(), row);
      return getShape(obj);
   }

   /**
    * Legend is always visible.
    */
   @Override
   boolean isMultiItem(Method getter) throws Exception {
      return true;
   }
}