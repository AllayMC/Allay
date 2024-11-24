package org.allaymc.server.block.impl.coralfan;

import java.util.List;
import org.allaymc.api.block.interfaces.coralfan.BlockTubeCoralFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockTubeCoralFanBehaviorImpl extends BlockBehaviorImpl implements BlockTubeCoralFanBehavior {
    public BlockTubeCoralFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
