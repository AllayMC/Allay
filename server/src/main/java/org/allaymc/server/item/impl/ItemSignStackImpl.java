package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemSignBaseComponent;
import org.allaymc.api.item.interfaces.ItemSignStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemSignStackImpl} implements the sign item API through supplied
 * component delegates. {@link ItemStackImpl} supplies ordinary stack behavior,
 * while {@code signBaseComponent} exposes the standing and wall sign block
 * variants used for placement. Callers access these contracts through this
 * stack instance.
 */
public class ItemSignStackImpl extends ItemStackImpl implements ItemSignStack {

    /**
     * {@code signBaseComponent} holds the injected
     * {@link ItemSignBaseComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemSignBaseComponent signBaseComponent;

    /**
     * Creates the sign stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemSignStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
