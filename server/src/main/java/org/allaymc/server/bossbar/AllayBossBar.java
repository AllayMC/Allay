package org.allaymc.server.bossbar;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.bossbar.BossBarColor;
import org.allaymc.api.bossbar.BossBarStyle;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BossEventPacket;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
public class AllayBossBar implements BossBar {

    protected Set<EntityPlayer> viewers = new HashSet<>();
    @Getter
    protected String title = "";
    @Getter
    protected float progress = 1.0f;
    @Getter
    protected BossBarColor color = BossBarColor.PINK;
    @Getter
    protected BossBarStyle style = BossBarStyle.SOLID;
    @Getter
    protected boolean darkenSky = false;

    @Override
    public void addViewer(EntityPlayer viewer) {
        viewers.add(viewer);
        var pk = new BossEventPacket();
        pk.setBossUniqueEntityId(viewer.getRuntimeId());
        pk.setAction(BossEventPacket.Action.CREATE);
        pk.setTitle(title);
        pk.setHealthPercentage(progress);
        pk.setDarkenSky(darkenSky ? 1 : 0);
        pk.setColor(color.ordinal());
        pk.setOverlay(style.ordinal());
        viewer.sendPacket(pk);
    }

    @Override
    public void removeViewer(EntityPlayer viewer) {
        viewers.remove(viewer);
        var pk = new BossEventPacket();
        pk.setBossUniqueEntityId(viewer.getRuntimeId());
        pk.setAction(BossEventPacket.Action.REMOVE);
        viewer.sendPacket(pk);
    }

    @Override
    public @UnmodifiableView Collection<EntityPlayer> getViewers() {
        return Collections.unmodifiableSet(viewers);
    }

    @Override
    public void setColor(BossBarColor color) {
        this.color = color;
        updateProperties();
    }

    @Override
    public void setStyle(BossBarStyle style) {
        this.style = style;
        updateProperties();
    }

    @Override
    public void setDarkenSky(boolean darkenSky) {
        this.darkenSky = darkenSky;
        sendPacketToViewers(pk -> {
            pk.setAction(BossEventPacket.Action.UPDATE_PROPERTIES);
            pk.setDarkenSky(darkenSky ? 1 : 0);
        });
    }

    @Override
    public void setProgress(@Range(from = 0, to = 1) float progress) {
        Preconditions.checkArgument(progress >= 0 && progress <= 1, "Progress must be between 0 and 1");
        this.progress = progress;
        sendPacketToViewers(pk -> {
            pk.setAction(BossEventPacket.Action.UPDATE_PERCENTAGE);
            pk.setHealthPercentage(progress);
        });
    }

    @Override
    public void setTitle(String name) {
        this.title = name;
        sendPacketToViewers(pk -> {
            pk.setAction(BossEventPacket.Action.UPDATE_NAME);
            pk.setTitle(name);
        });
    }

    protected void updateProperties() {
        sendPacketToViewers(pk -> {
            pk.setAction(BossEventPacket.Action.UPDATE_STYLE);
            pk.setColor(color.ordinal());
            pk.setOverlay(style.ordinal());
        });
    }

    protected void sendPacketToViewers(Consumer<BossEventPacket> processor) {
        viewers.forEach(viewer -> {
            var pk = new BossEventPacket();
            pk.setBossUniqueEntityId(viewer.getRuntimeId());
            processor.accept(pk);
            viewer.sendPacket(pk);
        });
    }
}
