package org.allaymc.server.block.impl.stairs;

import java.util.List;
import org.allaymc.api.block.interfaces.stairs.BlockDeepslateBrickStairsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeepslateBrickStairsBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateBrickStairsBehavior {
    public BlockDeepslateBrickStairsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
