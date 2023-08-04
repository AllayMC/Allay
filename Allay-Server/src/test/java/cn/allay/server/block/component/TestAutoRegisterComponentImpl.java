package cn.allay.server.block.component;

import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public class TestAutoRegisterComponentImpl implements TestAutoRegisterComponent, BlockComponentImpl {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_auto_register_component");

    private final boolean testFlag;

    public TestAutoRegisterComponentImpl(boolean testFlag) {
        this.testFlag = testFlag;
    }

    @Override
    @Impl
    public boolean getTestFlag() {
        return testFlag;
    }
}
