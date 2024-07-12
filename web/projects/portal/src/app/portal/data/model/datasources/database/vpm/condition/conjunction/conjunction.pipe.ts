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
import { Pipe, PipeTransform } from "@angular/core";
import { ConjunctionModel } from "./conjunction-model";

/**
 * Conjunction pipe
 *
 * Converts a conjunction into its string representation
 *
 */
@Pipe({
   name: "conjunctionToString"
})
export class ConjunctionPipe implements PipeTransform {
   transform(conjunction: ConjunctionModel): string {
      let indent: string = "";

      for(let i = 0; i < conjunction.level; i++) {
         indent += "....";
      }

      return indent + (conjunction.isNot ? "not " : "") + conjunction.conjunction;
   }
}