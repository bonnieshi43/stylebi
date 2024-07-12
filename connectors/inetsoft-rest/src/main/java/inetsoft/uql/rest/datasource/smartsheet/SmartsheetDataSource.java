/*
 * inetsoft-rest - StyleBI is a business intelligence web application.
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
package inetsoft.uql.rest.datasource.smartsheet;

import inetsoft.uql.rest.auth.AuthType;
import inetsoft.uql.rest.json.EndpointJsonDataSource;
import inetsoft.uql.tabular.*;
import inetsoft.util.Tool;
import org.w3c.dom.Element;

import java.io.PrintWriter;
import java.util.Objects;

@View(vertical = true, value = {
   @View1("accessToken")
})
public class SmartsheetDataSource extends EndpointJsonDataSource<SmartsheetDataSource> {
   static final String TYPE = "Rest.Smartsheet";

   public SmartsheetDataSource() {
      super(TYPE, SmartsheetDataSource.class);
      setAuthType(AuthType.NONE);
   }

   @Property(label = "Access Token", required = true, password = true)
   public String getAccessToken() {
      return accessToken;
   }

   public void setAccessToken(String accessToken) {
      this.accessToken = accessToken;
   }

   @Override
   public String getURL() {
      return "https://api.smartsheet.com";
   }

   @Override
   public void setURL(String url) {
      // no-op
   }

   @Override
   public HttpParameter[] getQueryHttpParameters() {
      HttpParameter authParam = new HttpParameter();
      authParam.setType(HttpParameter.ParameterType.HEADER);
      authParam.setName("Authorization");
      authParam.setValue("Bearer " + accessToken);

      return new HttpParameter[] { authParam };
   }

   @Override
   public void setQueryHttpParameters(HttpParameter[] parameters) {
      // no-op
   }

   @Override
   public void writeContents(PrintWriter writer) {
      super.writeContents(writer);

      if(accessToken != null) {
         writer.format(
            "<accessToken><![CDATA[%s]]></accessToken>%n", Tool.encryptPassword(accessToken));
      }
   }

   @Override
   public void parseContents(Element root) throws Exception {
      super.parseContents(root);
      accessToken = Tool.decryptPassword(Tool.getChildValueByTagName(root, "accessToken"));
   }

   @Override
   protected String getTestSuffix() {
      return "/2.0/users/me";
   }

   @Override
   public boolean equals(Object o) {
      if(this == o) {
         return true;
      }

      if(!(o instanceof SmartsheetDataSource)) {
         return false;
      }

      if(!super.equals(o)) {
         return false;
      }

      SmartsheetDataSource that = (SmartsheetDataSource) o;
      return Objects.equals(accessToken, that.accessToken);
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), accessToken);
   }

   private String accessToken;
}