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

import com.sabrix.services.taxservice._2009_12_20.AddressCollection;
import com.sabrix.services.taxservice._2009_12_20.SuccessType;
import com.sabrix.services.taxservice._2009_12_20.TaxResponse;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
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
    public void setUp()
    {
        module = new SabrixModule();
        module.setPassword(System.getenv("sabrixPassword"));
        module.setUsername(System.getenv("sabrixUsername"));
        module.setEndpoint("https://test.mts.sabrix.com/mts-te/TaxService/2009-12-20");
        module.init();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getTaxesAnswersNonNullListOfResults()
    {
        TaxResponse taxResponse = module.getTaxes(
            Collections.<Map<String, Object>> singletonList(new HashMap<String, Object>() { {
            put("documentNumber", "1");
            put("addresses", new AddressCollection());
            put("attributes", new HashMap() { {
                put("isAuditResult", false);
                put("isCredit", false);
                put("isExempt", false);
                put("exemptReason", "XX");
                put("amountType", "TAX_AMOUNT_BASED");
                put("companyRole", "BUYER");
                put("currencyCode", "USD");
                put("documentType", "foo");
            } });

            put("vendorTax", 100);
            put("lines", Arrays.asList(new HashMap() { {
                put("quantity", new HashMap() { {
                    put("UOM", "g");
                    put("amount", 10);
                } });
                put("lineNumber", BigInteger.TEN);
                put("amounts", new HashMap() { {
                    put("grossAmount", 1);
                    put("taxAmount", 10);
                } });
            } }));
            put("dates", new HashMap() { {
                put("documentDate", new Date());
                put("fiscalDate", new Date());
            } });

        } }), "ZQ-Zuora-Dev", new HashMap<String, Object>() { {
            put("callingClient", "Mule");
            put("callingSource", "Source");
            put("callingSystemNumber", "459");
            put("callingUser", "Mule");
            put("dbVersion", "1");
            put("erpVersion", "1");
            put("hostRequestId", "1");
            put("hostRequestLoggingId", "2");
            put("hostSystemNumber", "1");
            put("integrationVersion", "1");
            put("sdkVersion", "1");
        } });
        assertTrue(taxResponse.getRequestStatus().getSuccess() == SuccessType.SUCCESS);
    }
}
