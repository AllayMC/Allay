package org.allaymc.api.scoreboard;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.server.player.PlayerInitializedEvent;
import org.allaymc.api.eventbus.event.server.player.PlayerQuitEvent;
import org.allaymc.api.eventbus.event.world.entity.EntityDespawnEvent;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.scoreboard.storage.ScoreboardStorage;
import org.allaymc.api.server.Server;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2024/2/28
 *
 * @author daoge_cmd
 *
 * 管理，储存一批计分板 <br>
 * 此接口面向/scoreboard命令，若只是想要显示信息，请直接操作scoreboard对象
 */
@Getter
public final class ScoreboardService {

    private Map<String, Scoreboard> scoreboards = new ConcurrentHashMap<>();
    private final Map<DisplaySlot, Scoreboard> display = new ConcurrentHashMap<>();
    private final Set<ScoreboardViewer> viewers = Sets.newConcurrentHashSet();
    private final ScoreboardStorage storage;

    public ScoreboardService(Server server, ScoreboardStorage storage) {
        this.storage = storage;
        read();
        server.getEventBus().registerListener(new ServerEventListener());
        server.getWorldPool().registerListenerForAllWorlds(new AllWorldEventListener());
    }

    public boolean add(Scoreboard scoreboard) {
        // TODO: event
//        var event = new ScoreboardObjectiveChangeEvent(scoreboard, ScoreboardObjectiveChangeEvent.ActionType.ADD);
//        Server.getInstance().getPluginManager().callEvent(event);
//        if (event.isCancelled()) {
//            return false;
//        }
        scoreboards.put(scoreboard.getObjectiveName(), scoreboard);
        // TODO: soft enum
//        CommandEnum.SCOREBOARD_OBJECTIVES.updateSoftEnum(UpdateSoftEnumPacket.Type.ADD, scoreboard.getObjectiveName());
        return true;
    }

    public boolean remove(Scoreboard scoreboard) {
        return remove(scoreboard.getObjectiveName());
    }

    public boolean remove(String objectiveName) {
        var removed = scoreboards.remove(objectiveName);
        if (removed == null) return false;
        // TODO: event
//        var event = new ScoreboardObjectiveChangeEvent(removed, ScoreboardObjectiveChangeEvent.ActionType.REMOVE);
//        Server.getInstance().getPluginManager().callEvent(event);
//        if (event.isCancelled()) {
//            return false;
//        }
        // TODO: soft enum
//        CommandEnum.SCOREBOARD_OBJECTIVES.updateSoftEnum(UpdateSoftEnumPacket.Type.REMOVE, objectiveName);
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
        var added =  this.viewers.add(viewer);
        if (added) this.display.forEach((slot, scoreboard) -> {
            if (scoreboard != null) scoreboard.addViewer(viewer, slot);
        });
        return added;
    }

    public boolean removeViewer(ScoreboardViewer viewer) {
        var removed = viewers.remove(viewer);
        if (removed) this.display.forEach((slot, scoreboard) -> {
            if (scoreboard != null) scoreboard.removeViewer(viewer, slot);
        });
        return removed;
    }

    public void save() {
        storage.removeAll();
        storage.save(scoreboards.values());
        storage.saveDisplay(display);
    }

    public void read() {
        //新建一个列表避免迭代冲突
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

    public class AllWorldEventListener {
        @EventHandler
        public void onEntityDie(EntityDespawnEvent event) {
            var entity = event.getEntity();
            // Do not handle player
            if (entity instanceof EntityPlayer) return;
            var scorer = new EntityScorer(entity);
            scoreboards.forEach((s, scoreboard) -> {
                if (scoreboard.getLines().isEmpty()) return;
                scoreboard.removeLine(scorer);
            });
        }
    }

    public class ServerEventListener {
        @EventHandler
        public void onPlayerInitialized(PlayerInitializedEvent event) {
            var player = event.getPlayer();
            addViewer(player);
            var scorer = new PlayerScorer(player);
            scoreboards.values().forEach(scoreboard -> {
                if (scoreboard.containLine(scorer)) {
                    viewers.forEach(viewer -> viewer.updateScore(scoreboard.getLine(scorer)));
                }
            });
        }

        @EventHandler
        public void onPlayerQuit(PlayerQuitEvent event) {
            var player = event.getPlayer();
            var scorer = new PlayerScorer(player);
            scoreboards.values().forEach(scoreboard -> {
                if (scoreboard.containLine(scorer)) {
                    viewers.forEach(viewer -> viewer.removeScoreboardLine(scoreboard.getLine(scorer)));
                }
            });
            removeViewer(player);
        }
    }
}
