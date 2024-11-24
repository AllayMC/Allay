package org.allaymc.server.block.impl.bricks;

import java.util.List;
import org.allaymc.api.block.interfaces.bricks.BlockChiseledNetherBricksBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockChiseledNetherBricksBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledNetherBricksBehavior {
    public BlockChiseledNetherBricksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
