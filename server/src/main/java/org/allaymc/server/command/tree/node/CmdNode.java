package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * {@code CmdNode} reconstructs remaining argument tokens as command text for
 * later dispatch. It inherits greedy message joining but surrounds a token
 * containing a literal space with double quotes, preserving that token's
 * grouping when the command string is parsed again. Client metadata advertises
 * nested command syntax rather than ordinary message syntax.
 *
 * <p> The quoting hook acts on already split tokens, so it cannot restore
 * original spacing between them. It only checks the space character, does not
 * escape embedded quotes, and leaves other whitespace forms unchanged. The
 * inherited matcher trims the assembled text. Child attachment is rejected
 * because all remaining tokens belong to this reconstructed command, while the
 * constructor retains a separate optional omission default.
 *
 * @author daoge_cmd
 */
public class CmdNode extends MessageNode {
    /**
     * Creates an unattached command-text node retaining its parent and omission
     * default. Matching greedily reconstructs remaining tokens, while omission
     * supplies the retained default only after the caller marks the node
     * optional.
     */
    public CmdNode(String name, CommandNode parent, String defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Returns a token surrounded by double quotes when it contains a literal
     * space. Tokens without that character pass through unchanged, including
     * tokens with other whitespace characters.
     *
     * <p> The inherited join inserts spaces between processed tokens, so
     * quoting a space-containing token keeps its internal grouping recognizable
     * to a later command-line split. Embedded quotes are not escaped, and this
     * hook does not reconstruct the original separator whitespace; callers must
     * account for those limits when redispatching arbitrary input.
     */
    @Override
    protected String processArg(String arg) {
        // Tokenization has already removed the original separators. Quote a token
        // containing spaces before the inherited join so a later command split can
        // retain that token's grouping, without claiming general quote escaping.
        return arg.contains(" ") ? "\"" + arg + "\"" : arg;
    }

    /**
     * Creates client metadata advertising {@code CommandParam.COMMAND} for
     * reconstructed command text.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.COMMAND);
        return data;
    }

    /**
     * Rejects child attachment because this node owns the remaining nested
     * command text. The method always raises
     * {@code UnsupportedOperationException}; callers install execution behavior
     * directly on this terminal node.
     */
    @Override
    public CommandNode addLeaf(CommandNode leaf) {
        throw new UnsupportedOperationException("CmdNode can't add leaf");
    }
}
