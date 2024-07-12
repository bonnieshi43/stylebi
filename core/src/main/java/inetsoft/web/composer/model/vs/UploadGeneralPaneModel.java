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
package inetsoft.web.composer.model.vs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadGeneralPaneModel {
   public GeneralPropPaneModel getGeneralPropPaneModel() {
      if(generalPropPaneModel == null) {
         generalPropPaneModel = new GeneralPropPaneModel();
      }

      return generalPropPaneModel;
   }

   public void setGeneralPropPaneModel(
      GeneralPropPaneModel generalPropPaneModel)
   {
      this.generalPropPaneModel = generalPropPaneModel;
   }

   public LabelPropPaneModel getLabelPropPaneModel() {
      if(labelPropPaneModel == null) {
         labelPropPaneModel = new LabelPropPaneModel();
      }

      return labelPropPaneModel;
   }

   public void setLabelPropPaneModel(
      LabelPropPaneModel labelPropPaneModel)
   {
      this.labelPropPaneModel = labelPropPaneModel;
   }

   public SizePositionPaneModel getSizePositionPaneModel() {
      if(sizePositionPaneModel == null) {
         sizePositionPaneModel = new SizePositionPaneModel();
      }

      return sizePositionPaneModel;
   }

   public void setSizePositionPaneModel(SizePositionPaneModel sizePositionPaneModel) {
      this.sizePositionPaneModel = sizePositionPaneModel;
   }

   @Override
   public String toString() {
      return "UploadGeneralPaneModel{" +
         "generalPropPaneModel=" + generalPropPaneModel +
         ", labelPropPaneModel=" + labelPropPaneModel +
         ", sizePositionPaneModel=" + sizePositionPaneModel +
         '}';
   }

   private GeneralPropPaneModel generalPropPaneModel;
   private LabelPropPaneModel labelPropPaneModel;
   private SizePositionPaneModel sizePositionPaneModel;
}