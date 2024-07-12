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
package inetsoft.web.binding.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import inetsoft.web.binding.dnd.TableAddColumnInfo;
import inetsoft.web.binding.dnd.TableRemoveColumnInfo;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableVSChartDndEvent.class)
@JsonDeserialize(as = ImmutableVSChartDndEvent.class)
public interface VSTableDndEvent extends VSColumnDndEvent {
   /**
    * Get the dnd transfer.
    * @return dnd transfer.
    */
   @Nullable
   TableAddColumnInfo addInfo();
   /**
    * Get the drop target.
    * @return drop target.
    */
   @Nullable
   TableRemoveColumnInfo removeInfo();
}