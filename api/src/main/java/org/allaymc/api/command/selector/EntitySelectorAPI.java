package org.allaymc.api.command.selector;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author daoge_cmd
 */
public interface EntitySelectorAPI {

    AllayAPI.APIInstanceHolder<EntitySelectorAPI> API = AllayAPI.APIInstanceHolder.create();

    /**
     * Pattern for matching entity selector tokens (e.g., @a, @e, etc.).
     */
    Pattern ENTITY_SELECTOR = Pattern.compile("^@([aeprs])(?:\\[(.*)])?$");

    /**
     * String used to join selector arguments.
     */
    String ARGUMENT_JOINER = "=";

    static EntitySelectorAPI getAPI() {
        return API.get();
    }

    /**
     * Matches entities based on the given command sender and target selector token.
     * <p>
     * This method parses the selector token and returns a list of entities that
     * match the criteria specified by the token.
     *
     * @param sender the command sender, used for context (e.g., location, permissions)
     * @param token  the target selector token, such as {@code @a} or {@code @e[type=zombie]}
     * @return a list of entities that match the selector criteria
     * @throws SelectorSyntaxException if there is a syntax error in the selector token
     */
    List<Entity> matchEntities(CommandSender sender, String token) throws SelectorSyntaxException;

    /**
     * Checks if the given token is a valid entity selector.
     *
     * @param token the token to validate (e.g., {@code @a}, {@code @e})
     * @return {@code true} if the token is a valid target selector, {@code false} otherwise.
     */
    boolean checkValid(String token);

    /**
     * Registers a custom selector argument.
     * <p>
     * This allows for adding custom arguments to the entity selector system, such as
     * new filters or modifiers.
     *
     * @param argument the selector argument to register
     * @return {@code true} if the argument was successfully registered,
     * {@code false} if an argument with the same key already exists.
     */
    boolean registerArgument(SelectorArgument argument);
}
