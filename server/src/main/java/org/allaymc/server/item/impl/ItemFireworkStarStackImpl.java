package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFireworkStarBaseComponent;
import org.allaymc.api.item.interfaces.ItemFireworkStarStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemFireworkStarStackImpl} implements the firework star item API
 * through supplied component delegates. {@link ItemStackImpl} supplies
 * ordinary stack behavior, while {@code fireworkStarBaseComponent} handles the
 * stored firework explosion definition. Callers access these contracts through
 * this stack instance.
 */
public class ItemFireworkStarStackImpl extends ItemStackImpl implements ItemFireworkStarStack {

    /**
     * {@code fireworkStarBaseComponent} holds the injected
     * {@link ItemFireworkStarBaseComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemFireworkStarBaseComponent fireworkStarBaseComponent;

    /**
     * Creates the firework star stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemFireworkStarStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
