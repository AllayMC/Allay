package org.allaymc.server.block.impl.slab;

import java.util.List;
import org.allaymc.api.block.interfaces.slab.BlockWaxedDoubleCutCopperSlabBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockWaxedDoubleCutCopperSlabBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedDoubleCutCopperSlabBehavior {
    public BlockWaxedDoubleCutCopperSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
