package org.allaymc.server.block.impl.stainedglasspane;

import java.util.List;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardBlueStainedGlassPaneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockHardBlueStainedGlassPaneBehaviorImpl extends BlockBehaviorImpl implements BlockHardBlueStainedGlassPaneBehavior {
    public BlockHardBlueStainedGlassPaneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
