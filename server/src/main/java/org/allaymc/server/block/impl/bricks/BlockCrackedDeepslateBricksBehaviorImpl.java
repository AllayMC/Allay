package org.allaymc.server.block.impl.bricks;

import java.util.List;
import org.allaymc.api.block.interfaces.bricks.BlockCrackedDeepslateBricksBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCrackedDeepslateBricksBehaviorImpl extends BlockBehaviorImpl implements BlockCrackedDeepslateBricksBehavior {
    public BlockCrackedDeepslateBricksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
