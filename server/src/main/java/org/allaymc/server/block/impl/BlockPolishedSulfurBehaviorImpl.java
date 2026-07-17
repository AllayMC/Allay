package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockPolishedSulfurBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

public class BlockPolishedSulfurBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedSulfurBehavior {
    public BlockPolishedSulfurBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
