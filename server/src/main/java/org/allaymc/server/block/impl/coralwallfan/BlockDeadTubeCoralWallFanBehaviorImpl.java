package org.allaymc.server.block.impl.coralwallfan;

import java.util.List;
import org.allaymc.api.block.interfaces.coralwallfan.BlockDeadTubeCoralWallFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeadTubeCoralWallFanBehaviorImpl extends BlockBehaviorImpl implements BlockDeadTubeCoralWallFanBehavior {
    public BlockDeadTubeCoralWallFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
