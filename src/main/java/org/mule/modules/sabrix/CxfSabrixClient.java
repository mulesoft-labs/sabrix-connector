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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;

import javax.validation.constraints.NotNull;
import javax.xml.ws.BindingProvider;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

/**
 * {@link CxfSabrixClient}
 * @author flbulgarelli
 */
public class CxfSabrixClient implements SabrixClient
{

    private final String endpoint;
    private final String username;
    private final String password;
    private TaxServiceSoap connection;

    public CxfSabrixClient(@NotNull String endpoint, @NotNull String username, @NotNull String password)
    {
        Validate.notEmpty(endpoint);
        Validate.notEmpty(username);
        Validate.notEmpty(password);
        this.endpoint = endpoint;
        this.username = username;
        this.password = password;
        this.initConnection();
    }

    private void initConnection()
    {
        connection = ConnectionBuilder.fromPortType(TaxServiceSoap.class)
        .withServiceType(TaxService.class)
        .withClasspathWsdl("TaxService.wsdl")
        .withEndpoint(endpoint)
        .withUsernameTokenAuth(username, password)
        .build();

    }

    public TaxResponse getTaxes(@NotNull final DocumentCollection documents_,
                                @NotNull final String externalCompanyId_,
                                @NotNull final HostRequestInfo hostRequestInfo_)
    {
        Validate.notNull(documents_);
        Validate.notNull(externalCompanyId_);
        Validate.notNull(hostRequestInfo_);

        return getConnection().getTax(new TaxRequest(){{
                this.setDocuments(documents_);
                this.setExternalCompanyId(externalCompanyId_);
                this.setHostRequestInfo(hostRequestInfo_);
            }});
    }

    protected TaxServiceSoap getConnection()
    {
        return connection;
    }


}
