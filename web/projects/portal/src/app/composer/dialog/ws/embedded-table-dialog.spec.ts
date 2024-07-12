/*
 * inetsoft-web - StyleBI is a business intelligence web application.
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
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { of as observableOf } from "rxjs";
import { EnterSubmitDirective } from "../../../widget/directive/enter-submit.directive";
import { ModelService } from "../../../widget/services/model.service";
import { EmbeddedTableDialogModel } from "../../data/ws/embedded-table-dialog-model";
import { Worksheet } from "../../data/ws/worksheet";
import { EmbeddedTableDialog } from "./embedded-table-dialog.component";

describe("Embedded Table Dialog Tests", () => {
   let fixture: ComponentFixture<EmbeddedTableDialog>;

   beforeEach(() => {
      let modelService = { getModel: jest.fn() };
      modelService.getModel.mockImplementation(() => observableOf(<EmbeddedTableDialogModel> {
         name: "Query2",
         rows: 1,
         cols: 1
      }));

      TestBed.configureTestingModule({
         imports: [
            FormsModule, ReactiveFormsModule
         ],
         declarations: [
            EmbeddedTableDialog, EnterSubmitDirective
         ],
         providers: [
            {
               provide: ModelService,
               useValue: modelService
            }
         ],
         schemas: [NO_ERRORS_SCHEMA]
      }).compileComponents();

      fixture = TestBed.createComponent(EmbeddedTableDialog);
      fixture.componentInstance.worksheet = {
         runtimeId: "id",
         assemblyNames: () => ["Query1"]
      } as Worksheet;
      fixture.detectChanges(true);
   });

   it("should not allow non-positive number of rows or columns", () => {
      let rowsControl = fixture.componentInstance.form.get("rows");
      let colsControl = fixture.componentInstance.form.get("cols");

      rowsControl.patchValue(-1);
      colsControl.patchValue(-1);

      expect(rowsControl.errors).toBeTruthy();
      expect(colsControl.errors).toBeTruthy();

      rowsControl.patchValue(0);
      colsControl.patchValue(0);

      expect(rowsControl.errors).toBeTruthy();
      expect(colsControl.errors).toBeTruthy();

      rowsControl.patchValue(1);
      colsControl.patchValue(1);

      expect(rowsControl.errors).toBeFalsy();
      expect(colsControl.errors).toBeFalsy();
   });

   it("should not allow duplicate names", () => {
      let nameControl = fixture.componentInstance.form.get("name");
      nameControl.patchValue("Query1");

      expect(nameControl.errors).toBeTruthy();
   });
});