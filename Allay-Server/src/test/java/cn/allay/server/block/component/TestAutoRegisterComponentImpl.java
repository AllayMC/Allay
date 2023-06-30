package cn.allay.server.block.component;

import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public class TestAutoRegisterComponentImpl implements TestAutoRegisterComponent, BlockComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:test_auto_register_component");

    private final boolean testFlag;

    public TestAutoRegisterComponentImpl(boolean testFlag) {
        this.testFlag = testFlag;
    }

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    @Impl
    public boolean getTestFlag() {
        return testFlag;
    }
}
