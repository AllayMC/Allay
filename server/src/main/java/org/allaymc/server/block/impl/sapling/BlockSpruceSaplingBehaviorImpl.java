package org.allaymc.server.block.impl.sapling;

import java.util.List;
import org.allaymc.api.block.interfaces.sapling.BlockSpruceSaplingBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSpruceSaplingBehaviorImpl extends BlockBehaviorImpl implements BlockSpruceSaplingBehavior {
    public BlockSpruceSaplingBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
