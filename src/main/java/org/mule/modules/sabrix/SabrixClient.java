/**
 * Mule Sabrix Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc. All rights reserved. http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.sabrix;

import javax.validation.constraints.NotNull;

import com.sabrix.services.taxservice._2009_12_20.DocumentCollection;
import com.sabrix.services.taxservice._2009_12_20.HostRequestInfo;
import com.sabrix.services.taxservice._2009_12_20.TaxResponse;

/**
 * Client to interact with Sabrix service
 *
 * @author flbulgarelli
 */
public interface SabrixClient
{

    /**
     * Retrieves taxes
     *
     * @param documents
     * @param externalCompanyId
     * @param hostRequestInfo
     * @return the tax response
     */
    TaxResponse getTaxes(@NotNull DocumentCollection documents,
                         @NotNull String externalCompanyId,
                         @NotNull HostRequestInfo hostRequestInfo);

}
