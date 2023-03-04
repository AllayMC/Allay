package cn.allay.component.impl;

import cn.allay.component.interfaces.NameComponent;
import cn.allay.component.annotation.ComponentImpl;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleNameComponent implements NameComponent {

    protected String name;

    public SimpleNameComponent(String name) {
        this.name = name;
    }

    @Override
    @ComponentImpl
    public String getName() {
        return name;
    }
}
