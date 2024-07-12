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
package inetsoft.web.graph.model;

import java.awt.geom.RectangularShape;

public class ChartTile {
   public ChartTile(RectangularShape bounds, int row, int col) {
      this.bounds = bounds;
      this.row = row;
      this.col = col;
   }

   public RectangularShape getBounds() {
      return bounds;
   }

   public int getRow() {
      return row;
   }

   public int getCol() {
      return col;
   }

   private RectangularShape bounds;
   private int row;
   private int col;
}