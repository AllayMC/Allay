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
 * @author IWareQ
 */
@RequiredArgsConstructor
public final class AllayCommandSuggester {
    private final Server server;

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

    private List<String> collectCandidates(List<Token> tokens, int wordIndex, String currentWord) {
        if (wordIndex <= 0) {
            return commandCandidates(normalizeRootToken(currentWord), currentWord.startsWith("/"));
        }

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

    private List<String> commandCandidates(String prefix, boolean includeSlash) {
        return commandNames().stream()
                .filter(name -> startsWithIgnoreCase(name, prefix))
                .map(name -> includeSlash ? "/" + name : name)
                .toList();
    }

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

    private CommandNode resolveRedirect(CommandNode node, AllayCommandContext context, CommandNode root) {
        while (node != null && node.isLeaf() && node.getOnRedirect() != null) {
            node.getOnRedirect().accept(context);
            context.clearResults();
            node = root;
        }
        return node;
    }

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

    private void collectParameterSuggestions(CommandParamData param, Set<String> values) {
        var enumData = param.getEnumData();
        if (enumData != null && !enumData.values().isEmpty()) {
            values.addAll(enumData.values().keySet());
            return;
        }

        if (CommandParam.TARGET.equals(param.getType())) {
            server.getPlayerManager().getPlayers().values().stream()
                    .map(Player::getOriginName)
                    .forEach(values::add);
        }
    }

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

    private String normalizeRootToken(String token) {
        if (token == null || token.isEmpty()) {
            return "";
        }
        return token.charAt(0) == '/' ? token.substring(1) : token;
    }

    private boolean startsWithIgnoreCase(String value, String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return true;
        }

        return value.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    public record Completion(List<String> candidates, int replaceStart, int replaceEnd) {}

    private record Token(String value, int start, int end) {}
}
