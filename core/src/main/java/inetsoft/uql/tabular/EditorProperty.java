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
package inetsoft.uql.tabular;

/**
 * Annotation used to provide a editor-specific property. It's an attribute
 * in the PropertyEditor annotation.
 */
public @interface EditorProperty {
   /**
    * The name of the property.
    *
    * @return the property name.
    */
   String name();

   /**
    * The static property value. Either this or <i>method</i> must be specified.
    */
   String value() default "";

   /**
    * The name of the method that provides the property value. Either this or
    * <i>value</i> must be specified.
    */
   String method() default "";
}
