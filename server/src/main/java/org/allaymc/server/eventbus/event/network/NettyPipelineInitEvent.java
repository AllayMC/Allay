package org.allaymc.server.eventbus.event.network;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.allaymc.server.player.AllayPlayer;

/**
 * {@code NettyPipelineInitEvent} exposes a channel after the base initializer
 * installs framing and initial compression handlers. The RakNet initializer
 * dispatches this event before its NetEase compression adjustment and before
 * packet codecs and the peer are installed. Listeners therefore receive a
 * pipeline that later initialization steps will continue modifying.
 *
 * @author hax0r31337
 */
@Getter
@RequiredArgsConstructor
public class NettyPipelineInitEvent extends NetworkEvent {

    /**
     * {@code PLAYER_ATTRIBUTE_KEY} identifies the channel attribute populated
     * when {@code initPlayerSession} creates its player. The attribute can be
     * absent while pipeline initialization listeners run.
     */
    public static final AttributeKey<AllayPlayer> PLAYER_ATTRIBUTE_KEY = AttributeKey.valueOf("allay_player");
    /**
     * {@code channel} retains the channel being initialized. Its generated getter
     * exposes the same channel and live pipeline for listener inspection or
     * modification.
     */
    private final Channel channel;
}
