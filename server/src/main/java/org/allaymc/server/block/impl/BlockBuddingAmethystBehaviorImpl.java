package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBuddingAmethystBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBuddingAmethystBehaviorImpl extends BlockBehaviorImpl implements BlockBuddingAmethystBehavior {
    public BlockBuddingAmethystBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
