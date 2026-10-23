package org.allaymc.server.pdc;

import lombok.AllArgsConstructor;
import org.allaymc.api.pdc.PersistentDataAdapterContext;
import org.allaymc.api.pdc.PersistentDataType;

/**
 * {@code PrimitiveDataType} supplies identity conversions for primitive-adapter tests, including
 * class tokens that deliberately have no registered adapter.
 *
 * @author IWareQ
 */
@AllArgsConstructor
public class PrimitiveDataType<P> implements PersistentDataType<P, P> {
    /**
     * Requested primitive adapter class.
     */
    private final Class<P> primitiveType;

    /**
     * Returns the retained primitive class requested by the test case.
     */
    @Override
    public Class<P> getPrimitiveType() {
        return primitiveType;
    }

    /**
     * Returns the same class used for this identity converter's primitive values.
     */
    @Override
    public Class<P> getComplexType() {
        return primitiveType;
    }

    /**
     * Returns the supplied application value unchanged for registry validation tests.
     */
    @Override
    public P toPrimitive(P complex, PersistentDataAdapterContext context) {
        return complex;
    }

    /**
     * Returns the supplied primitive value unchanged for identity conversion tests.
     */
    @Override
    public P fromPrimitive(P primitive, PersistentDataAdapterContext context) {
        return primitive;
    }
}
