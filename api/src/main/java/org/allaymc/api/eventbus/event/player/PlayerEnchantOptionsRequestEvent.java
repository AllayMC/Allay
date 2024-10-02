package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantOptionData;

import java.util.List;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class PlayerEnchantOptionsRequestEvent extends PlayerEvent implements CancellableEvent {

    protected List<EnchantOptionData> enchantOptions;

    public PlayerEnchantOptionsRequestEvent(EntityPlayer player, List<EnchantOptionData> enchantOptions) {
        super(player);
        this.enchantOptions = enchantOptions;
    }
}
