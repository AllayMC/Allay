package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.pickaxe.*;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemPickaxeStackImpl extends ItemStackImpl implements ItemDiamondPickaxeStack, ItemGoldenPickaxeStack, ItemIronPickaxeStack, ItemNetheritePickaxeStack, ItemStonePickaxeStack, ItemWoodenPickaxeStack {
    public ItemPickaxeStackImpl(ItemStackInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
