package cn.allay.server.command.defaults;

import cn.allay.api.command.CommandSender;
import cn.allay.api.command.annotation.Command;
import cn.allay.api.command.annotation.DefaultExecuteFor;
import cn.allay.api.command.annotation.Description;
import cn.allay.api.command.annotation.Overload;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.GameType;

@Description("change gamemode")
@Command({"gamemode", "gm", "gmc", "gms"})
public class GamemodeCommand {

    @DefaultExecuteFor({"gamemode", "gm"})
    public void gamemodeInt(CommandSender sender, int mode, EntityPlayer target) {
        sender.reply("int");
    }

    @Overload("gamemode") // only gamemode for tests
    public void gamemodeEnum(CommandSender sender, GameType mode, EntityPlayer target) {
        sender.reply("enum");
    }

    @DefaultExecuteFor("gmc")
    public void gmc(CommandSender sender, EntityPlayer target) {
        gamemodeEnum(sender, GameType.CREATIVE, target);
    }

    @DefaultExecuteFor("gms")
    public void gms(CommandSender sender, EntityPlayer target) {
        gamemodeEnum(sender, GameType.SURVIVAL, target);
    }
}
