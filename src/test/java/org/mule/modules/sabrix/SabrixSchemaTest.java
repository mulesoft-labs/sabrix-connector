
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
