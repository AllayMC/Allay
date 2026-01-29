package org.allaymc.server.eventbus.event.network;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.allaymc.server.player.AllayPlayer;

/**
 * @author hax0r31337
 */
@Getter
@RequiredArgsConstructor
public class NettyPipelineInitEvent extends NetworkEvent {

    public static final AttributeKey<AllayPlayer> PLAYER_ATTRIBUTE_KEY = AttributeKey.valueOf("allay_player");
    private final Channel channel;
}
