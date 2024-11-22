package org.allaymc.server.item.impl.concretepowder;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.concretepowder.ItemLightBlueConcretePowderStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemLightBlueConcretePowderStackImpl extends ItemStackImpl implements ItemLightBlueConcretePowderStack {
    public ItemLightBlueConcretePowderStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
