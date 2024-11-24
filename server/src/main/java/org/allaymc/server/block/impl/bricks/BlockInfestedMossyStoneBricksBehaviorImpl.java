package org.allaymc.server.block.impl.bricks;

import java.util.List;
import org.allaymc.api.block.interfaces.bricks.BlockInfestedMossyStoneBricksBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockInfestedMossyStoneBricksBehaviorImpl extends BlockBehaviorImpl implements BlockInfestedMossyStoneBricksBehavior {
    public BlockInfestedMossyStoneBricksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
