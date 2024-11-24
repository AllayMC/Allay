package org.allaymc.server.block.impl.coralfan;

import java.util.List;
import org.allaymc.api.block.interfaces.coralfan.BlockDeadBrainCoralFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeadBrainCoralFanBehaviorImpl extends BlockBehaviorImpl implements BlockDeadBrainCoralFanBehavior {
    public BlockDeadBrainCoralFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
