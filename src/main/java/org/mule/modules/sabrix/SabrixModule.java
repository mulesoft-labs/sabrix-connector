/**
 * Mule Sabrix Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */

package org.mule.modules.sabrix;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.Processor;
import org.mule.modules.util.internal.CollectionInliner;

import ar.com.zauber.commons.mom.MapObjectMapper;
import ar.com.zauber.commons.mom.MapObjectMappers;
import ar.com.zauber.commons.mom.style.impl.CXFStyle;

import com.sabrix.services.taxservice._2009_12_20.DocumentCollection;
import com.sabrix.services.taxservice._2009_12_20.HostRequestInfo;
import com.sabrix.services.taxservice._2009_12_20.TaxResponse;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

/**
 * <a href="http://onesource.thomsonreuters.com/solutions/indirect-tax/sales-use/sales-use-solutions/">Indirect Tax</a>
 * is a software Tax solution, previously and better known as Sabrix, which exposes a single operation
 * to calculates taxes for a given transaction.
 *
 * This connector lets Mule users to consume that service.
 *
 * @author flbulgarelli
 */
@Module(name = "sabrix", schemaVersion = "1.0")
public class SabrixModule
{
    /**
     * The Sabrix login username
     */
    @Configurable
    private String username;

    /**
     * The Sabrix login password
     */
    @Configurable
    private String password;

    /**
     * The Sabrix
     */
    @Configurable
    private String endpoint;

    private SabrixClient sabrixClient;

    private final MapObjectMapper mom;
    {
        mom = MapObjectMappers.defaultWithPackage("com.sabrix.services")
            .withSetterStyle(CXFStyle.STYLE)
            .withInterceptor(new CollectionInliner())
            .build();
    }


    /**
     * Calculate taxes using host information, transaction information, and an
     * external company id
     *
     * {@sample.xml ../../../doc/connector.xml.sample sabrix:get-taxes}
     *
     * @param documents The list of documents that are required to get their respective
     *                  taxes.
     * @param externalCompanyId The company id.
     * @param hostRequestInfo Technical information of the client that is trying to
     *                        get this information.
     * @return The {@link TaxResponse}
     */
    @Processor
    public TaxResponse getTaxes(List<Map<String, Object>> documents,
                                String externalCompanyId,
                                Map<String, Object> hostRequestInfo)
    {
        return sabrixClient.getTaxes(
            (DocumentCollection) mom.unmap(documents, DocumentCollection.class),
            externalCompanyId,
            (HostRequestInfo) mom.unmap(hostRequestInfo, HostRequestInfo.class));
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }

    @PostConstruct
    public void init()
    {
        if (sabrixClient == null)
        {
            sabrixClient = new CxfSabrixClient(endpoint, username, password);
        }
    }

    public void setSabrixClient(SabrixClient sabrixClient)
    {
        this.sabrixClient = sabrixClient;
    }

}
