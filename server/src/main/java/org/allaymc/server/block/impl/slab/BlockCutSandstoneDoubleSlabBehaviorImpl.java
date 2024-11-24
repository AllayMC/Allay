package org.allaymc.server.block.impl.slab;

import java.util.List;
import org.allaymc.api.block.interfaces.slab.BlockCutSandstoneDoubleSlabBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCutSandstoneDoubleSlabBehaviorImpl extends BlockBehaviorImpl implements BlockCutSandstoneDoubleSlabBehavior {
    public BlockCutSandstoneDoubleSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
