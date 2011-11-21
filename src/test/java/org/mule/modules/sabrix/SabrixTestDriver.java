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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration test for Sabrix tax service
 *
 * @author flbulgarelli
 */
@SuppressWarnings("serial")
public class SabrixTestDriver
{
    private SabrixModule module;

    @Before
    public void setUp() {
        module = new SabrixModule();
        module.setPassword(System.getenv("sabrixPassword"));
        module.setUsername(System.getenv("sabrixUsername"));
        module.setEndpoint("https://test.mts.sabrix.com/mts-te/TaxService/2009-12-20");
        module.init();
    }

    @Test
    public void getTaxesAnswersNonNullListOfResults() {
        module.getTaxes(Collections.<Map<String,Object>>singletonList(new HashMap<String, Object>(){{

        }}), "ZQ-Zuora-Dev", new HashMap<String, Object>(){{
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
    }
}







