package org.allaymc.api.scoreboard;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.scoreboard.scorer.FakeScorer;
import org.allaymc.api.scoreboard.scorer.Scorer;
import org.allaymc.api.server.Server;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Getter
public final class Scoreboard {
    /**
     * 此计分板的标识名称
     */
    private final String objectiveName;
    /**
     * 此计分板的显示名称
     */
    private final String displayName;
    /**
     * 此计分板的 “准则” (eg: dummy)
     */
    private final String criteriaName;
    private final Map<DisplaySlot, Set<ScoreboardViewer>> viewers = new HashMap<>();
    private final Map<Scorer, ScoreboardLine> lines = new HashMap<>();
    /**
     * 此计分板的排序规则 <br>
     * 排序动作由客户端执行，所以说服务端不需要去处理排序
     */
    @Setter
    private SortOrder sortOrder;

    {
        for (var slot : DisplaySlot.values()) {
            viewers.put(slot, new HashSet<>());
        }
    }

    public Scoreboard(String objectiveName) {
        this(objectiveName, objectiveName);
    }

    public Scoreboard(String objectiveName, String displayName) {
        this(objectiveName, displayName, "dummy");
    }

    public Scoreboard(String objectiveName, String displayName, String criteriaName) {
        this(objectiveName, displayName, criteriaName, SortOrder.ASCENDING);
    }

    public Scoreboard(String objectiveName, String displayName, String criteriaName, SortOrder sortOrder) {
        this.objectiveName = objectiveName;
        this.displayName = displayName;
        this.criteriaName = criteriaName;
        this.sortOrder = sortOrder;
    }

    /**
     * @return 此计分板的所有观察者
     */
    public Set<ScoreboardViewer> getAllViewers() {
        var all = new HashSet<ScoreboardViewer>();
        this.viewers.values().forEach(all::addAll);
        return all;
    }

    /**
     * @param slot 目标槽位
     * @return 此计分板目标槽位的观察者
     */
    public Set<ScoreboardViewer> getViewers(DisplaySlot slot) {
        return this.viewers.get(slot);
    }

    /**
     * 删除此计分板目标槽位中的某个观察者
     * @param viewer 目标观察者
     * @param slot 目标槽位
     * @return 是否删除成功
     */
    public boolean removeViewer(ScoreboardViewer viewer, DisplaySlot slot) {
        boolean removed = this.viewers.get(slot).remove(viewer);
        if (removed) viewer.hideScoreboardSlot(slot);
        return removed;
    }

    /**
     * 向此计分板目标槽位中添加一个观察者
     * @param viewer 目标观察者
     * @param slot 目标槽位
     * @return 是否添加成功
     */
    public boolean addViewer(ScoreboardViewer viewer, DisplaySlot slot) {
        removeInvalidViewers();
        boolean added = this.viewers.get(slot).add(viewer);
        if (added) viewer.displayScoreboard(this, slot);
        return added;
    }

    /**
     * remove invalid viewers（eg: offline player）
     */
    private void removeInvalidViewers() {
        this.viewers.forEach((slot, slotViewers) -> {
            var invalid = new HashSet<ScoreboardViewer>();
            slotViewers.forEach(viewer -> {
                if (!viewer.isScoreboardViewerValid()) {
                    invalid.add(viewer);
                }
            });
            slotViewers.removeAll(invalid);
        });
    }

    /**
     * 检查此计分板目标槽位中是否有特定观察者
     * @param viewer 目标观察者
     * @param slot 目标槽位
     * @return 是否存在
     */
    public boolean containViewer(ScoreboardViewer viewer, DisplaySlot slot) {
        return this.viewers.get(slot).contains(viewer);
    }

    /**
     * 获取追踪对象在此计分板上对应的行（如果存在）
     * @param scorer 追踪对象
     * @return 对应行
     */
    public @Nullable ScoreboardLine getLine(Scorer scorer) {
        return this.lines.get(scorer);
    }

    /**
     * 为此计分板添加一个行
     * @param line 目标行
     * @return 是否添加成功
     */
    public boolean addLine(ScoreboardLine line) {
        if (wouldCallEvent()) {
            // TODO: event
//            var event = new ScoreboardLineChangeEvent(this, line, line.getScore(), line.getScore(), ScoreboardLineChangeEvent.ActionType.ADD_LINE);
//            Server.getInstance().getPluginManager().callEvent(event);
//            if (event.isCancelled()) return false;
//            line = event.getLine();
        }
        this.lines.put(line.getScorer(), line);
        updateScore(line);
        return true;
    }

