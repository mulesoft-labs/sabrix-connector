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

import com.sabrix.services.taxservice._2009_12_20.DocumentCollection;
import com.sabrix.services.taxservice._2009_12_20.HostRequestInfo;
import com.sabrix.services.taxservice._2009_12_20.TaxResponse;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

@SuppressWarnings("serial")
public class SabrixUnitTest
{
    private SabrixModule module;
    private SabrixClient client;

    @Before
    public void setUp() {
        module = new SabrixModule();
        client = mock(SabrixClient.class);
        module.setSabrixClient(client);
        module.init();
    }


    @Test
    public void getTaxesAnswersNonNullListOfResults() {

        module.getTaxes(null, "ZQ-Zuora-Dev", new HashMap<String, Object>(){{
            put("callingClient", "");
            put("callingSource", "");
            put("callingSystemNumber", "");
            put("callingUser", "");
            put("dbVersion", "");
            put("erpVersion", "");
            put("hostRequestId", "");
            put("hostRequestLoggingId", "");
            put("hostSystemNumber", "");
            put("integrationVersion", "");
            put("sdkVersion", "");
        }});

        verify(client).getTaxes(refEq(new DocumentCollection()), eq("ZQ-Zuora-Dev"), refEq(new HostRequestInfo()));
    }
}


