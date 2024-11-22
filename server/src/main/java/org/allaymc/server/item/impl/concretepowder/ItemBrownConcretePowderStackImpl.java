package org.allaymc.server.item.impl.concretepowder;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.concretepowder.ItemBrownConcretePowderStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemBrownConcretePowderStackImpl extends ItemStackImpl implements ItemBrownConcretePowderStack {
    public ItemBrownConcretePowderStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
