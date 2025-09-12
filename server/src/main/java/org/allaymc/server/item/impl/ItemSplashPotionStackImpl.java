package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSplashPotionStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSplashPotionStackImpl extends ItemStackImpl implements ItemSplashPotionStack {
    @Delegate
    protected ItemProjectileComponent projectileComponent;
    @Delegate
    protected ItemPotionComponent potionComponent;

    public ItemSplashPotionStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
