package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.interfaces.ItemFishingRodStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemFishingRodStackImpl} implements the fishing rod item API through
 * supplied component delegates. {@link ItemStackImpl} supplies ordinary stack
 * behavior, while {@code repairableComponent} handles selection of repair
 * ingredients. Callers access these contracts through this stack instance.
 */
public class ItemFishingRodStackImpl extends ItemStackImpl implements ItemFishingRodStack {

    /**
     * {@code repairableComponent} holds the injected
     * {@link ItemRepairableComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemRepairableComponent repairableComponent;

    /**
     * Creates the fishing rod stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemFishingRodStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
