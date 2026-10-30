package org.allaymc.server.pdc;

import lombok.RequiredArgsConstructor;
import org.allaymc.api.pdc.PersistentDataAdapterContext;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.pdc.PersistentDataTypeRegistry;

/**
 * {@code AllayPersistentDataAdapterContext} creates empty persistent containers backed by the
 * same type registry as their owning conversion. Its generated constructor retains the registry
 * reference, so nested containers use compatible adapters without copying or rebuilding
 * registry registrations.
 *
 * @author IWareQ
 */
@RequiredArgsConstructor
public class AllayPersistentDataAdapterContext implements PersistentDataAdapterContext {
    /**
     * Retained nested-container adapter registry.
     */
    private final PersistentDataTypeRegistry registry;

    /**
     * Creates an empty mutable container using the retained registry reference.
     */
    @Override
    public PersistentDataContainer newPersistentDataContainer() {
        return new AllayPersistentDataContainer(this.registry);
    }
}
