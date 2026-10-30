package org.allaymc.server.command;

import lombok.RequiredArgsConstructor;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.server.command.tree.AllayCommandContext;
import org.allaymc.server.command.tree.node.BaseNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.*;

/**
 * {@code AllayCommandSuggester} computes console command candidates and the
 * source-text interval to replace at a cursor. It tokenizes only the prefix
 * before the clamped cursor, preserving source offsets while removing
 * double-quote delimiters from token values. Root completion advertises
 * accessible command names and aliases; parameter completion walks the existing
 * command tree with the server as sender.
 *
 * <p> Completed argument tokens are parsed through normal node matchers, and
 * terminal redirect callbacks run against the temporary context. Executors are
 * never invoked by this traversal, but redirect side effects remain possible.
 * Candidate extraction uses each child's network enumeration metadata or online
 * player names for target parameters. Matching filters candidates without case
 * sensitivity, while source offsets still refer to the original prefix so the
 * caller can replace the active word.
 *
 * @author IWareQ
 */
@RequiredArgsConstructor
public final class AllayCommandSuggester {
    /**
     * Server supplying console permissions, execution origin, and online-player
     * candidates.
     */
    private final Server server;

    /**
     * Returns completion candidates and the source interval ending at the
     * clamped cursor. {@code null} input becomes an empty line, and cursor
     * positions outside the line are clamped to its endpoints. Only the prefix
     * before that cursor participates in tokenization or tree resolution.
     *
     * <p> The last prefix character decides whether completion starts a new
     * word or replaces the last token. Token values omit double-quote
     * delimiters, while their offsets refer to the original prefix; the
     * returned replacement starts at the active token's recorded start or at
     * the cursor for an empty word.
     */
    public Completion complete(String line, int cursor) {
        var input = line == null ? "" : line;
        var safeCursor = Math.clamp(cursor, 0, input.length());
        var tokens = tokenize(input.substring(0, safeCursor));
        var trailingWhitespace = safeCursor > 0 && Character.isWhitespace(input.charAt(safeCursor - 1));
        var wordIndex = trailingWhitespace ? tokens.size() : Math.max(0, tokens.size() - 1);
        var currentToken = trailingWhitespace || tokens.isEmpty() ? null : tokens.getLast();
        var currentWord = currentToken == null ? "" : currentToken.value();
        var replaceStart = currentToken == null ? safeCursor : currentToken.start();

        var candidates = collectCandidates(tokens, wordIndex, currentWord);
        return new Completion(candidates, replaceStart, safeCursor);
    }

    /**
     * Selects command-name candidates for the root word or parameter candidates
     * after resolving a command path. Unknown commands, failed traversal, and
     * terminal resolved nodes return an empty list because they provide no
     * child parameter metadata to suggest.
     */
    private List<String> collectCandidates(List<Token> tokens, int wordIndex, String currentWord) {
        if (wordIndex <= 0) {
            return commandCandidates(normalizeRootToken(currentWord), currentWord.startsWith("/"));
        }

        // This defensive branch accepts empty tokens paired with a later word
        // index. Normal completion handles an empty prefix in the root-word branch,
        // before reaching this fallback command-name lookup.
        if (tokens.isEmpty()) {
            return commandCandidates("", false);
        }

        var command = Registries.COMMANDS.findCommand(normalizeRootToken(tokens.getFirst().value()));
        if (command == null) {
            return List.of();
        }

        var node = resolveNode(command, tokens, wordIndex);
        if (node == null || node.isLeaf()) {
            return List.of();
        }

        return nodeCandidates(node, currentWord);
    }

    /**
     * Returns accessible command names and aliases matching the supplied prefix
     * without case sensitivity. {@code includeSlash} prepends {@code /} to each
     * candidate so completion preserves a slash used in the root token.
     */
    private List<String> commandCandidates(String prefix, boolean includeSlash) {
        return commandNames().stream()
                .filter(name -> startsWithIgnoreCase(name, prefix))
                .map(name -> includeSlash ? "/" + name : name)
                .toList();
    }

    /**
     * Returns distinct primary names and aliases for commands whose
     * command-wide permissions the server satisfies. Primary commands are
     * sorted by name before insertion, and each command contributes its aliases
     * in their current list order.
     */
    private List<String> commandNames() {
        Set<String> names = new LinkedHashSet<>();
        Registries.COMMANDS.getContent().values().stream()
                .filter(command -> server.hasPermissions(command.getPermissions()))
                .sorted(Comparator.comparing(Command::getName))
                .forEach(command -> {
                    names.add(command.getName());
                    names.addAll(command.getAliases());
                });
        return List.copyOf(names);
    }

    /**
     * Returns the node reached by completed argument tokens before the word
     * being edited. A temporary context retains those tokens and starts at the
     * command root; normal matchers consume them and append results. The
     * traversal checks selected-node permissions against {@code server} and
     * returns {@code null} for a failed selection or denied node.
     *
     * <p> Terminal redirects run their callbacks and clear result slots before
     * traversal resumes from the root. The argument cursor is retained across
     * redirects, while terminal executors are left uncalled. Reusing matchers
     * preserves their accepted input forms, but their context changes and
     * redirect side effects are not rolled back by this completion path.
     */
    private CommandNode resolveNode(Command command, List<Token> tokens, int wordIndex) {
        var tree = command.getCommandTree();
        var args = new ArrayList<String>(Math.max(0, wordIndex - 1));
        for (int index = 1; index < wordIndex; index++) {
            args.add(tokens.get(index).value());
        }

        var context = new AllayCommandContext(command, server, args.toArray(String[]::new));
        CommandNode node = tree.getRoot();

        while (true) {
            node = resolveRedirect(node, context, tree.getRoot());
            if (node == null || !context.haveUnhandledArg()) {
                return node;
            }

            if (node.isLeaf()) {
                return node;
            }

            var nextNode = node.nextNode(context);
            if (nextNode == null || !nextNode.checkPermissions(server)) {
                return null;
            }

            context.addPermissions(nextNode.getPermissions());
            node = nextNode;
        }
    }

