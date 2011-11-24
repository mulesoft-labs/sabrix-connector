/**
 * Mule Sabrix Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.sabrix;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.sabrix.services.taxservice._2009_12_20.DocumentCollection;
import com.sabrix.services.taxservice._2009_12_20.HostRequestInfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings({"serial", "unchecked" })
public class SabrixUnitTest
{
    private SabrixModule module;
    private SabrixClient client;

    @Before
    public void setUp()
    {
        module = new SabrixModule();
        client = mock(SabrixClient.class);
        module.setSabrixClient(client);
        module.init();
    }

    @Test
    public void getTaxesAnswersNonNullListOfResults()
    {

        module.getTaxes(Arrays.<Map<String, Object>> asList(),
            "ZQ-Zuora-Dev",
            new HashMap<String, Object>() { {
                put("callingClient", "Foo");
                put("callingSource", "");
                put("callingSystemNumber", "");
                put("callingUser", "");
                put("dbVersion", "");
                put("erpVersion", 15);
            } } );

        verify(client).getTaxes(refEq(new DocumentCollection() { {
            getDocument();
        } } ), eq("ZQ-Zuora-Dev"), refEq(new HostRequestInfo() { {
            this.callingClient = "Foo";
            this.callingSource = "";
            this.callingSystemNumber = "";
            this.callingUser = "";
            this.dbVersion = "";
            this.erpVersion = "15";
        } } ));
    }
}


