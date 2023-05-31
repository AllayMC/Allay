package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.interfaces.ComponentImpl;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.interfaces.TestInitArgComponent;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/21 <br>
 * Allay Project <br>
 */
public class SimpleTestInitArgComponent implements TestInitArgComponent, ComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:test_init_arg_component");

    protected final int data;

    public SimpleTestInitArgComponent(int data) {
        this.data = data;
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }

    @Impl
    @Override
    public int getData() {
        return data;
    }
}
