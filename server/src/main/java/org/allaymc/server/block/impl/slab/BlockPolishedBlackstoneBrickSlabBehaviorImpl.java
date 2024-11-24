package org.allaymc.server.block.impl.slab;

import java.util.List;
import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneBrickSlabBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPolishedBlackstoneBrickSlabBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedBlackstoneBrickSlabBehavior {
    public BlockPolishedBlackstoneBrickSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
