package org.allaymc.server.block.component;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code TestComponentImpl} supplies the marker capability used by block component
 * construction tests. Its identifier lets a provider expose this fixture as an
 * independently registered block component alongside the block's ordinary behavior
 * components.
 *
 * @author daoge_cmd
 */
public class TestComponentImpl implements TestComponent, BlockComponent {
    /**
     * {@code IDENTIFIER} names this test component so provider registration can
     * distinguish it from the ordinary block behavior components.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_component");
}
