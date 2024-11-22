package org.allaymc.server.item.impl.egg;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.egg.ItemWardenSpawnEggStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemWardenSpawnEggStackImpl extends ItemStackImpl implements ItemWardenSpawnEggStack {
    public ItemWardenSpawnEggStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
