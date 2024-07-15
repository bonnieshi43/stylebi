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
package inetsoft.web.viewsheet.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * {@link inetsoft.uql.viewsheet.internal.ScaleInfo}
 */
@Value.Immutable
@Value.Style(allParameters = true)
@JsonSerialize(as = ImmutableScaleInfoModel.class)
public interface ScaleInfoModel {
   boolean preserveAspectRatio();
   boolean tiled();
   boolean scaleImage();

   static ScaleInfoModel of(boolean preserveAspectRatio, boolean tiled, boolean scaleImage) {
      return ImmutableScaleInfoModel.of(preserveAspectRatio, tiled, scaleImage);
   }
}
