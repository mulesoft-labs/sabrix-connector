/**
 * Mule Workday Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.sabrix.internal;

import ar.com.zauber.commons.mom.MapObjectMapperInterceptor;

import java.util.HashMap;
import java.util.List;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;

import org.apache.commons.lang.StringUtils;

public class CollectionInliner implements MapObjectMapperInterceptor
{
    @Override
    @SuppressWarnings({"unchecked", "serial"})
    public Object unmap(final Object value, final Class<?> type, Applicable2<Object, Class<?>, Object> proceed)
    {
        if (value instanceof List && isCollection(type)) {
            return proceed.apply(new HashMap() {{
                    put(propertyName(type), value);
            }}, type);
        }
        return proceed.apply(value, type);
    }

    private boolean isCollection(final Class<?> type)
    {
        return type.getSimpleName().endsWith("Collection");
    }

    @Override
    public Object unamp(Object map, Applicable<Object, Object> proceed)
    {
        return proceed.apply(map);
    }

    @Override
    public Object map(Object value, Applicable<Object, Object> proceed)
    {
        return proceed.apply(value);
    }

    protected String propertyName(final Class<?> type)
    {
        return StringUtils.uncapitalize(type.getSimpleName().replace("Collection", ""));
    }
}
