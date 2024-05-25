package org.allaymc.server.gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.jgoodies.forms.layout.FormLayout;
import io.netty.util.internal.logging.Slf4JLoggerFactory;
import net.minecrell.terminalconsole.TerminalConsoleAppender;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.server.player.PlayerInitializedEvent;
import org.allaymc.api.eventbus.event.server.player.PlayerQuitEvent;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Allay Project 2024/5/19
 *
 * @author daoge_cmd
 */
public final class Dashboard {
    public static final int RAM_VALUE_COUNT = 100;
    public static final int CHUNK_VALUE_COUNT = 100;
    public static final int ENTITY_VALUE_COUNT = 100;
    private static Dashboard INSTANCE;
    private static ImageIcon ICON;

    private JPanel rootPane;
    private JTabbedPane tabbedPane;
    private JPanel playerTab;
    private JPanel perfTab;
    private JTable playerTable;
    private JTable pluginTable;
    private JLabel onlinePlayerCount;
    private GraphPanel ramGraph;
    private GraphPanel chunkGraph;
    private GraphPanel entityGraph;
    private ConsolePanel consolePane;
    private JTextField cmdInput;
    private List<Integer> ramValues;
    private List<Integer> chunkValues;
    private List<Integer> entityValues;

    public static Dashboard getInstance() {
        if (INSTANCE == null) {
            FlatMacDarkLaf.setup();
            INSTANCE = new Dashboard();
        }
        return INSTANCE;
    }

    private Dashboard() {
        wrapSystemOutputStreams();
        JFrame frame = new JFrame("Dashboard");
        frame.setContentPane(rootPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        // Set icon
        URL image = Dashboard.class.getClassLoader().getResource("icon.png");
        if (image != null) {
            ICON = new ImageIcon(image);
            frame.setIconImage(ICON.getImage());
        }
        // Show the frame
        frame.setVisible(true);
        pluginTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON1) return;
                // Show the focused plugin's information
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem infoItem = new JMenuItem("Infomation");
                infoItem.addActionListener(unused -> {
                    if (pluginTable.getSelectedRow() == -1) return;
                    // Get the plugin
                    String pluginName = (String) pluginTable.getValueAt(pluginTable.getSelectedRow(), 0);
                    var pluginDescriptor = Server.getInstance().getPluginManager().getPlugin(pluginName).descriptor();
                    JOptionPane.showMessageDialog(null,
                            "Name: " + pluginDescriptor.getName() + "\n" +
                                    "Entrance: " + pluginDescriptor.getEntrance() + "\n" +
                                    "Order: " + pluginDescriptor.getOrder() + "\n" +
                                    "Description: " + pluginDescriptor.getDescription() + "\n" +
                                    "Version: " + pluginDescriptor.getVersion() + "\n" +
                                    "Authors: " + String.join(", ", pluginDescriptor.getAuthors()) + "\n" +
                                    "Dependencies: " + pluginDescriptor.getDependencies().stream().map(PluginDependency::name).collect(Collectors.joining(", ")) + "\n" +
                                    "Website: " + pluginDescriptor.getWebsite(),
                            "Plugin Information",
                            JOptionPane.INFORMATION_MESSAGE);
                });
                popupMenu.add(infoItem);
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        playerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON1) return;
                JPopupMenu popupMenu = new JPopupMenu();

