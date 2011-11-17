/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.sabrix;

import ar.com.zauber.commons.ws.connection.ConnectionBuilder;

import com.sabrix.services.taxservice._2009_12_20.DocumentCollection;
import com.sabrix.services.taxservice._2009_12_20.HostRequestInfo;
import com.sabrix.services.taxservice._2009_12_20.TaxRequest;
import com.sabrix.services.taxservice._2009_12_20.TaxResponse;
import com.sabrix.services.taxservice._2009_12_20.TaxService;
import com.sabrix.services.taxservice._2009_12_20.TaxServiceSoap;

public class CxfSabrixClient implements SabrixClient
{

    private final String endpoint;
    private final String username;
    private final String password;

    public CxfSabrixClient(@NotEmpty String endpoint, @NotEmpty  String username, @NotEmpty  String password)
    {
        this.endpoint = endpoint;
        this.username = username;
        this.password = password;
    }

    public TaxResponse getTaxes(@NonNull final DocumentCollection documents_,
                                @NonNull final String externalCompanyId_,
                                @NonNull final HostRequestInfo hostRequestInfo_)
    {
        return getConnection().getTax(new TaxRequest(){{
                this.documents = documents_;
                this.setExternalCompanyId(externalCompanyId_);
                this.setHostRequestInfo(hostRequestInfo_);
            }});
    }

    protected TaxServiceSoap getConnection()
    {
        return ConnectionBuilder.fromPortType(TaxServiceSoap.class)
            .withServiceType(TaxService.class)
            .withClasspathWsdl("TaxService.wsdl")
            .withEndpoint(endpoint)
            .withBasicAuth(username, password)
            .build();
    }


}
