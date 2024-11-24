package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.helmet.*;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemHelmetStackImpl extends ItemStackImpl implements ItemChainmailHelmetStack, ItemDiamondHelmetStack, ItemGoldenHelmetStack, ItemIronHelmetStack, ItemLeatherHelmetStack, ItemNetheriteHelmetStack, ItemTurtleHelmetStack {
    @Delegate
    protected ItemArmorBaseComponent armorBaseComponent;

    public ItemHelmetStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
