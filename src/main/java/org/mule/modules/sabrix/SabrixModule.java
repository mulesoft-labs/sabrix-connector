/**
 * Mule Workday Cloud Connector
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
import org.mule.modules.sabrix.internal.CollectionInliner;

import ar.com.zauber.commons.mom.MapObjectMapper;
import ar.com.zauber.commons.mom.MapObjectMappers;
import ar.com.zauber.commons.mom.style.impl.CXFStyle;

import com.sabrix.services.taxservice._2009_12_20.DocumentCollection;
import com.sabrix.services.taxservice._2009_12_20.HostRequestInfo;
import com.sabrix.services.taxservice._2009_12_20.TaxResponse;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.Validate;

/**
 * SabrixModule
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
     * @param documents TODO
     * @param externalCompanyId TODO
     * @param hostRequestInfo TODO
     * @return TODO
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
    public void init() {
        if(sabrixClient == null) {
            Validate.notNull(password);
            Validate.notNull(username);
            Validate.notNull(endpoint);
            sabrixClient = new CxfSabrixClient(endpoint, username, password);
        }
    }

    public void setSabrixClient(SabrixClient sabrixClient)
    {
        this.sabrixClient = sabrixClient;
    }

}
