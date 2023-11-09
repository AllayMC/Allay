package org.allaymc.server.component.impl;

import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.server.component.interfaces.TestInitArgComponent;

/**
 * Allay Project 2023/4/21
 *
 * @author daoge_cmd
 */
public class SimpleTestInitArgComponent implements TestInitArgComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_init_arg_component");

    protected final int data;

    public SimpleTestInitArgComponent(int data) {
        this.data = data;
    }

    @Override
    public int getData() {
        return data;
    }
}
