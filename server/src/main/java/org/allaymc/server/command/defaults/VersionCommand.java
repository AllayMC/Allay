package org.allaymc.server.command.defaults;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.server.network.ProtocolInfo;
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
            var versionStr = ProtocolInfo.getLowestCodec().getMinecraftVersion() + " - " + ProtocolInfo.getLatestCodec().getMinecraftVersion();
            var protocolStr = ProtocolInfo.getLowestCodec().getProtocolVersion() + " - " + ProtocolInfo.getLatestCodec().getProtocolVersion();
            context.addOutput(
                    TrKeys.ALLAY_COMMAND_VERSION_OUTPUT,
                    AllayAPI.getInstance().getCoreName(), GitProperties.getBuildVersion(),
                    GitProperties.getBuildApiVersion(), versionStr, protocolStr
            );
            if (AllayAPI.getInstance().isDevBuild()) {
                context.addOutput(I18n.get().tr(TrKeys.ALLAY_SERVER_IS_DEV_VERSION));
            }
            return context.success();
        });
    }
}
