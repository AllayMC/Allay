package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.interfaces.ItemBakedPotatoStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemBakedPotatoStackImpl} implements the baked potato item API
 * through supplied component delegates. {@link ItemStackImpl} supplies
 * ordinary stack behavior, while {@code edibleComponent} handles consumption
 * timing, nutrition, drink behavior, and eating eligibility.
 * Callers access these contracts through this stack instance.
 */
public class ItemBakedPotatoStackImpl extends ItemStackImpl implements ItemBakedPotatoStack {
    /**
     * {@code edibleComponent} holds the injected {@link ItemEdibleComponent}
     * implementation. Lombok-generated forwarding methods use this instance
     * after superclass component initialization completes.
     */
    @Delegate
    private ItemEdibleComponent edibleComponent;

    /**
     * Creates the baked potato stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemBakedPotatoStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
