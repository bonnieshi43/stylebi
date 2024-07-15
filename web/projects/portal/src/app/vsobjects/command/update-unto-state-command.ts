/*
 * inetsoft-web - StyleBI is a business intelligence web application.
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
import { ViewsheetCommand } from "../../common/viewsheet-client/viewsheet-command";

/**
 * Command used update the undo/redo state.
 */
export interface UpdateUndoStateCommand extends ViewsheetCommand {
   /**
    * The number of states.
    */
   points: number;

   /**
    * The index of current state.
    */
   current: number;

   /**
    * The index of the save point.
    */
   savePoint: number;

   /**
    * The id of the sheet being updated.
    */
   id?: string;
}