/**
 * Mule Workday Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


package org.mule.modules.sabrix;

import org.mule.tck.FunctionalTestCase;

import org.junit.Test;

public class SabrixSchemaTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "schema-test.xml";
    }

    @Test
    public void testCanParseXmlWithoutSchemaValidationErrors() throws Exception
    {
        // Nothing. Will fail if can not parse
    }
}
