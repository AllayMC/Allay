package org.allaymc.api.item.descriptor;

import org.allaymc.api.identifier.Identifier;

/**
 * Allay Project 2023/11/26
 *
 * TODO: 目前暂时不知道ComplexAliasDescriptor和DefaultDescriptor有什么区别
 *
 * @author daoge_cmd
 */
public class ComplexAliasDescriptor extends DefaultDescriptor {
    public ComplexAliasDescriptor(Identifier identifier, int meta) {
        super(identifier, meta);
    }

    public ComplexAliasDescriptor(Identifier identifier) {
        super(identifier);
    }
}
