package org.allaymc.server.command;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.perm.tree.PermTree;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;

/**
 * @author daoge_cmd
 */
public class ProxyCommandSender implements CommandSender {

    @Getter
    protected CommandSender origin;
    @Setter
    protected Location3fc cmdExecuteLocation;

    public ProxyCommandSender(CommandSender origin) {
        this.origin = origin;
    }

    @Override
    public String getCommandSenderName() {
        return origin.getCommandSenderName();
    }

    @Override
    public CommandOriginData getCommandOriginData() {
        return origin.getCommandOriginData();
    }

    @Override
    public Location3fc getCmdExecuteLocation() {
        if (cmdExecuteLocation != null) {
            return cmdExecuteLocation;
        }
        return origin.getCmdExecuteLocation();
    }

    @Override
    public void sendText(String text) {
        origin.sendText(text);
    }

    @Override
    public void sendTr(String key, boolean forceTranslatedByClient, String... args) {
        origin.sendTr(key, forceTranslatedByClient, args);
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        origin.sendCommandOutputs(sender, status, outputs);
    }

    @Override
    public PermTree getPermTree() {
        return origin.getPermTree();
    }
}
