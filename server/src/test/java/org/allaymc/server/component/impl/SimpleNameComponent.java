package org.allaymc.server.component.impl;

import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.interfaces.NameComponent;

/**
 * @author daoge_cmd
 */
public class SimpleNameComponent implements NameComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:name_component");

    protected String name;

    public SimpleNameComponent(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
