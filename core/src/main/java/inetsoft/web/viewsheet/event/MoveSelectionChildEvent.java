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
package inetsoft.web.viewsheet.event;


/**
 * Class that encapsulates the parameters for moving a selection child.
 *
 * @since 12.3
 */
public class MoveSelectionChildEvent {
   public int getFromIndex() {
      return fromIndex;
   }

   public void setFromIndex(int fromIndex) {
      this.fromIndex = fromIndex;
   }

   public int getToIndex() {
      return toIndex;
   }

   public void setToIndex(int toIndex) {
      this.toIndex = toIndex;
   }

   public boolean isCurrentSelection() {
      return currentSelection;
   }

   public void setCurrentSelection(boolean currentSelection) {
      this.currentSelection = currentSelection;
   }

   @Override
   public String toString() {
      return "{fromIndex: " + fromIndex +
         ", toIndex: " + toIndex +
         ", currentSelection: " + currentSelection +
         "}";
   }

   private int fromIndex;
   private int toIndex;
   private boolean currentSelection;
}
