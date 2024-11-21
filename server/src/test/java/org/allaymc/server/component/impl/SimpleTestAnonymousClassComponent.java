package org.allaymc.server.component.impl;

import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.interfaces.TestAnonymousClassComponent;

/**
 * @author daoge_cmd
 */
public class SimpleTestAnonymousClassComponent implements TestAnonymousClassComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_anonymous_class_component");
}
