package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.interfaces.ItemHelmetStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemHelmetStackImpl} represents a server item stack with delegated
 * wearable slot selection, armor trim access and repair material checks. {@code
 * ItemStackImpl} supplies the shared stack state and item operations, while the
 * local delegates expose their component contracts through the same typed stack
 * object.
 */
public class ItemHelmetStackImpl extends ItemStackImpl implements ItemHelmetStack {
    // armorBaseComponent, trimmableComponent and repairableComponent receive
    // components created for this stack. ComponentClass populates these fields
    // during construction and rejects missing or ambiguous provider results
    // before the stack becomes usable.
    /**
     * Wearable armor slot delegate.
     */
    @Delegate
    private ItemWearableComponent armorBaseComponent;
    /**
     * Armor trim data delegate.
     */
    @Delegate
    private ItemTrimmableComponent trimmableComponent;
    /**
     * Repair material compatibility delegate.
     */
    @Delegate
    private ItemRepairableComponent repairableComponent;

    /**
     * Creates the typed stack using the supplied initialization data and component
     * providers. {@code ComponentClass} requires exactly one compatible provider
     * result for each delegate type, including the inherited base component;
     * missing or ambiguous matches fail construction.
     */
    public ItemHelmetStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
