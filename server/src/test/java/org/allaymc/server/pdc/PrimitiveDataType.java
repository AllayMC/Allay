package org.allaymc.server.pdc;

import lombok.AllArgsConstructor;
import org.allaymc.api.pdc.PersistentDataAdapterContext;
import org.allaymc.api.pdc.PersistentDataType;

/**
 * @author IWareQ
 */
@AllArgsConstructor
public class PrimitiveDataType<P> implements PersistentDataType<P, P> {
    private final Class<P> primitiveType;

    @Override
    public Class<P> getPrimitiveType() {
        return primitiveType;
    }

    @Override
    public Class<P> getComplexType() {
        return primitiveType;
    }

    @Override
    public P toPrimitive(P complex, PersistentDataAdapterContext context) {
        return complex;
    }

    @Override
    public P fromPrimitive(P primitive, PersistentDataAdapterContext context) {
        return primitive;
    }
}
