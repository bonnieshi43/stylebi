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
package inetsoft.uql.jdbc.util;

import inetsoft.uql.table.XShortColumn;
import inetsoft.uql.table.XTableColumnCreator;

import java.sql.Types;

/**
 * SQLTypes specialization that handles DB2 databases.
 *
 * @author  InetSoft Technology
 * @since   9.1
 */
public final class DB2SQLTypes extends SQLTypes {
   /**
    * Get the table column creator.
    * @param type the specified sql type.
    * @param tname the specified sql type name.
    * @return the table column creator.
    */
   @Override
   public XTableColumnCreator getXTableColumnCreator(int type, String tname) {
      if(type == Types.SMALLINT) {
         return XShortColumn.getCreator();
      }

      return super.getXTableColumnCreator(type, tname);
   }
}
