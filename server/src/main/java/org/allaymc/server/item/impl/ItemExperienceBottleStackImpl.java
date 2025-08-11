package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemExperienceBottleStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemExperienceBottleStackImpl extends ItemStackImpl implements ItemExperienceBottleStack {
    @Delegate
    protected ItemProjectileComponent projectileComponent;

    public ItemExperienceBottleStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