    /**
     * 为此计分板添加一个行
     * @param scorer 追踪对象
     * @param score 分数
     * @return 是否添加成功
     */
    public boolean addLine(Scorer scorer, int score) {
        return addLine(new ScoreboardLine(this, scorer, score));
    }

    /**
     * 为插件提供的便捷的计分板显示接口
     * @param text FakeScorer的名称
     * @param score 分数
     * @return 是否添加成功
     */
    public boolean addLine(String text, int score) {
        var fakeScorer = new FakeScorer(text);
        return addLine(new ScoreboardLine(this, fakeScorer, score));
    }

    /**
     * 删除追踪对象在此计分板上对应的行（如果存在）
     * @param scorer 目标追踪对象
     * @return 是否删除成功
     */
    public boolean removeLine(Scorer scorer) {
        var removed = lines.get(scorer);
        if (removed == null) return false;
        if (wouldCallEvent()) {
            // TODO: event
//            var event = new ScoreboardLineChangeEvent(this, removed, removed.getScore(), removed.getScore(), ScoreboardLineChangeEvent.ActionType.REMOVE_LINE);
//            Server.getInstance().getPluginManager().callEvent(event);
//            if (event.isCancelled()) return false;
        }
        this.lines.remove(scorer);
        getAllViewers().forEach(viewer -> viewer.removeScoreboardLine(removed));
        return true;
    }

    /**
     * 删除计分板所有行
     * @param send 是否发送到观察者
     * @return 是否删除成功
     */
    public boolean removeAllLines(boolean send) {
        if (lines.isEmpty()) return false;
        if (wouldCallEvent()) {
            // TODO: event
//            var event = new ScoreboardLineChangeEvent(this, null, 0, 0, ScoreboardLineChangeEvent.ActionType.REMOVE_ALL_LINES);
//            Server.getInstance().getPluginManager().callEvent(event);
//            if (event.isCancelled()) return false;
        }
        if (send) {
            this.lines.keySet().forEach(this::removeLine);
        } else {
            this.lines.clear();
        }
        return true;
    }

    /**
     * 检查追踪对象在此计分板上是否有记录
     * @param scorer 目标追踪对象
     * @return 是否存在
     */
    public boolean containLine(Scorer scorer) {
        return this.lines.containsKey(scorer);
    }

    /**
     * 向所有观察者发送新的分数 <br>
     * @param update 需要更新的行
     */
    public void updateScore(ScoreboardLine update) {
        getAllViewers().forEach(viewer -> viewer.updateScore(update));
    }

    /**
     * 向所有观察者重新发送此计分板以及行信息 <br>
     * 例如当对计分板进行了大量的更改后，调用此方法 <br>
     * 可节省大量带宽
     */
    public void resend() {
        getAllViewers().forEach(viewer -> viewer.removeScoreboard(this));

        this.viewers.forEach((slot, slotViewers) -> {
            slotViewers.forEach(slotViewer -> {
                slotViewer.displayScoreboard(this, slot);
            });
        });
    }

    /**
     * 为插件提供的快捷接口 <br>
     * 按照List顺序设置计分板的内容 (使用FakeScorer作为追踪对象) <br>
     * 会覆盖之前的所有行 <br>
     * @param lines 需要设置的字符串内容
     */
    public void setLines(List<String> lines) {
        removeAllLines(false);
        AtomicInteger score = new AtomicInteger();
        lines.forEach(str -> {
            var scorer = new FakeScorer(str);
            this.lines.put(scorer, new ScoreboardLine(this, scorer, score.getAndIncrement()));
        });
        resend();
    }

    /**
     * 按照List顺序设置计分板的内容 <br>
     * 会覆盖之前的所有行 <br>
     * @param lines 需要设置的行内容
     */
    public void setLines(Collection<ScoreboardLine> lines) {
        removeAllLines(false);
        lines.forEach(line -> this.lines.put(line.getScorer(), line));
        resend();
    }

    /**
     * @return 对此计分板的更改是否会产生事件
     */
    public boolean wouldCallEvent() {
        return Server.getInstance().getScoreboardService().contain(this);
    }
}
