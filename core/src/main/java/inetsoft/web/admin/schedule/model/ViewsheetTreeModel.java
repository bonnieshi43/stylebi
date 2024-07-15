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
package inetsoft.web.admin.schedule.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import inetsoft.sree.*;
import inetsoft.sree.internal.SUtil;
import inetsoft.util.Catalog;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Modifiable
@JsonSerialize(as = ImmutableViewsheetTreeModel.class)
@JsonDeserialize(as = ImmutableViewsheetTreeModel.class)
public interface ViewsheetTreeModel {
   String id();
   String label();
   boolean folder();
   List<ViewsheetTreeModel> children();

   static Builder builder() {
      return new Builder();
   }

   final class Builder extends ImmutableViewsheetTreeModel.Builder {
      public Builder from(RepositoryEntry entry, List<ViewsheetTreeModel> children, Catalog catalog,
                          String user)
      {
         if(entry instanceof ViewsheetEntry) {
            ViewsheetEntry vsEntry = (ViewsheetEntry) entry;
            String label = vsEntry.getName();

            id(vsEntry.getAssetEntry().toIdentifier());
            label(catalog.getString(label));
            folder(false);
            children(children);
         }
         else if(entry instanceof DefaultFolderEntry){
            DefaultFolderEntry fEntry =(DefaultFolderEntry) entry;
            String label = fEntry.getName();

            if(fEntry.isMyReport() && label != null && label.equals(SUtil.MY_REPORT)) {
               label = user;
            }

            id(fEntry.getPath());
            label(catalog.getString(label));
            folder(true);
            children(children);
         }

         return this;
      }
   }
}
