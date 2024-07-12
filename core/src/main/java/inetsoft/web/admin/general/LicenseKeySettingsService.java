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
package inetsoft.web.admin.general;

import inetsoft.report.internal.license.License;
import inetsoft.report.internal.license.LicenseManager;
import inetsoft.util.audit.ActionRecord;
import inetsoft.web.admin.general.model.LicenseKeyModel;
import inetsoft.web.admin.general.model.LicenseKeySettingsModel;
import inetsoft.web.viewsheet.AuditUser;
import inetsoft.web.viewsheet.Audited;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class LicenseKeySettingsService {

   public LicenseKeySettingsModel getModel() {
      return LicenseKeySettingsModel.builder()
         .serverKeys(getServerLicenseData())
         .schedulerKeys(getSchedulerLicenseData())
         .clusterKeys(getClusterLicenseData())
         .build();
   }

   @Audited(
      actionName = ActionRecord.ACTION_NAME_EDIT,
      objectName = "Server-License",
      objectType = ActionRecord.OBJECT_TYPE_EMPROPERTY)
   public void setModel(LicenseKeySettingsModel model,
                        @SuppressWarnings("unused") @AuditUser Principal principal)
      throws Exception
   {
      setServerKeys(model.serverKeys());
      setSchedulerKeys(model.schedulerKeys());
   }

   private List<LicenseKeyModel> getServerLicenseData() {
      return LicenseManager.getInstance().getInstalledLicenses().stream()
         .map(this::createLicenseKeyModel)
         .collect(Collectors.toList());
   }

   private List<LicenseKeyModel> getSchedulerLicenseData() {
      return LicenseManager.getInstance().getSchedulerLicenses().stream()
         .map(this::createLicenseKeyModel)
         .collect(Collectors.toList());
   }

   private Map<String, String> getClusterLicenseData() {
      return LicenseManager.getInstance().getClaimedNodeLicenses();
   }

   private LicenseKeyModel createLicenseKeyModel(License license) {
      return LicenseKeyModel.builder().from(license).build();
   }

   private void setServerKeys(List<LicenseKeyModel> licenses) {
      LicenseManager manager = LicenseManager.getInstance();
      Set<License> installed = manager.getInstalledLicenses();
      updateKeys(
         licenses, installed, manager::addLicense, manager::replaceLicense, manager::removeLicense);
   }

   private void setSchedulerKeys(List<LicenseKeyModel> licenses) {
      LicenseManager manager = LicenseManager.getInstance();
      Set<License> installed = manager.getSchedulerLicenses();
      updateKeys(
         licenses, installed, manager::addSchedulerLicense, manager::replaceSchedulerLicense,
         manager::removeSchedulerLicense);
   }

   private void updateKeys(List<LicenseKeyModel> newLicenses, Set<License> installedLicenses,
                           Consumer<String> addFn, BiConsumer<String, String> replaceFn,
                           Consumer<String> removeFn)
   {
      Set<String> keys = newLicenses.stream().map(LicenseKeyModel::key).collect(Collectors.toSet());
      Set<String> installed =
         installedLicenses.stream().map(License::key).collect(Collectors.toSet());

      // ignore keys that are already installed
      keys.removeIf(installed::remove);

      for(String newKey : keys) {
         if(installed.isEmpty()) {
            // new key added
            addFn.accept(newKey);
         }
         else {
            // old key replaced
            Iterator<String> i = installed.iterator();
            String oldKey = i.next();
            i.remove();
            replaceFn.accept(oldKey, newKey);
         }
      }

      // any remaining keys that were previously installed should be removed
      for(String key : installed) {
         removeFn.accept(key);
      }
   }

   LicenseKeyModel getSingleServerLicenseKey(String requestedKey) {
      return createLicenseKeyModel(LicenseManager.getInstance().parseLicense(requestedKey));
   }

   LicenseKeyModel getSchedulerLicenseKey(String requestedKey) {
      return createLicenseKeyModel(LicenseManager.getInstance().parseLicense(requestedKey));
   }
}