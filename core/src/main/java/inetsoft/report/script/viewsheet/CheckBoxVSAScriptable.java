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
package inetsoft.report.script.viewsheet;

import inetsoft.report.composition.execution.ViewsheetSandbox;
import inetsoft.uql.viewsheet.internal.CheckBoxVSAssemblyInfo;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

/**
 * The checkbox viewsheet assembly scriptable in viewsheet scope.
 *
 * @version 8.5
 * @author InetSoft Technology Corp
 */
public class CheckBoxVSAScriptable extends InputVSAScriptable
   implements CompositeVSAScriptable
{
   /**
    * Create an input viewsheet assembly scriptable.
    * @param box the specified viewsheet sandbox.
    */
   public CheckBoxVSAScriptable(ViewsheetSandbox box) {
      super(box);

      cellValue = NULL;
   }

   /**
    * Get the name of the set of objects implemented by this Java class.
    */
   @Override
   public String getClassName() {
      return "CheckBoxVSA";
   }

   /**
    * Get the cell value.
    * @return the cell value, <tt>NULL</tt> no cell value.
    */
   @Override
   public Object getCellValue() {
      return cellValue;
   }

   /**
    * Set the cell value.
    * @param val the specified cell value, <tt>NULL</tt> clear cell value.
    */
   @Override
   public void setCellValue(Object val) {
      this.cellValue = val;
   }

   /**
    * Get a named property from the object.
    */
   @Override
   public Object get(String name, Scriptable start) {
      if(!(getVSAssemblyInfo() instanceof CheckBoxVSAssemblyInfo)) {
         return Undefined.instance;
      }

      if(cellValue != NULL && name.equals("value")) {
         return cellValue;
      }

      return super.get(name, start);
   }

   /**
    * Add assembly properties.
    */
   @Override
   protected void addProperties() {
      super.addProperties();

      CheckBoxVSAssemblyInfo info = getInfo();

      addProperty("title", "getTitle", "setTitle", String.class,
                  info.getClass(), info);
      addProperty("titleVisible", "isTitleVisible", "setTitleVisible",
                  boolean.class, info.getClass(), info);
      addProperty("selectFirstItemOnLoad", "isSelectFirstItem", "setSelectFirstItem",
         boolean.class, getClass(), this);
      addProperty("value", new Object[0]);
   }

   /**
    * Indicate whether or not a named property is defined in an object.
    */
   @Override
   public boolean has(String name, Scriptable start) {
      if(!(getVSAssemblyInfo() instanceof CheckBoxVSAssemblyInfo)) {
         return false;
      }

      if(cellValue != NULL && name.equals("value")) {
         return true;
      }

      return super.has(name, start);
   }

   public boolean isSelectFirstItem() {
      return this.getInfo().isSelectFirstItem();
   }

   public void setSelectFirstItem(boolean selectFirstItem) {
      this.getInfo().setSelectFirstItem(selectFirstItem);
   }

   /**
    * Get the assembly info of current check box.
    */
   private CheckBoxVSAssemblyInfo getInfo() {
      if(getVSAssemblyInfo() instanceof CheckBoxVSAssemblyInfo) {
         return (CheckBoxVSAssemblyInfo) getVSAssemblyInfo();
      }

      return new CheckBoxVSAssemblyInfo();
   }

   private Object cellValue;
}