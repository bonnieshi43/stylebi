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
package inetsoft.web.factory;

import inetsoft.uql.viewsheet.ViewsheetLifecycleMessageChannel;
import inetsoft.util.SingletonManager;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Factory for {@link ViewsheetLifecycleMessageChannel}
 */
@Component
@Lazy
public class ViewsheetLifecycleMessageChannelFactory
   extends AbstractFactoryBean<ViewsheetLifecycleMessageChannel>
{
   /**
    * @inheritDoc
    */
   @Override
   public Class<ViewsheetLifecycleMessageChannel> getObjectType() {
      return ViewsheetLifecycleMessageChannel.class;
   }

   /**
    * @inheritDoc
    */
   @Override
   protected ViewsheetLifecycleMessageChannel createInstance() {
      return SingletonManager.getInstance(ViewsheetLifecycleMessageChannel.class);
   }
}
