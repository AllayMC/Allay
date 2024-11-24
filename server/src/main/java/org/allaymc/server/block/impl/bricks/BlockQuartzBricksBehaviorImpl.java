package org.allaymc.server.block.impl.bricks;

import java.util.List;
import org.allaymc.api.block.interfaces.bricks.BlockQuartzBricksBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockQuartzBricksBehaviorImpl extends BlockBehaviorImpl implements BlockQuartzBricksBehavior {
    public BlockQuartzBricksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
