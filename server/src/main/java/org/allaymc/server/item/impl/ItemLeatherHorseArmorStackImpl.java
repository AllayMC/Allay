package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemDyeableComponent;
import org.allaymc.api.item.interfaces.ItemLeatherHorseArmorStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLeatherHorseArmorStackImpl extends ItemStackImpl implements ItemLeatherHorseArmorStack {
    @Delegate
    private ItemDyeableComponent dyeableComponent;

    public ItemLeatherHorseArmorStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
