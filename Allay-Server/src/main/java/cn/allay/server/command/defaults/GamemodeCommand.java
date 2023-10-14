package cn.allay.server.command.defaults;

import cn.allay.api.command.CommandSender;
import cn.allay.api.command.annotation.Command;
import cn.allay.api.command.annotation.DefaultExecute;
import cn.allay.api.command.annotation.Overload;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.GameType;

@Command({"gamemode", "gm"})
public class GamemodeCommand {

    @DefaultExecute
    public void gamemodeInt(CommandSender sender, int mode, EntityPlayer target) {
        sender.reply("int");
    }

    @Overload
    public void gamemodeEnum(CommandSender sender, GameType mode, EntityPlayer target) {
        sender.reply("enum");
    }
}
