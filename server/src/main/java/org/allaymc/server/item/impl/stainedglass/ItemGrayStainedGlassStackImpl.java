package org.allaymc.server.item.impl.stainedglass;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.stainedglass.ItemGrayStainedGlassStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemGrayStainedGlassStackImpl extends ItemStackImpl implements ItemGrayStainedGlassStack {
    public ItemGrayStainedGlassStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
