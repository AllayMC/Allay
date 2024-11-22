package org.allaymc.server.item.impl.torch;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.torch.ItemUnlitRedstoneTorchStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemUnlitRedstoneTorchStackImpl extends ItemStackImpl implements ItemUnlitRedstoneTorchStack {
    public ItemUnlitRedstoneTorchStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
