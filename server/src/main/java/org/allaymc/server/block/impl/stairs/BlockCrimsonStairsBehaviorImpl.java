package org.allaymc.server.block.impl.stairs;

import java.util.List;
import org.allaymc.api.block.interfaces.stairs.BlockCrimsonStairsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCrimsonStairsBehaviorImpl extends BlockBehaviorImpl implements BlockCrimsonStairsBehavior {
    public BlockCrimsonStairsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
