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
package inetsoft.analytic.composition.command;

/**
 * Resize detail pane command.
 *
 * @version 10.2
 * @author InetSoft Technology Corp
 */
public class ResizeDetailPaneCommand extends ViewsheetCommand {
   /**
    * Constructor.
    */
   public ResizeDetailPaneCommand() {
      super();
   }

   /**
    * Constructor.
    */
   public ResizeDetailPaneCommand(String type, int[] lengths) {
      this();
      put("type", type);
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < lengths.length; i++) {
         if(sb.length() > 0) {
            sb.append(",");
         }

         sb.append(lengths[i]);
      }

      put("lengths", sb.toString());
   }
}