    /**
     * Runs terminal redirect callbacks until traversal reaches the root or a
     * nonredirect node. Each redirect clears parsed results while retaining
     * argument progress, so the restarted path reads the remaining completed
     * tokens with fresh result indices.
     */
    private CommandNode resolveRedirect(CommandNode node, AllayCommandContext context, CommandNode root) {
        while (node != null && node.isLeaf() && node.getOnRedirect() != null) {
            node.getOnRedirect().accept(context);
            // Redirected results describe the completed prefix, so discard them
            // before a root restart assigns result indices to the remaining tokens.
            context.clearResults();
            node = root;
        }
        return node;
    }

    /**
     * Collects candidates from accessible server parameter children and filters
     * them by the current word without case sensitivity. A {@code null} word
     * means an empty prefix; duplicates are removed and the resulting values
     * are sorted lexicographically.
     */
    private List<String> nodeCandidates(CommandNode node, String currentWord) {
        var prefix = currentWord == null ? "" : currentWord;
        Set<String> values = new LinkedHashSet<>();

        for (var leaf : node.getLeaves()) {
            if (!leaf.checkPermissions(server) || !(leaf instanceof BaseNode baseNode)) {
                continue;
            }

            collectParameterSuggestions(baseNode.toNetworkData(), values);
        }

        return values.stream()
                .filter(value -> startsWithIgnoreCase(value, prefix))
                .sorted(String::compareTo)
                .toList();
    }

    /**
     * Adds enumeration values when parameter metadata supplies a nonempty
     * enumeration. Otherwise, target parameters contribute online players'
     * origin names; other parameter types add no candidates to the caller's
     * existing set.
     */
    private void collectParameterSuggestions(CommandParamData param, Set<String> values) {
        var enumData = param.getEnumData();
        if (enumData != null && !enumData.values().isEmpty()) {
            values.addAll(enumData.values().keySet());
            return;
        }

        // Targets without an explicit enumeration can still offer currently online
        // player names; enumeration metadata takes precedence when the node provides it.
        if (CommandParam.TARGET.equals(param.getType())) {
            server.getPlayerManager().getPlayers().values().stream()
                    .map(Player::getOriginName)
                    .forEach(values::add);
        }
    }

    /**
     * Splits a prefix into token values and source offsets while treating
     * double-quoted whitespace as token content. Quote characters toggle
     * quoting and are omitted from values; the start offset is after an opening
     * quote when it begins a token.
     *
     * <p> Offsets remain in the original line even when quote removal changes
     * value length. End offsets are exclusive and include any closing quote
     * before a separator or the prefix end. An unfinished quoted token is still
     * emitted so interactive completion can operate before the user closes it;
     * escape sequences are not interpreted.
     */
    private List<Token> tokenize(String line) {
        var tokens = new ArrayList<Token>();
        var token = new StringBuilder();
        var start = -1;
        var quoted = false;

        for (int i = 0; i < line.length(); i++) {
            var ch = line.charAt(i);
            if (ch == '"') {
                if (start == -1) {
                    start = i + 1;
                }
                quoted = !quoted;
                continue;
            }

            if (!quoted && Character.isWhitespace(ch)) {
                if (start != -1) {
                    tokens.add(new Token(token.toString(), start, i));
                    token.setLength(0);
                    start = -1;
                }
                continue;
            }

            if (start == -1) {
                start = i;
            }
            token.append(ch);
        }

        if (start != -1) {
            tokens.add(new Token(token.toString(), start, line.length()));
        }
        return tokens;
    }

    /**
     * Returns a root token without one leading slash. {@code null} and empty
     * tokens become {@code ""}; later characters and case remain unchanged for
     * the registry's command lookup.
     */
    private String normalizeRootToken(String token) {
        if (token == null || token.isEmpty()) {
            return "";
        }
        // Console input may include the chat-style slash, while registry keys use
        // bare names; strip only that prefix so later name characters remain intact.
        return token.charAt(0) == '/' ? token.substring(1) : token;
    }

    /**
     * Returns whether a value starts with the prefix without case sensitivity.
     * A {@code null} or empty prefix accepts every supplied value, which
     * supports completion at a new word boundary.
     */
    private boolean startsWithIgnoreCase(String value, String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return true;
        }

        return value.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    /**
     * {@code Completion} carries candidates and an exclusive-end source
     * interval for the caller's replacement operation.
     *
     * @param candidates retained candidate list
     * @param replaceStart inclusive UTF-16 offset in the original line
     * @param replaceEnd exclusive UTF-16 offset in the original line
     */
    public record Completion(List<String> candidates, int replaceStart, int replaceEnd) {}

    /**
     * {@code Token} pairs a quote-stripped argument value with its positions in
     * the original input prefix.
     *
     * @param value token text without double-quote delimiters
     * @param start inclusive UTF-16 offset in the original prefix
     * @param end exclusive UTF-16 offset in the original prefix
     */
    private record Token(String value, int start, int end) {}
}
