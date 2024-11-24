package org.allaymc.server.block.impl.leaves;

import org.allaymc.api.block.interfaces.leaves.*;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLeavesBehaviorImpl extends BlockBehaviorImpl implements
        BlockAcaciaLeavesBehavior, BlockAzaleaLeavesBehavior, BlockBirchLeavesBehavior, BlockCherryLeavesBehavior, BlockDarkOakLeavesBehavior,
        BlockJungleLeavesBehavior, BlockMangroveLeavesBehavior, BlockOakLeavesBehavior, BlockSpruceLeavesBehavior {
    public BlockLeavesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
