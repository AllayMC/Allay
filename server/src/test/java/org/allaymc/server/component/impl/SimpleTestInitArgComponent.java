package org.allaymc.server.component.impl;

import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.interfaces.TestInitArgComponent;

/**
 * @author daoge_cmd
 */
public class SimpleTestInitArgComponent implements TestInitArgComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:test_init_arg_component");

    protected final int data;

    public SimpleTestInitArgComponent(int data) {
        this.data = data;
    }

    @Override
    public int getData() {
        return data;
    }
}
