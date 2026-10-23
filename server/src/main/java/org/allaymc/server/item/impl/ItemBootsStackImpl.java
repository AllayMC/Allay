package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.interfaces.ItemBootsStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemBootsStackImpl} represents a server item stack with delegated
 * wearable slot selection and armor trim access. {@code ItemStackImpl} supplies
 * the shared stack state and item operations, while the local delegates expose
 * their component contracts through the same typed stack object.
 */
public class ItemBootsStackImpl extends ItemStackImpl implements ItemBootsStack {

    // wearableComponent and trimComponent receive components created for this
    // stack. ComponentClass populates both fields during construction before
    // delegated use and rejects missing or ambiguous compatible provider results.
    /**
     * Wearable armor slot delegate.
     */
    @Delegate
    private ItemWearableComponent wearableComponent;
    /**
     * Armor trim data delegate.
     */
    @Delegate
    private ItemTrimmableComponent trimComponent;

    /**
     * Creates the typed stack using the supplied initialization data and component
     * providers. {@code ComponentClass} requires exactly one compatible provider
     * result for each delegate type, including the inherited base component;
     * missing or ambiguous matches fail construction.
     */
    public ItemBootsStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
