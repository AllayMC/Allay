package org.allaymc.server.block.impl.coralblock;

import java.util.List;
import org.allaymc.api.block.interfaces.coralblock.BlockTubeCoralBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockTubeCoralBlockBehaviorImpl extends BlockBehaviorImpl implements BlockTubeCoralBlockBehavior {
    public BlockTubeCoralBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
