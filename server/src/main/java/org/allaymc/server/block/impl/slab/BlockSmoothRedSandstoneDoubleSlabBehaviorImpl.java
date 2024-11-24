package org.allaymc.server.block.impl.slab;

import java.util.List;
import org.allaymc.api.block.interfaces.slab.BlockSmoothRedSandstoneDoubleSlabBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSmoothRedSandstoneDoubleSlabBehaviorImpl extends BlockBehaviorImpl implements BlockSmoothRedSandstoneDoubleSlabBehavior {
    public BlockSmoothRedSandstoneDoubleSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
