package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.interfaces.ItemExperienceBottleStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemExperienceBottleStackImpl extends ItemStackImpl implements ItemExperienceBottleStack {
    @Delegate
    private ItemProjectileComponent projectileComponent;

    public ItemExperienceBottleStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
