package org.allaymc.server.component.impl;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.server.component.interfaces.TestAnonymousClassComponent;

/**
 * Allay Project 2023/9/28
 *
 * @author daoge_cmd
 */
public class SimpleTestAnonymousClassComponent implements TestAnonymousClassComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_anonymous_class_component");
}
