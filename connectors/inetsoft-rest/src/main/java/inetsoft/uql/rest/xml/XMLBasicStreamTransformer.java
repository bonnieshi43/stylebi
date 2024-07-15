/*
 * inetsoft-rest - StyleBI is a business intelligence web application.
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
package inetsoft.uql.rest.xml;

import java.io.InputStream;

/**
 * Basic XML stream transformer which transform an XML stream into a java object.
 */
class XMLBasicStreamTransformer extends AbstractXMLStreamTransformer {
   XMLBasicStreamTransformer(RestXMLQuery query) throws Exception {
      super(query);
   }

   @Override
   protected InputStream getXSLTInputStream() {
      return loader.getResourceAsStream("basic-xpath.xslt");
   }

   private static final ClassLoader loader = XMLBasicStreamTransformer.class.getClassLoader();
}
