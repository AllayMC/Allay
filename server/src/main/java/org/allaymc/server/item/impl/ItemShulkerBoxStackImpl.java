package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemShulkerBoxBaseComponent;
import org.allaymc.api.item.interfaces.ItemShulkerBoxStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemShulkerBoxStackImpl} implements the shulker box item API through
 * supplied component delegates. {@link ItemStackImpl} supplies ordinary stack
 * behavior, while {@code shulkerBoxBaseComponent} exposes the inventory items
 * stored by their slot indices. Callers access these contracts through this
 * stack instance.
 */
public class ItemShulkerBoxStackImpl extends ItemStackImpl implements ItemShulkerBoxStack {
    /**
     * {@code shulkerBoxBaseComponent} holds the injected
     * {@link ItemShulkerBoxBaseComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemShulkerBoxBaseComponent shulkerBoxBaseComponent;

    /**
     * Creates the shulker box stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemShulkerBoxStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
