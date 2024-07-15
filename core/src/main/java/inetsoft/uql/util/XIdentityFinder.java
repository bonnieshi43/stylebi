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
package inetsoft.uql.util;

import inetsoft.sree.security.IdentityID;
import inetsoft.uql.XPrincipal;

import java.security.Principal;

/**
 * XIdentityFinder, the finder for identities like User, Group, Role, etc.
 *
 * @version 8.5
 * @author InetSoft Technology Corp
 */
public interface XIdentityFinder {
   /**
    * Get the roles for the user identified by the specified Principal.
    *
    * @param user a Principal object that identifies the user.
    *
    * @return an array of role names.
    */
   public IdentityID[] getUserRoles(Principal user);

   /**
    * Get the groups for the user identified by the specified Principal.
    * @param user a Principal object that identifies the user.
    * @return an array of group names.
    */
   public String[] getUserGroups(Principal user);

   /**
    * Get the organization ID assigned to the user identified by the specified Principal.
    * @param user a Principal object that identifies the user.
    * @return the organization ID.
    */
   public String getUserOrganizationId(Principal user);

   /**
    * Get all the users.
    */
   public IdentityID[] getUsers();

   /**
    * Create principal for the specified identity.
    */
   public XPrincipal create(Identity id);

   /**
    * Check whether security exists.
    */
   public boolean isSecurityExisting();
}
