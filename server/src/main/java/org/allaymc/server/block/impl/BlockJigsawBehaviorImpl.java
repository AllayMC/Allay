package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockJigsawBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockJigsawBehaviorImpl extends BlockBehaviorImpl implements BlockJigsawBehavior {
    public BlockJigsawBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
