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
package inetsoft.web.service;

import inetsoft.report.internal.license.LicenseManager;
import inetsoft.sree.RepletException;
import inetsoft.sree.internal.AnalyticEngine;
import inetsoft.sree.internal.SUtil;
import inetsoft.uql.asset.AssetRepository;
import inetsoft.util.Tool;
import inetsoft.web.portal.model.LicenseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class RootAppAccessController {
   @Autowired
   public RootAppAccessController(AssetRepository assetRepository) {
      this.assetRepository = assetRepository;
   }

   @GetMapping("api/license-info")
   public LicenseInfo getLicenseInfo(Principal principal) {
      return LicenseInfo.builder()
         .valid(LicenseManager.getInstance().isLicensed())
         .access(checkAccess(principal))
         .build();
   }

   @GetMapping("/api/enterprise")
   public boolean isEnterprise(@SuppressWarnings("unused") Principal principal) {
      return LicenseManager.getInstance().isEnterprise();
   }

   /**
    * Check if the access matches license.
    */
   private boolean checkAccess(Principal principal) {
      if(assetRepository instanceof AnalyticEngine) {
         try {
            ((AnalyticEngine) assetRepository).checkAccess(principal);
         }
         catch(RepletException ex) {
            return false;
         }
      }

      return true;
   }

   private final AssetRepository assetRepository;
}