package cn.allay.server.block.component;

import cn.allay.api.block.component.impl.custom.CustomBlockComponentImpl;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.identifier.Identifier;

/**
 * Allay Project 2023/6/3
 *
 * @author Cool_Loong
 */
public class TestCustomBlockComponentImpl extends CustomBlockComponentImpl {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_custom_block_component");
}
