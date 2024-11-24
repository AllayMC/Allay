package org.allaymc.server.block.impl.planks;

import java.util.List;
import org.allaymc.api.block.interfaces.planks.BlockCrimsonPlanksBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCrimsonPlanksBehaviorImpl extends BlockBehaviorImpl implements BlockCrimsonPlanksBehavior {
    public BlockCrimsonPlanksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
