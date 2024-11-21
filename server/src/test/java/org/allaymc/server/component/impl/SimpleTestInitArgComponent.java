package org.allaymc.server.component.impl;

import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.interfaces.TestInitArgComponent;

/**
 * @author daoge_cmd
 */
public class SimpleTestInitArgComponent implements TestInitArgComponent {
    @Identifier.Component
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
