package org.allaymc.server.item.impl.wood;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.wood.ItemStrippedBirchWoodStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemStrippedBirchWoodStackImpl extends ItemStackImpl implements ItemStrippedBirchWoodStack {
    public ItemStrippedBirchWoodStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
