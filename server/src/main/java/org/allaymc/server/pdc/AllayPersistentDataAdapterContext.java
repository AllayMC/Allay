package org.allaymc.server.pdc;

import lombok.RequiredArgsConstructor;
import org.allaymc.api.pdc.PersistentDataAdapterContext;
import org.allaymc.api.pdc.PersistentDataContainer;

/**
 * @author IWareQ
 */
@RequiredArgsConstructor
public class AllayPersistentDataAdapterContext implements PersistentDataAdapterContext {
    private final PersistentDataTypeRegistry registry;

    @Override
    public PersistentDataContainer newPersistentDataContainer() {
        return new AllayPersistentDataContainer(this.registry);
    }
}
