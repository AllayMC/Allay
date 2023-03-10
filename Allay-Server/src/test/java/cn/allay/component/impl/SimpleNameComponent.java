package cn.allay.component.impl;

import cn.allay.component.annotation.Impl;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.NameComponent;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleNameComponent implements NameComponent, ComponentImpl {

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
    public String getNamespaceId() {
        return "minecraft:name_component";
    }
}