                JMenuItem infoItem = new JMenuItem("Infomation");
                infoItem.addActionListener(unused -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    // Get the player
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getOnlinePlayerByName(playerName);
                    var pos = player.getLocation();
                    JOptionPane.showMessageDialog(null,
                            "Name: " + player.getOriginName() + "\n" +
                                    "Address: " + player.getClientSession().getSocketAddress().toString() + "\n" +
                                    "UUID: " + player.getUUID().toString() + "\n" +
                                    "Pos: (" + pos.x() + ", " + pos.y() + ", " + pos.z() + ")" + "\n" +
                                    "World: " + pos.dimension().getWorld().getWorldData().getName() + "\n" +
                                    "DimensionId: " + pos.dimension().getDimensionInfo().dimensionId(),
                            "Player Information",
                            JOptionPane.INFORMATION_MESSAGE);
                });
                popupMenu.add(infoItem);

                JMenuItem kickItem = new JMenuItem("Kick");
                kickItem.addActionListener(unused -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    // Get the player
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getOnlinePlayerByName(playerName);
                    player.disconnect(TrKeys.M_DISCONNECT_CLOSED);
                });
                popupMenu.add(kickItem);

                JMenuItem banItem = new JMenuItem("Ban");
                    banItem.addActionListener(unused -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    // Get the player
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getOnlinePlayerByName(playerName);
                    Server.getInstance().ban(player.getUUID().toString());
                });
                popupMenu.add(banItem);

                JMenuItem banIpItem = new JMenuItem("Ban");
                banIpItem.addActionListener(unused -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    // Get the player
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getOnlinePlayerByName(playerName);
                    Server.getInstance().banIP(AllayStringUtils.fastTwoPartSplit(player.getClientSession().getSocketAddress().toString().substring(1), ":", "")[0]);
                });
                popupMenu.add(banIpItem);

                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        consolePane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) return;
                JPopupMenu popupMenu = new JPopupMenu();

                JMenuItem changeFontSizeItem = new JMenuItem("Change Font Size");
                changeFontSizeItem.addActionListener(unused -> {
                    String input = JOptionPane.showInputDialog("Please input the font size you want:", consolePane.getFont().getSize());
                    if (input == null) return;
                    try {
                        int fontSize = Integer.parseInt(input);
                        consolePane.setFont(consolePane.getFont().deriveFont((float) fontSize));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid font size!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
                popupMenu.add(changeFontSizeItem);

                JMenuItem clearItem = new JMenuItem("Clear");
                clearItem.addActionListener(unused -> consolePane.setText(""));
                popupMenu.add(clearItem);

                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    private void wrapSystemOutputStreams() {
        org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();
        for (Appender appender : logger.getAppenders().values()) {
            // Prevents multiple appenders/double logging and removes harmless errors
            if (appender instanceof TerminalConsoleAppender) {
                logger.removeAppender(appender);
            }
        }
        var proxyOutputStream = createProxyOutputStream();
        // Override the system output streams
        System.setOut(new PrintStream(proxyOutputStream, true));
        System.setErr(new PrintStream(proxyOutputStream, true));
    }

    private @NotNull OutputStream createProxyOutputStream() {
        var originalOutputStream = System.out;
        return new OutputStream() {
            @Override
            public void write(final int b) {
                originalOutputStream.write(b);
                appendTextToConsole(String.valueOf((char) b));
            }

            @Override
            public void write(byte @NonNull [] b, int off, int len) {
                originalOutputStream.write(b, off, len);
                appendTextToConsole(new String(b, off, len));
            }

            @Override
            public void write(byte @NonNull[] b) throws IOException {
                write(b, 0, b.length);
            }
        };
    }

    public void serverStarted() {
        Runnable guiUpdateTask = () -> {
            // Update ram graph
            final long freeMemory = Runtime.getRuntime().freeMemory();
            final long totalMemory = Runtime.getRuntime().totalMemory();
            final int freePercent = (int) (freeMemory * 100.0 / totalMemory + 0.5);
            ramValues.add(100 - freePercent);
            ramGraph.setXLabel("Usage: " + String.format("%,d", (totalMemory - freeMemory) / MEGABYTE) + "mb (" + freePercent + "% free)");
            // Trim the list
            int k = ramValues.size();
            if (k > RAM_VALUE_COUNT)
                ramValues.subList(0, k - RAM_VALUE_COUNT).clear();
            // Update the graph
            ramGraph.setValues(ramValues);

            // Update chunk graph
            var loadedChunkCount = Server.getInstance()
                    .getWorldPool()
                    .getWorlds()
                    .values()
                    .stream()
                    .mapToInt(world ->
                            world.getDimensions()
                                    .values()
                                    .stream()
                                    .mapToInt(dimension -> dimension.getChunkService().getLoadedChunks().size())
                                    .sum()
                    )
                    .sum();
            chunkValues.add(loadedChunkCount);
            chunkGraph.setXLabel("Loaded Chunk Count: " + loadedChunkCount);
            // Trim the list
            k = chunkValues.size();
            if (k > CHUNK_VALUE_COUNT)
                chunkValues.subList(0, k - CHUNK_VALUE_COUNT).clear();
            // Update the graph
            chunkGraph.setValues(chunkValues);

            // Update entity graph
            var loadedEntityCount = Server.getInstance()
                    .getWorldPool()
                    .getWorlds()
                    .values()
                    .stream()
                    .mapToInt(world ->
                            world.getDimensions()
                                    .values()
                                    .stream()
                                    .mapToInt(org.allaymc.api.world.Dimension::getEntityCount)
                                    .sum()
                    )
                    .sum();
            entityValues.add(loadedEntityCount);
            entityGraph.setXLabel("Loaded Entity Count: " + loadedEntityCount);
            // Trim the list
            k = entityValues.size();
            if (k > ENTITY_VALUE_COUNT)
                entityValues.subList(0, k - ENTITY_VALUE_COUNT).clear();
            // Update the graph
            entityGraph.setValues(entityValues);
        };

        // SwingUtilities.invokeLater is called so that we don't run into threading issues with the GUI
        Server.getInstance().getScheduler().scheduleRepeating(
                Server.getInstance(),
                () -> {
                    SwingUtilities.invokeLater(guiUpdateTask);
                    return true;},
                20);

        var server = Server.getInstance();
        server.getEventBus().registerListener(this);
        // TODO: Update the table if any plugin disabled or enabled
        updatePluginTable();

        cmdInput.addActionListener(new CommandListener());
        SwingUtilities.invokeLater(() -> {
            cmdInput.setEnabled(true);
            cmdInput.requestFocusInWindow();
        });
    }

    @EventHandler
    private void onPlayerJoin(PlayerInitializedEvent event) {
        updateOnlinePlayerCount();
        updateOnlinePlayerTable();
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        updateOnlinePlayerCount();
        updateOnlinePlayerTable();
    }

    private void updateOnlinePlayerCount() {
        SwingUtilities.invokeLater(() -> onlinePlayerCount.setText("Online: " + Server.getInstance().getOnlinePlayerCount()));
    }

    private void updateOnlinePlayerTable() {
        var title = new String[] {"Name", "Address", "UUID"};
        var players = Server.getInstance().getOnlinePlayers().values();
        String[][] data;
        if (players.isEmpty()) {
            data = new String[3][0];
        } else {
            data = new String[3][players.size() - 1];
            int row = 0;
            for (var player : players) {
                data[row] = new String[]{
                        player.getOriginName(),
                        player.getClientSession().getSocketAddress().toString(),
                        player.getUUID().toString()
                };
                row++;
            }
        }
        var model = new UneditableDefaultTableModel(data, title);
        SwingUtilities.invokeLater(() -> {
            playerTable.setModel(model);
            playerTable.getTableHeader().setReorderingAllowed(false);
        });
    }

    private void updatePluginTable() {
        var title = new String[] {"Name", "Description", "Version", "Author"};
        var plugins = Server.getInstance().getPluginManager().getPlugins().values();
        var data = new String[4][plugins.size() - 1];
        int row = 0;
        for (var plugin : plugins) {
            var descriptor = plugin.descriptor();
            data[row] = new String[] {
                    descriptor.getName(),
                    descriptor.getDescription().isBlank() ? "N/A" : descriptor.getDescription(),
                    descriptor.getVersion(),
                    String.join(", ", descriptor.getAuthors())
            };
            row++;
        }
        var model = new UneditableDefaultTableModel(data, title);
        SwingUtilities.invokeLater(() -> {
            pluginTable.setModel(model);
            pluginTable.getTableHeader().setReorderingAllowed(false);
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPane = new JPanel();
        rootPane.setLayout(new GridLayoutManager(1, 1, new Insets(8, 8, 8, 8), -1, -1));
        tabbedPane = new JTabbedPane();
        rootPane.add(tabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        playerTab = new JPanel();
        playerTab.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane.addTab("Players", playerTab);
        final JLabel label1 = new JLabel();
        label1.setEnabled(true);
        label1.setHorizontalAlignment(2);
        label1.setText("Online: ");
        playerTab.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        playerTab.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        playerTable = new JTable();
        playerTable.setAutoCreateRowSorter(true);
        scrollPane1.setViewportView(playerTable);
        perfTab = new JPanel();
        perfTab.setLayout(new FormLayout("", ""));
        tabbedPane.addTab("Performance", perfTab);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPane;
    }

    private static final long MEGABYTE = 1024L * 1024L;

    private void createUIComponents() {
        // Init the three graph
        ramGraph = new GraphPanel();
        ramValues = new ArrayList<>();
        // Set the ram graph to 0
        for (int i = 0; i < RAM_VALUE_COUNT; i++) {
            ramValues.add(0);
        }
        ramGraph.setValues(ramValues);

        chunkGraph = new GraphPanel();
        chunkValues = new ArrayList<>();
        // Set the chunk graph to 0
        for (int i = 0; i < CHUNK_VALUE_COUNT; i++) {
            chunkValues.add(0);
        }
        chunkGraph.setValues(chunkValues);

        entityGraph = new GraphPanel();
        entityValues = new ArrayList<>();
        // Set the entity graph to 0
        for (int i = 0; i < ENTITY_VALUE_COUNT; i++) {
            entityValues.add(0);
        }
        entityGraph.setValues(entityValues);

        // Init the console
        consolePane = new ConsolePanel();
        consolePane.setBackground(Color.BLACK);
        consolePane.setEditable(false);
    }

    private static class UneditableDefaultTableModel extends DefaultTableModel {
        public UneditableDefaultTableModel(String[][] data, String[] title) {super(data, title);}

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public void appendTextToConsole(final String text) {
        SwingUtilities.invokeLater(() -> {
            consolePane.appendANSI(text);
            Document doc = consolePane.getDocument();
            consolePane.setCaretPosition(doc.getLength());
        });
    }


    private class CommandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = cmdInput.getText();
            cmdInput.setText(""); // clear the input
            appendTextToConsole(cmd + "\n"); // show what was run in the console
            var server = Server.getInstance();
            server.getScheduler().runLater(server, () -> server.getCommandRegistry().execute(Server.getInstance(), cmd));
        }
    }
}
