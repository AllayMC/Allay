package org.allaymc.api.scoreboard;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityDespawnEvent;
import org.allaymc.api.eventbus.event.scoreboard.ScoreboardAddEvent;
import org.allaymc.api.eventbus.event.scoreboard.ScoreboardRemoveEvent;
import org.allaymc.api.eventbus.event.server.PlayerJoinEvent;
import org.allaymc.api.eventbus.event.server.PlayerQuitEvent;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.scoreboard.scorer.Scorer;
import org.allaymc.api.server.Server;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages and stores a batch of scoreboards.
 * <p>
 * This interface is for /scoreboard command.
 * if you just want to display information, please operate directly on the scoreboard object.
 *
 * @author daoge_cmd
 */
@Getter
public final class ScoreboardManager {

    private final Map<DisplaySlot, Scoreboard> display = new ConcurrentHashMap<>();
    private final Set<ScoreboardViewer> viewers = Sets.newConcurrentHashSet();
    private final ScoreboardStorage storage;
    private Map<String, Scoreboard> scoreboards = new ConcurrentHashMap<>();

    public ScoreboardManager(Server server, ScoreboardStorage storage) {
        this.storage = storage;
        var eventBus = server.getEventBus();
        eventBus.registerListenerFor(EntityDespawnEvent.class, event -> {
            var entity = event.getEntity();
            // Do not handle player
            if (entity instanceof EntityPlayer) return;
            removeScorerFromAllScoreboards(new EntityScorer(entity));
        });
        eventBus.registerListenerFor(PlayerJoinEvent.class, event -> {
            var player = event.getPlayer();
            addViewer(player);
            var scorer = new PlayerScorer(player);
            scoreboards.values().forEach(scoreboard -> {
                if (scoreboard.containLine(scorer)) {
                    viewers.forEach(viewer -> viewer.updateScore(scoreboard.getLine(scorer)));
                }
            });
        });
        eventBus.registerListenerFor(PlayerQuitEvent.class, event -> {
            var player = event.getPlayer();
            if (!player.isInitialized()) return;
            var scorer = new PlayerScorer(player);
            scoreboards.values().forEach(scoreboard -> {
                if (scoreboard.containLine(scorer)) {
                    viewers.forEach(viewer -> viewer.removeScoreboardLine(scoreboard.getLine(scorer)));
                }
            });
            removeViewer(player);
        });
    }

    public boolean add(Scoreboard scoreboard) {
        var event = new ScoreboardAddEvent(scoreboard);
        if (!event.call()) {
            return false;
        }

        scoreboards.put(scoreboard.getObjectiveName(), scoreboard);
        // TODO: soft enum
        //       CommandEnum.SCOREBOARD_OBJECTIVES.updateSoftEnum(UpdateSoftEnumPacket.Type.ADD, scoreboard.getObjectiveName());
        return true;
    }

    public boolean remove(Scoreboard scoreboard) {
        return remove(scoreboard.getObjectiveName());
    }

    public boolean remove(String objectiveName) {
        var removed = scoreboards.get(objectiveName);
        if (removed == null) return false;

        var event = new ScoreboardRemoveEvent(removed);
        if (!event.call()) {
            return false;
        }

        scoreboards.remove(objectiveName);
        // TODO: soft enum
        //       CommandEnum.SCOREBOARD_OBJECTIVES.updateSoftEnum(UpdateSoftEnumPacket.Type.REMOVE, objectiveName);
        viewers.forEach(viewer -> viewer.removeScoreboard(removed));
        display.forEach((slot, scoreboard) -> {
            if (scoreboard != null && scoreboard.getObjectiveName().equals(objectiveName)) {
                clearDisplaySlot(slot);
            }
        });
        return true;
    }

    public Scoreboard get(String objectiveName) {
        return scoreboards.get(objectiveName);
    }

    public boolean contain(Scoreboard scoreboard) {
        return scoreboards.containsKey(scoreboard.getObjectiveName());
    }

    public boolean contain(String name) {
        return scoreboards.containsKey(name);
    }

    public Scoreboard getDisplaySlot(DisplaySlot slot) {
        return display.get(slot);
    }

    public void setDisplaySlot(DisplaySlot slot, Scoreboard scoreboard) {
        Preconditions.checkNotNull(scoreboard);
        clearDisplaySlot(slot);
        this.viewers.forEach(viewer -> scoreboard.addViewer(viewer, slot));
    }

    public void clearDisplaySlot(DisplaySlot slot) {
        var old = display.remove(slot);
        if (old != null) this.viewers.forEach(viewer -> old.removeViewer(viewer, slot));
    }

    public boolean addViewer(ScoreboardViewer viewer) {
        var added = this.viewers.add(viewer);
        if (added) this.display.forEach((slot, scoreboard) -> {
            scoreboard.addViewer(viewer, slot);
        });
        return added;
    }

    public boolean removeViewer(ScoreboardViewer viewer) {
        var removed = viewers.remove(viewer);
        if (removed) this.display.forEach((slot, scoreboard) -> {
            scoreboard.removeViewer(viewer, slot);
        });
        return removed;
    }

    public void save() {
        storage.save(scoreboards.values());
        storage.saveDisplay(display);
    }

    public void read() {
        // Create a new list to avoid iteration conflicts
        new ArrayList<>(scoreboards.values()).forEach(this::remove);
        this.display.keySet().forEach(this::clearDisplaySlot);

        scoreboards = storage.readAll();
        storage.readDisplay().forEach((slot, objectiveName) -> {
            var scoreboard = get(objectiveName);
            if (scoreboard != null) {
                this.setDisplaySlot(slot, scoreboard);
            }
        });
    }

    public void removeScorerFromAllScoreboards(Scorer scorer) {
        scoreboards.values().forEach(scoreboard -> scoreboard.removeLine(scorer));
    }
}
