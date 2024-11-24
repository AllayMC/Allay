package org.allaymc.server.block.impl.wool;

import java.util.List;
import org.allaymc.api.block.interfaces.wool.BlockPurpleWoolBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPurpleWoolBehaviorImpl extends BlockBehaviorImpl implements BlockPurpleWoolBehavior {
    public BlockPurpleWoolBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
