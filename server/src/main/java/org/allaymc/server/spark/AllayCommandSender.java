package org.allaymc.server.spark;

import me.lucko.spark.common.command.sender.AbstractCommandSender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.UUID;

public final class AllayCommandSender extends AbstractCommandSender<org.allaymc.api.command.CommandSender> {

    public AllayCommandSender(org.allaymc.api.command.CommandSender delegate) {
        super(delegate);
    }

    @Override
    public String getName() {
        return delegate.getCommandSenderName();
    }

    @Override
    public UUID getUniqueId() {
        if (delegate instanceof EntityPlayer player) {
            return player.getUniqueId();
        }
        return null;
    }

    @Override
    public void sendMessage(Component message) {
        delegate.sendMessage(LegacyComponentSerializer.legacySection().serialize(message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return delegate.hasPermission(permission).asBoolean();
    }
}