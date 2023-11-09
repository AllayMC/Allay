package org.allaymc.server.component.impl;

import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.server.component.interfaces.NameComponent;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public class SimpleNameComponent implements NameComponent {

    @ComponentIdentifier
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
