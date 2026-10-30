package org.allaymc.server.item.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemStackImpl} implements shared item-stack operations through an
 * injected {@link ItemBaseComponent}. Specialized subclasses add delegates for
 * their own component contracts, while this base class exposes ordinary stack
 * behavior and the same component through its generated getter.
 *
 * @author daoge_cmd
 */
public class ItemStackImpl extends ComponentClass implements ItemStack {

    /**
     * {@code baseComponent} holds the injected shared item behavior. The
     * generated getter returns this same component instance, and forwarding
     * methods dispatch stack operations through it.
     */
    @Getter
    @Delegate
    private ItemBaseComponent baseComponent;

    /**
     * Creates the item stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
