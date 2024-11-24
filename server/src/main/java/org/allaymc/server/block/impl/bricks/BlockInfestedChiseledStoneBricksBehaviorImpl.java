package org.allaymc.server.block.impl.bricks;

import java.util.List;
import org.allaymc.api.block.interfaces.bricks.BlockInfestedChiseledStoneBricksBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockInfestedChiseledStoneBricksBehaviorImpl extends BlockBehaviorImpl implements BlockInfestedChiseledStoneBricksBehavior {
    public BlockInfestedChiseledStoneBricksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
