package org.allaymc.server.block.impl.stairs;

import java.util.List;
import org.allaymc.api.block.interfaces.stairs.BlockBirchStairsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBirchStairsBehaviorImpl extends BlockBehaviorImpl implements BlockBirchStairsBehavior {
    public BlockBirchStairsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
