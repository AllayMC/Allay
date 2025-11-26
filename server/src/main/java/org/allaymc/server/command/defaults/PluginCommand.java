package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;

/**
 * @author daoge_cmd
 */
public class PluginCommand extends Command {

    public PluginCommand() {
        super("plugin", TrKeys.ALLAY_COMMAND_PLUGIN_DESCRIPTION, Permissions.COMMAND_PERMISSION);
        aliases.add("pl");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("list")
                .exec(context -> {
                    var str = String.join(", ", Server.getInstance()
                            .getPluginManager()
                            .getEnabledPlugins()
                            .keySet());
                    context.addOutput("Enabled plugins: " + TextFormat.GREEN + str);
                    return context.success();
                })
                .root()
                .key("reload")
                .str("pluginName")
                .exec(context -> {
                    var manager = Server.getInstance().getPluginManager();
                    String name = context.getResult(1);
                    if (!manager.isPluginEnabled(name)) {
                        context.addError("Plugin " + name + " is not enabled!");
                        return context.fail();
                    }

                    var plugin = manager.getEnabledPlugin(name).plugin();
                    if (!plugin.isReloadable()) {
                        context.addError("Plugin " + name + " is not reloadable!");
                        return context.fail();
                    }

                    plugin.reload();
                    context.addOutput(TextFormat.GREEN + "Plugin " + name + " has been reloaded");
                    return context.success();
                })
                .root()
                .key("reloadall")
                .exec(context -> {
                    var manager = Server.getInstance().getPluginManager();
                    for (var plugin : manager.getEnabledPlugins().values()) {
                        if (plugin.plugin().isReloadable()) {
                            plugin.plugin().reload();
                        }
                    }

                    context.addOutput(TextFormat.GREEN + "All reloadable plugins have been reloaded");
                    return context.success();
                });
    }
}
