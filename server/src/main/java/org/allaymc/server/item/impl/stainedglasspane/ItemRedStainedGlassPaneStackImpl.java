package org.allaymc.server.item.impl.stainedglasspane;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemRedStainedGlassPaneStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemRedStainedGlassPaneStackImpl extends ItemStackImpl implements ItemRedStainedGlassPaneStack {
    public ItemRedStainedGlassPaneStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
