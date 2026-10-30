package org.allaymc.server.command.defaults;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.utils.GitProperties;

/**
 * {@code VersionCommand} reports server and API build versions alongside the
 * supported Minecraft and protocol ranges. Its handler derives range endpoints
 * from the configured codecs and adds a development-build notice when the
 * active API identifies the current build as a development version.
 *
 * @author daoge_cmd
 */
public class VersionCommand extends Command {

    /**
     * Creates the version command and its {@code /ver} and {@code /about} aliases.
     */
    public VersionCommand() {
        super("version", TrKeys.ALLAY_COMMAND_VERSION_DESCRIPTION, Permissions.COMMAND_VERSION);
        aliases.add("ver");
        aliases.add("about");
    }

    /**
     * Installs the argument-free version and supported-protocol reporting handler.
     */
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
