package cn.allay.server.network.handlers;

import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketHandler;

import java.util.regex.Pattern;

public class LoginHandler implements BedrockPacketHandler {
    public static final Pattern NAME_PATTERN = Pattern.compile("^[aA-zZ\\s\\d_]{3,16}+$");

    private final BedrockServerSession session;

    public LoginHandler(BedrockServerSession session) {
        this.session = session;
    }


}
