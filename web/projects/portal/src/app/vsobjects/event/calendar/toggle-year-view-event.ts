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
import { ViewsheetEvent } from "../../../common/viewsheet-client/index";
import { CalendarSelectionEvent } from "./calendar-selection-event";

/**
 * Class that encapsulates an event sent to the server to instruct it to toggle
 * year view mode of a calendar
 */
export class ToggleYearViewEvent extends CalendarSelectionEvent implements ViewsheetEvent {
   /**
    * The type of update to apply.
    */
   public yearView: boolean;

   /**
    * Creates a new instance of <tt>ToggleYearViewEvent</tt>.
    *
    * @param currentDate1  the current date of calendar 1
    * @param currentDate2  the current date of calendar 2
    * @param yearView      if calendar is year view
    */
   constructor(currentDate1: string, currentDate2: string, yearView: boolean)
   {
      // when changing year view, dates are rese
      super(null, currentDate1, currentDate2);
      this.yearView = yearView;
   }
}