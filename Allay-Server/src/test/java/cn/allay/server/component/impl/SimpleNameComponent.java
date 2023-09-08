package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.interfaces.Component;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.interfaces.NameComponent;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public class SimpleNameComponent implements NameComponent, Component {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:name_component");

    protected String name;

    public SimpleNameComponent(String name) {
        this.name = name;
    }

    @Override
    @Impl
    public String getName() {
        return name;
    }
}
