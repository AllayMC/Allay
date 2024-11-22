package org.allaymc.server.item.impl.stainedglasspane;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemPurpleStainedGlassPaneStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemPurpleStainedGlassPaneStackImpl extends ItemStackImpl implements ItemPurpleStainedGlassPaneStack {
    public ItemPurpleStainedGlassPaneStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
