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
package inetsoft.web.vswizard.recommender.execution.data;

/**
 * @version 13.2
 * @author InetSoft Technology Corp
 */
public class HierarchyData implements WizardData {
   public HierarchyData(boolean hierarchical) {
      this.hierarchical = hierarchical;
   }

   public void setHierarchical(boolean hierarchical) {
       this.hierarchical = hierarchical;
   }

   public boolean isHierarchical() {
       return hierarchical;
   }

   @Override
   public String toString() {
      return "hierarchical: " + hierarchical;
   }

   private boolean hierarchical = false;
}