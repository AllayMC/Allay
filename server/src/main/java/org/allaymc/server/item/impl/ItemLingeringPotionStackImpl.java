package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.interfaces.ItemLingeringPotionStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLingeringPotionStackImpl extends ItemStackImpl implements ItemLingeringPotionStack {
    @Delegate
    private ItemProjectileComponent projectileComponent;
    @Delegate
    private ItemPotionComponent potionComponent;

    public ItemLingeringPotionStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
