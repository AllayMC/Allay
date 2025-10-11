package org.allaymc.server.entity.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.entity.component.EntityPlayerBossBarViewerComponent;
import org.allaymc.api.entity.component.EntityPlayerClientComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.protocol.bedrock.packet.BossEventPacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerBossBarViewerComponentImpl implements EntityPlayerBossBarViewerComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_boss_bar_viewer_component");

    @ComponentObject
    protected EntityPlayer thisPlayer;
    @Dependency
    protected EntityPlayerClientComponent clientComponent;

    @Override
    public void viewBossBar(BossBar bossBar) {
        // Clear any existing boss bar client-side before sending the new one. Although we
        // can use BossEventPacket.Action.UPDATE_XXX to update the existing one, it is much
        // easier to implement by creating a new one.
        clearBossBar();
        var packet = new BossEventPacket();
        packet.setBossUniqueEntityId(thisPlayer.getRuntimeId());
        packet.setAction(BossEventPacket.Action.CREATE);
        packet.setTitle(bossBar.getTitle());
        packet.setHealthPercentage(bossBar.getProgress());
        packet.setDarkenSky(bossBar.isDarkenSky() ? 1 : 0);
        packet.setColor(bossBar.getColor().ordinal());
        packet.setOverlay(bossBar.getStyle().ordinal());
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void clearBossBar() {
        var packet = new BossEventPacket();
        packet.setBossUniqueEntityId(thisPlayer.getRuntimeId());
        packet.setAction(BossEventPacket.Action.REMOVE);
        this.clientComponent.sendPacket(packet);
    }
}
