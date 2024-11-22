package org.allaymc.server.item.impl.boots;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.boots.ItemDiamondBootsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemDiamondBootsStackImpl extends ItemStackImpl implements ItemDiamondBootsStack {
    @Delegate
    protected ItemArmorBaseComponent armorBaseComponent;

    public ItemDiamondBootsStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
