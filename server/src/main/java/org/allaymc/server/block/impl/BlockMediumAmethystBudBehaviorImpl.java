package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMediumAmethystBudBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMediumAmethystBudBehaviorImpl extends BlockBehaviorImpl implements BlockMediumAmethystBudBehavior {
    public BlockMediumAmethystBudBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
