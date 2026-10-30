package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.component.BlockLecternBaseComponent;
import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityLectern;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockLecternBehaviorImpl} implements lectern block behavior through
 * components supplied by its caller. Its constructor delegates initialization to
 * {@code BlockBehaviorImpl}, and Lombok-generated methods forward the specialized
 * {@code BlockLecternBehavior} contracts to the injected component fields.
 */
public class BlockLecternBehaviorImpl extends BlockBehaviorImpl implements BlockLecternBehavior {

    // Superclass construction injects blockEntityHolderComponent and
    // lecternBaseComponent from the supplied providers. Each field must match
    // exactly one compatible component before the generated delegation methods can
    // use it.
    /**
     * Typed lectern entity access delegate.
     */
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityLectern> blockEntityHolderComponent;

    /**
     * Lectern page-change handling delegate.
     */
    @Delegate
    private BlockLecternBaseComponent lecternBaseComponent;

    /**
     * Creates lectern behavior from the supplied component providers.
     * {@code componentProviders} must supply exactly one compatible component for
     * every delegate field, including inherited fields. Missing or ambiguous
     * matches abort construction without rolling back initialization side effects
     * already performed.
     */
    public BlockLecternBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
