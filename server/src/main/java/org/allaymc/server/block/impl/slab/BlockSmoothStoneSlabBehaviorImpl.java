package org.allaymc.server.block.impl.slab;

import java.util.List;
import org.allaymc.api.block.interfaces.slab.BlockSmoothStoneSlabBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSmoothStoneSlabBehaviorImpl extends BlockBehaviorImpl implements BlockSmoothStoneSlabBehavior {
    public BlockSmoothStoneSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
