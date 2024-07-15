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
import { Component, Input, OnInit } from "@angular/core";
import { UploadGeneralPaneModel } from "../../data/vs/upload-general-pane-model";
import { UntypedFormGroup } from "@angular/forms";
import { TreeNodeModel } from "../../../widget/tree/tree-node-model";

@Component({
   selector: "upload-general-pane",
   templateUrl: "upload-general-pane.component.html",
})
export class UploadGeneralPane implements OnInit {
   @Input() model: UploadGeneralPaneModel;
   @Input() form: UntypedFormGroup;
   @Input() vsId: string;
   @Input() variableValues: string[];
   @Input() columnTreeRoot: TreeNodeModel = null;
   @Input() functionTreeRoot: TreeNodeModel = null;
   @Input() operatorTreeRoot: TreeNodeModel = null;

   ngOnInit(): void {
      this.form.addControl("generalPropPaneForm", new UntypedFormGroup({}));
      this.form.addControl("sizePositionPaneForm", new UntypedFormGroup({}));
   }
}
