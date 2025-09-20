package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockOchreFroglightBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockOchreFroglightBehaviorImpl extends BlockBehaviorImpl implements BlockOchreFroglightBehavior {
    public BlockOchreFroglightBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
