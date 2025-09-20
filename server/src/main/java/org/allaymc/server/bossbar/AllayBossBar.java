package org.allaymc.server.bossbar;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.bossbar.BossBarColor;
import org.allaymc.api.bossbar.BossBarStyle;
import org.allaymc.api.bossbar.BossBarViewer;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class AllayBossBar implements BossBar {

    protected Set<BossBarViewer> viewers = new HashSet<>();
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
    public void addViewer(BossBarViewer viewer) {
        if (viewers.add(viewer)) {
            viewer.viewBossBar(this);
        }
    }

    @Override
    public void removeViewer(BossBarViewer viewer) {
        if (viewers.remove(viewer)) {
            viewer.clearBossBar();
        }
    }

    @Override
    public @UnmodifiableView Collection<BossBarViewer> getViewers() {
        return Collections.unmodifiableSet(viewers);
    }

    @Override
    public void setColor(BossBarColor color) {
        this.color = color;
        update();
    }

    @Override
    public void setStyle(BossBarStyle style) {
        this.style = style;
        update();
    }

    @Override
    public void setDarkenSky(boolean darkenSky) {
        this.darkenSky = darkenSky;
        update();
    }

    @Override
    public void setProgress(@Range(from = 0, to = 1) float progress) {
        Preconditions.checkArgument(progress >= 0 && progress <= 1, "Progress must be between 0 and 1");
        this.progress = progress;
        update();
    }

    @Override
    public void setTitle(String name) {
        this.title = name;
        update();
    }

    protected void update() {
        this.viewers.forEach(viewer -> viewer.viewBossBar(this));
    }
}
