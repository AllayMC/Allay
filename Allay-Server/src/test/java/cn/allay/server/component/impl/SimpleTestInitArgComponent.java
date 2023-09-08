package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.interfaces.Component;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.interfaces.TestInitArgComponent;

/**
 * Allay Project 2023/4/21
 *
 * @author daoge_cmd
 */
public class SimpleTestInitArgComponent implements TestInitArgComponent, Component {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_init_arg_component");

    protected final int data;

    public SimpleTestInitArgComponent(int data) {
        this.data = data;
    }

    @Impl
    @Override
    public int getData() {
        return data;
    }
}
