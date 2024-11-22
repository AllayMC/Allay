package org.allaymc.server.item.impl.stainedglasspane;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemLightGrayStainedGlassPaneStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemLightGrayStainedGlassPaneStackImpl extends ItemStackImpl implements ItemLightGrayStainedGlassPaneStack {
    public ItemLightGrayStainedGlassPaneStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
