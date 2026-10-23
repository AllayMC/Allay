package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.interfaces.ItemPumpkinPieStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemPumpkinPieStackImpl} implements the pumpkin pie item API through
 * supplied component delegates. {@link ItemStackImpl} supplies ordinary stack
 * behavior, while {@code edibleComponent} exposes eating eligibility, eating
 * time, nutrition values, drink behavior, and consumption callbacks. Callers
 * access these contracts through this stack instance.
 */
public class ItemPumpkinPieStackImpl extends ItemStackImpl implements ItemPumpkinPieStack {
    /**
     * {@code edibleComponent} holds the injected {@link ItemEdibleComponent}
     * implementation. Lombok-generated forwarding methods use this instance
     * after superclass component initialization completes.
     */
    @Delegate
    private ItemEdibleComponent edibleComponent;

    /**
     * Creates the pumpkin pie stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemPumpkinPieStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
