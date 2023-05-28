package cn.allay.component.impl;

import cn.allay.component.annotation.Impl;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.NameComponent;
import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleNameComponent implements NameComponent, ComponentImpl {

    private static final Identifier IDENTIFIER = new Identifier("minecraft:name_component");

    protected String name;

    public SimpleNameComponent(String name) {
        this.name = name;
    }

    @Override
    @Impl
    public String getName() {
        return name;
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
