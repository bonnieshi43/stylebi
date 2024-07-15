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
import { VSObjectEvent } from "./vs-object-event";

/**
 * Event used to remove columns from a table.
 */
export class RemoveTableColumnsEvent extends VSObjectEvent {
   columns: number[];

   /**
    * Creates a new instance of <tt>RemoveTableColumnsEvent</tt>.
    *
    * @param objectName the name of the object.
    * @param columns    the column indexes to remove.
    */
   constructor(objectName: string, columns: number[]) {
      super(objectName);

      this.columns = columns;
   }
}