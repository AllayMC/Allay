package org.allaymc.server.block.component;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.server.block.component.common.CustomBlockComponentImpl;

/**
 * Allay Project 2023/6/3
 *
 * @author Cool_Loong
 */
public class TestCustomBlockComponentImpl extends CustomBlockComponentImpl {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_custom_block_component");
}
