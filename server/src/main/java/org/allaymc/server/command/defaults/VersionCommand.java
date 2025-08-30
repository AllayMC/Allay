package org.allaymc.server.command.defaults;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.server.utils.GitProperties;

/**
 * @author daoge_cmd
 */
public class VersionCommand extends VanillaCommand {

    public VersionCommand() {
        super("version", TrKeys.ALLAY_COMMAND_VERSION_DESCRIPTION);
        aliases.add("ver");
        aliases.add("about");
        getPermissions().forEach(PermissionGroups.MEMBER::addPermission);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().exec(context -> {
            context.addOutput(
                    TrKeys.ALLAY_COMMAND_VERSION_OUTPUT,
                    AllayAPI.getInstance().getCoreName(),
                    GitProperties.getBuildVersion(),
                    GitProperties.getBuildApiVersion()
            );
            if (AllayAPI.getInstance().isDevBuild()) {
                context.addOutput(I18n.get().tr(TrKeys.ALLAY_SERVER_IS_DEV_VERSION));
            }
            return context.success();
        });
    }
}
