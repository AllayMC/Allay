package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorArgument;
import org.allaymc.api.command.selector.SelectorType;

import java.util.List;
import java.util.Map;

/**
 * ScopeArgument is the base class for args like {@link DX}, {@link DY} and {@link DZ}.
 */
public abstract class ScopeArgument implements SelectorArgument {
    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String getDefaultValue(Map<String, List<String>> values, SelectorType selectorType, CommandSender sender) {
        if (values.containsKey("dx") || values.containsKey("dy") || values.containsKey("dz")) {
            return "0";
        }

        return null;
    }
}
