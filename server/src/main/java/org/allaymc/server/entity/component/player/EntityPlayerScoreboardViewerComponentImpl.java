package org.allaymc.server.entity.component.player;

import org.allaymc.api.entity.component.player.EntityPlayerScoreboardViewerComponent;
import org.allaymc.api.entity.data.EntityData;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.FakeScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;
import org.cloudburstmc.protocol.bedrock.packet.RemoveObjectivePacket;
import org.cloudburstmc.protocol.bedrock.packet.SetDisplayObjectivePacket;
import org.cloudburstmc.protocol.bedrock.packet.SetScorePacket;

import java.util.Objects;

/**
 * @author daoge_cmd
 */
public class EntityPlayerScoreboardViewerComponentImpl implements EntityPlayerScoreboardViewerComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_scoreboard_viewer_component");

    @Dependency
    protected EntityPlayerNetworkComponentImpl networkComponent;

    @ComponentObject
    protected EntityPlayer thisPlayer;

    @Override
    public void displayScoreboard(Scoreboard scoreboard, DisplaySlot slot) {
        var setDisplayObjectivePacket = new SetDisplayObjectivePacket();
        setDisplayObjectivePacket.setDisplaySlot(slot.getSlotName());
        setDisplayObjectivePacket.setObjectiveId(scoreboard.getObjectiveName());
        setDisplayObjectivePacket.setDisplayName(scoreboard.getDisplayName());
        setDisplayObjectivePacket.setCriteria(scoreboard.getCriteriaName());
        setDisplayObjectivePacket.setSortOrder(scoreboard.getSortOrder().ordinal());
        networkComponent.sendPacket(setDisplayObjectivePacket);

        // Client won't storage the score of a scoreboard,so we should send the score to client
        var setScorePacket = new SetScorePacket();
        setScorePacket.setInfos(
                scoreboard.getLines().values()
                        .stream()
                        .map(this::toNetwork)
                        .filter(Objects::nonNull)
                        .toList()
        );
        setScorePacket.setAction(SetScorePacket.Action.SET);
        networkComponent.sendPacket(setScorePacket);

        var scorer = new PlayerScorer(thisPlayer);
        var line = scoreboard.getLine(scorer);
        if (slot == DisplaySlot.BELOW_NAME && line != null) {
            thisPlayer.setData(EntityData.SCORE, line.getScore() + " " + scoreboard.getDisplayName());
        }
    }

    protected ScoreInfo toNetwork(ScoreboardLine line) {
        var scoreboard = line.getScoreboard();
        switch (line.getScorer()) {
            case EntityScorer scorer -> {
                return new ScoreInfo(
                        line.getLineId(), scoreboard.getObjectiveName(), line.getScore(),
                        ScoreInfo.ScorerType.ENTITY, scorer.getUniqueId()
                );
            }
            case PlayerScorer scorer -> {
                if (scorer.getUuid() == null) {
                    return null;
                }

                var player = Server.getInstance().getPlayerManager().getPlayers().get(scorer.getUuid());
                if (player == null) {
                    return null;
                }

                return new ScoreInfo(
                        line.getLineId(), scoreboard.getObjectiveName(), line.getScore(),
                        ScoreInfo.ScorerType.PLAYER, player.getRuntimeId()
                );
            }
            case FakeScorer scorer -> {
                return new ScoreInfo(
                        line.getLineId(), scoreboard.getObjectiveName(),
                        line.getScore(), scorer.getFakeName()
                );
            }
        }
    }

    @Override
    public void hideScoreboardSlot(DisplaySlot slot) {
        var packet = new SetDisplayObjectivePacket();
        packet.setDisplaySlot(slot.getSlotName());
        packet.setObjectiveId("");
        packet.setDisplayName("");
        packet.setCriteria("");
        packet.setSortOrder(SortOrder.ASCENDING.ordinal());
        networkComponent.sendPacket(packet);

        if (slot == DisplaySlot.BELOW_NAME) {
            thisPlayer.setData(EntityData.SCORE, "");
        }
    }

    @Override
    public void removeScoreboard(Scoreboard scoreboard) {
        var packet = new RemoveObjectivePacket();
        packet.setObjectiveId(scoreboard.getObjectiveName());

        networkComponent.sendPacket(packet);
    }

    @Override
    public void removeScoreboardLine(ScoreboardLine line) {
        var packet = new SetScorePacket();
        packet.setAction(SetScorePacket.Action.REMOVE);
        var networkInfo = toNetwork(line);
        if (networkInfo != null) {
            packet.getInfos().add(networkInfo);
        }
        networkComponent.sendPacket(packet);

        var scorer = new PlayerScorer(thisPlayer);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(thisPlayer)) {
            thisPlayer.setData(EntityData.SCORE, "");
        }
    }

    @Override
    public void updateScore(ScoreboardLine line) {
        var packet = new SetScorePacket();
        packet.setAction(SetScorePacket.Action.SET);
        var networkInfo = toNetwork(line);
        if (networkInfo != null) {
            packet.getInfos().add(networkInfo);
        }
        networkComponent.sendPacket(packet);

        var scorer = new PlayerScorer(thisPlayer);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(this)) {
            thisPlayer.setData(EntityData.SCORE, line.getScore() + " " + line.getScoreboard().getDisplayName());
        }
    }

    @Override
    public boolean isScoreboardViewerValid() {
        return !networkComponent.isDisconnected();
    }
}
