package org.allaymc.server.item.impl.pickaxe;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.pickaxe.ItemNetheritePickaxeStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemNetheritePickaxeStackImpl extends ItemStackImpl implements ItemNetheritePickaxeStack {
    public ItemNetheritePickaxeStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
