/*
 * inetsoft-core - StyleBI is a business intelligence web application.
 * Copyright © 2024 InetSoft Technology (info@inetsoft.com)
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
 * You should have received a copy of the GNU Affrero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package inetsoft.web.composer.ws.event;

public class WSDropTableIntoJoinSchemaEvent {

   public String getJoinTable() {
      return joinTable;
   }

   public void setJoinTable(String joinTable) {
      this.joinTable = joinTable;
   }

   public String getDroppedTable() {
      return droppedTable;
   }

   public void setDroppedTable(String droppedTable) {
      this.droppedTable = droppedTable;
   }

   public double getTop() {
      return top;
   }

   public void setTop(double top) {
      this.top = top;
   }

   public double getLeft() {
      return left;
   }

   public void setLeft(double left) {
      this.left = left;
   }

   private String joinTable;
   private String droppedTable;
   private double top;
   private double left;
}
