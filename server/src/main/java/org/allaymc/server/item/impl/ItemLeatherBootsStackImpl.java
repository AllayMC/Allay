package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemDyeableComponent;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.interfaces.ItemLeatherBootsStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemLeatherBootsStackImpl} represents a server item stack with
 * delegated wearable slot selection, armor trim access, repair material checks
 * and custom color access. {@code ItemStackImpl} supplies the shared stack state
 * and item operations, while the local delegates expose their component
 * contracts through the same typed stack object.
 */
public class ItemLeatherBootsStackImpl extends ItemStackImpl implements ItemLeatherBootsStack {
    // wearableComponent, trimmableComponent, repairableComponent and
    // dyeableComponent receive components created for this stack.
    // ComponentClass populates these fields during construction and rejects
    // missing or ambiguous provider results before the stack becomes usable.
    /**
     * Wearable armor slot delegate.
     */
    @Delegate
    private ItemWearableComponent wearableComponent;
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
     * Custom dye color delegate.
     */
    @Delegate
    private ItemDyeableComponent dyeableComponent;

    /**
     * Creates the typed stack using the supplied initialization data and component
     * providers. {@code ComponentClass} requires exactly one compatible provider
     * result for each delegate type, including the inherited base component;
     * missing or ambiguous matches fail construction.
     */
    public ItemLeatherBootsStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
