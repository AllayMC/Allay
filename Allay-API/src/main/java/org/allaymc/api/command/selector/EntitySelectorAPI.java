package org.allaymc.api.command.selector;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.args.SelectorArgument;
import org.allaymc.api.entity.Entity;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Allay Project 2024/2/25
 *
 * @author daoge_cmd
 */
public interface EntitySelectorAPI {

    ApiInstanceHolder<EntitySelectorAPI> API = ApiInstanceHolder.create();
    Pattern ENTITY_SELECTOR = Pattern.compile("^@([aeprs]|initiator)(?:\\[(.*)])?$");
    String ARGUMENT_JOINER = "=";

    static EntitySelectorAPI getAPI() {
        return API.get();
    }

    /**
     * Matches entities based on the given command sender and target selector token.
     *
     * @param sender the command sender
     * @param token  the target selector token
     *
     * @return the list of matched entities
     *
     * @throws SelectorSyntaxException if there is a syntax error in the selector token
     */
    List<Entity> matchEntities(CommandSender sender, String token) throws SelectorSyntaxException;

    /**
     * Checks if the given token is a valid target selector.
     *
     * @param token the token to check
     *
     * @return true if the token is a valid target selector, false otherwise
     */
    boolean checkValid(String token);

    /**
     * Registers a selector argument.
     *
     * @param argument the selector argument to register
     *
     * @return true if registration is successful (returns false if an argument with the same key already exists)
     */
    boolean registerArgument(SelectorArgument argument);
}
