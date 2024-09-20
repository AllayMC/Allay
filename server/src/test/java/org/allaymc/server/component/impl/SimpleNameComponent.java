package org.allaymc.server.component.impl;

import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.interfaces.NameComponent;

/**
 * @author daoge_cmd
 */
public class SimpleNameComponent implements NameComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:name_component");

    protected String name;

    public SimpleNameComponent(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
