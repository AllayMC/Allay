package org.allaymc.api.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the weather in the world.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum Weather {
    CLEAR(20 * 60 * 10, 20 * 60 * 150, null, null),
    RAIN(20 * 60 * 3, 20 * 60 * 13, LevelEvent.START_RAINING, LevelEvent.STOP_RAINING),
    THUNDER(20 * 60 * 3, 20 * 60 * 13, LevelEvent.START_THUNDERSTORM, LevelEvent.STOP_THUNDERSTORM);

    private final int minTime;
    private final int maxTime;
    private final LevelEvent startLevelEvent;
    private final LevelEvent stopLevelEvent;

    /**
     * Generate a random time length for this weather.
     *
     * @return the random time length for this weather
     */
    public int generateRandomTimeLength() {
        return ThreadLocalRandom.current().nextInt(minTime, maxTime + 1);
    }

    /**
     * Create a start level event packet for this weather.
     *
     * @return the start level event packet for this weather, or {@code null} if the start level event is {@code null}
     */
    public LevelEventPacket createStartLevelEventPacket() {
        if (startLevelEvent == null) {
            return null;
        }

        var pk = new LevelEventPacket();
        pk.setType(startLevelEvent);
        pk.setPosition(Vector3f.ZERO);
        pk.setData(65535);
        return pk;
    }

    /**
     * Create a stop level event packet for this weather.
     *
     * @return the stop level event packet for this weather, or {@code null} if the stop level event is {@code null}
     */
    public LevelEventPacket createStopLevelEventPacket() {
        if (stopLevelEvent == null) {
            return null;
        }

        var pk = new LevelEventPacket();
        pk.setType(stopLevelEvent);
        pk.setPosition(Vector3f.ZERO);
        return pk;
    }
}