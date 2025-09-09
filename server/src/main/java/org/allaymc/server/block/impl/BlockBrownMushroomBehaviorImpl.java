package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBrownMushroomBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBrownMushroomBehaviorImpl extends BlockBehaviorImpl implements BlockBrownMushroomBehavior {
    public BlockBrownMushroomBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
