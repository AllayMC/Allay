/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.allaymc.server.gui;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {@code GraphPanel} draws integer histories as one sampled line or several named dimension series.
 * Callers update and render it on the Swing event thread. Single-series values are copied into
 * internal storage; multi-series replacement copies the outer list but retains each series and its
 * value list.
 *
 * <p>
 * Multi-series drawing uses the shortest series length so each plotted sample index exists in every
 * line. Dynamic scaling examines all retained values, including longer tails that are not drawn,
 * and keeps a zero-or-lower minimum with at least a hundred-unit upper bound. {@code setValues} and
 * {@code setSeries} recompute that range, while {@code addValues} only appends and refreshes the
 * UI. Callers retaining mutable series lists must coordinate their later changes with painting.
 *
 * @author daoge_cmd
 */
public final class GraphPanel extends JPanel {

    /**
     * Swing serialization compatibility identifier.
     */
    @Serial
    // serialVersionUID identifies serialized Swing state. PADDING, LABEL_PADDING,
    // LEGEND_PADDING, POINT_WIDTH, and NUMBER_Y_DIVISIONS determine the plot
    // geometry; TEXT_COLOR, BACKGROUND_COLOR, GRID_COLOR, and GRAPH_STROKE
    // determine presentation, while MAX_SCORE_THRESHOLD and MIN_SCORE_THRESHOLD
    // anchor the automatically calculated numeric range.
    private static final long serialVersionUID = 1L;

    /**
     * Outer plot padding in pixels.
     */
    private static final int PADDING = 10;
    /**
     * Axis label padding in pixels.
     */
    private static final int LABEL_PADDING = 25;
    /**
     * Additional multi-series legend padding in pixels.
     */
    private static final int LEGEND_PADDING = 20;
    /**
     * Sample dot width in pixels.
     */
    private static final int POINT_WIDTH = 4;
    /**
     * Number of vertical scale divisions.
     */
    private static final int NUMBER_Y_DIVISIONS = 20;

    /**
     * Axis and legend text color.
     */
    private static final Color TEXT_COLOR = Color.WHITE;
    /**
     * Plot interior background color.
     */
    private static final Color BACKGROUND_COLOR = new Color(40, 40, 40);
    /**
     * Plot grid line color.
     */
    private static final Color GRID_COLOR = Color.GRAY;

    /**
     * Stroke used for sampled lines.
     */
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

    /**
     * Baseline upper scale bound.
     */
    private static final int MAX_SCORE_THRESHOLD = 100;
    /**
     * Baseline lower scale bound.
     */
    private static final int MIN_SCORE_THRESHOLD = 0;

    /**
     * Single-series line stroke color.
     */
    private final Color lineColor = new Color(44, 102, 230, 200);
    /**
     * Single-series sample dot color.
     */
    private final Color pointColor = new Color(44, 102, 230, 200);
    /**
     * Retained single-series integer samples.
     */
    private final List<Integer> values = new ArrayList<>();
    /**
     * {@code seriesList} retains a copied outer list of series. Each record and its mutable sample
     * list remain shared with the supplied series.
     */
    private List<Series> seriesList = new ArrayList<>();
    /**
     * Current lower plotted scale bound.
     */
    // minScore and maxScore define the shared vertical scale for every rendered
    // series. variableExtreme controls whether setValues and setSeries recalculate
    // both endpoints; addValues leaves the current endpoints unchanged.
    private int minScore = MIN_SCORE_THRESHOLD;
    /**
     * Current upper plotted scale bound.
     */
    private int maxScore = MAX_SCORE_THRESHOLD;
    /**
     * Whether replacement recomputes scale extrema.
     */
    @Setter
    private boolean variableExtreme = true;
    /**
     * Caption displayed beneath the horizontal axis.
     */
    @Setter
    private String xLabel = "";

    /**
     * Creates an empty graph panel with its preferred dimensions and baseline scale.
     */
    public GraphPanel() {
        setPreferredSize(new Dimension(700 - (PADDING * 2), 700 - (PADDING * 2)));
    }

    /**
     * Replaces the single-series samples, clears multi-series mode, and recalculates enabled
     * extrema. Samples are copied into internal storage, and callers perform the mutation on the
     * Swing event thread.
     */
    public void setValues(Collection<Integer> newValues) {
        seriesList.clear();
        values.clear();
        addValues(newValues);
        calculateExtremum();
    }

    /**
     * Appends the supplied samples to internal single-series storage and refreshes the UI. This
     * operation does not recalculate extrema or clear active multi-series data, so callers choose
     * the appropriate replacement operation when needed.
     */
    public void addValues(Collection<Integer> newValues) {
        values.addAll(newValues);
        updateUI();
    }

    /**
     * Replaces the outer series list, clears single-series samples, and recalculates enabled
     * extrema. Series records and their value lists remain shared, so callers coordinate later list
     * mutation with Swing rendering.
     */
    public void setSeries(List<Series> series) {
        this.seriesList = new ArrayList<>(series);
        this.values.clear();
        calculateExtremum();
        updateUI();
    }

    /**
     * Draws axes, labels, and sample lines for the current single-series or multi-series state. A
     * non-{@code Graphics2D} context displays a diagnostic string instead of the graph.
     *
     * <p>
     * Multi-series rendering uses only the common prefix length so every series provides the
     * indexed sample. The scale can still include unseen longer tails because extrema calculation
     * examines all samples. Zero range or fewer than two common points leaves axes without
     * connected curves, avoiding division by zero. Multi-series output includes a legend, while
     * dense single-series output suppresses dots to reduce crowding.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (!(graphics instanceof final Graphics2D g)) {
            // A basic graphics context lacks the rendering controls used below;
            // preserve a visible diagnostic instead of attempting an incompatible cast.
            graphics.drawString("Graphics is not Graphics2D, unable to render", 0, 0);
            return;
        }

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        var multiSeries = !seriesList.isEmpty();
        // Different series can retain different sample counts. Draw their common
        // prefix so each shared horizontal index has a value in every curve.
        var length = multiSeries
                ? seriesList.stream().mapToInt(s -> s.values().size()).min().orElse(0)
                : values.size();
        var width = getWidth();
        var height = getHeight();
        var maxScore = this.maxScore;
        var minScore = this.minScore;
        var scoreRange = maxScore - minScore;
        var effectiveLabelPadding = LABEL_PADDING + (multiSeries ? LEGEND_PADDING : 0);

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(
                PADDING + LABEL_PADDING,
                PADDING,
                width - (2 * PADDING) - LABEL_PADDING,
                height - 2 * PADDING - effectiveLabelPadding
        );
        g.setColor(Color.BLACK);

        var fontMetrics = g.getFontMetrics();
        var fontHeight = fontMetrics.getHeight();

        for (int i = 0; i < NUMBER_Y_DIVISIONS + 1; i++) {
            var x1 = PADDING + LABEL_PADDING;
            var x2 = POINT_WIDTH + PADDING + LABEL_PADDING;
            var y = height - ((i * (height - PADDING * 2 - effectiveLabelPadding)) / NUMBER_Y_DIVISIONS + PADDING + effectiveLabelPadding);
            if (length > 0) {
                g.setColor(GRID_COLOR);
                g.drawLine(PADDING + LABEL_PADDING + 1 + POINT_WIDTH, y, width - PADDING, y);

                g.setColor(TEXT_COLOR);
                var tickValue = minScore + ((scoreRange * i) / NUMBER_Y_DIVISIONS);
                var yLabel = tickValue + "";
                var labelWidth = fontMetrics.stringWidth(yLabel);
                g.drawString(yLabel, x1 - labelWidth - 5, y + (fontHeight / 2) - 3);
            }
            g.drawLine(x1, y, x2, y);
        }

        if (length > 1) {
            for (int i = 0; i < length; i++) {
                var x = i * (width - PADDING * 2 - LABEL_PADDING) / (length - 1) + PADDING + LABEL_PADDING;
                var y1 = height - PADDING - effectiveLabelPadding;
                var y2 = y1 - POINT_WIDTH;
                // Thin vertical grid lines as sample history grows.
                if ((i % ((int) ((length / 20.0)) + 1)) == 0) {
                    g.setColor(GRID_COLOR);
                    g.drawLine(x, height - PADDING - effectiveLabelPadding - 1 - POINT_WIDTH, x, PADDING);

                    g.setColor(Color.BLACK);
                }
                g.drawLine(x, y1, x, y2);
            }
        }

        g.drawLine(PADDING + LABEL_PADDING, height - PADDING - effectiveLabelPadding, PADDING + LABEL_PADDING, PADDING);
        g.drawLine(PADDING + LABEL_PADDING, height - PADDING - effectiveLabelPadding, width - PADDING, height - PADDING - effectiveLabelPadding);

        g.setColor(TEXT_COLOR);
        var labelWidth = fontMetrics.stringWidth(xLabel);
        var labelX = ((PADDING + LABEL_PADDING) + (width - PADDING)) / 2;
        var labelY = height - PADDING - effectiveLabelPadding;
        g.drawString(xLabel, labelX - labelWidth / 2, labelY + fontHeight + 3);

        // Connected curves require two points and a nonzero scale denominator.
        if (length <= 1 || scoreRange == 0) return;

        var xScale = ((double) width - (2 * PADDING) - LABEL_PADDING) / (length - 1);
        var yScale = ((double) height - 2 * PADDING - effectiveLabelPadding) / scoreRange;

        if (multiSeries) {
            var oldStroke = g.getStroke();
            g.setStroke(GRAPH_STROKE);

            for (var series : seriesList) {
                g.setColor(series.color());
                List<Point> graphPoints = new ArrayList<>(length);
                for (int i = 0; i < length; i++) {
                    var x1 = (int) (i * xScale + PADDING + LABEL_PADDING);
                    var y1 = (int) ((maxScore - series.values().get(i)) * yScale + PADDING);
                    graphPoints.add(new Point(x1, y1));
                }
                for (int i = 0; i < graphPoints.size() - 1; i++) {
                    var x1 = graphPoints.get(i).x;
                    var y1 = graphPoints.get(i).y;
                    var x2 = graphPoints.get(i + 1).x;
                    var y2 = graphPoints.get(i + 1).y;
                    g.drawLine(x1, y1, x2, y2);
                }
            }

            g.setStroke(oldStroke);

            var legendY = labelY + fontHeight + 3 + fontHeight;
            var legendRectSize = fontHeight - 4;
            // Separate adjacent legend names with horizontal padding.
            var legendSpacing = 15;
            var totalLegendWidth = 0;
            for (var series : seriesList) {
                totalLegendWidth += legendRectSize + 4 + fontMetrics.stringWidth(series.name()) + legendSpacing;
            }
            totalLegendWidth -= legendSpacing;
            var legendX = ((PADDING + LABEL_PADDING) + (width - PADDING)) / 2 - totalLegendWidth / 2;

            for (var series : seriesList) {
                g.setColor(series.color());
                g.fillRect(legendX, legendY - legendRectSize, legendRectSize, legendRectSize);
                g.setColor(TEXT_COLOR);
                legendX += legendRectSize + 4;
                g.drawString(series.name(), legendX, legendY);
                legendX += fontMetrics.stringWidth(series.name()) + legendSpacing;
            }
        } else {
            var oldStroke = g.getStroke();
            g.setColor(lineColor);
            g.setStroke(GRAPH_STROKE);

            List<Point> graphPoints = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                var x1 = (int) (i * xScale + PADDING + LABEL_PADDING);
                var y1 = (int) ((maxScore - values.get(i)) * yScale + PADDING);
                graphPoints.add(new Point(x1, y1));
            }

            for (int i = 0; i < graphPoints.size() - 1; i++) {
                var x1 = graphPoints.get(i).x;
                var y1 = graphPoints.get(i).y;
                var x2 = graphPoints.get(i + 1).x;
                var y2 = graphPoints.get(i + 1).y;
                g.drawLine(x1, y1, x2, y2);
            }

            // Panel width limits dot density to reduce visual crowding.
            var drawDots = width > (length * POINT_WIDTH);
            if (drawDots) {
                g.setStroke(oldStroke);
                g.setColor(pointColor);
                for (Point graphPoint : graphPoints) {
                    var x = graphPoint.x - POINT_WIDTH / 2;
                    var y = graphPoint.y - POINT_WIDTH / 2;
                    //noinspection SuspiciousNameCombination
                    g.fillOval(x, y, POINT_WIDTH, POINT_WIDTH);
                }
            }
        }
    }

    /**
     * Recomputes scale endpoints from all retained series samples when dynamic scaling is enabled.
     * Multi-series data takes precedence over single-series values, including longer tails omitted
     * from the common plotted prefix.
     *
     * <p>
     * The minimum includes zero and the maximum includes a hundred-unit baseline, keeping empty or
     * small histories on a usable default scale. Positive maxima receive twenty percent headroom
     * before integer conversion. Disabling dynamic scaling preserves existing endpoints, and adding
     * samples alone does not call this calculation.
     */
    private void calculateExtremum() {
        if (!variableExtreme) return;

        minScore = Integer.MAX_VALUE;
        maxScore = Integer.MIN_VALUE;

        if (!seriesList.isEmpty()) {
            for (var series : seriesList) {
                for (int value : series.values()) {
                    minScore = Math.min(minScore, value);
                    maxScore = Math.max(maxScore, value);
                }
            }
        } else {
            for (int value : values) {
                minScore = Math.min(minScore, value);
                maxScore = Math.max(maxScore, value);
            }
        }

        // Include the zero baseline even when all samples are positive.
        minScore = Math.min(MIN_SCORE_THRESHOLD, minScore);
        // Add twenty percent headroom, then truncate to an integer bound.
        maxScore = (int) Math.max(MAX_SCORE_THRESHOLD, maxScore * 1.2);
    }

    /**
     * {@code Series} represents one named colored sample history drawn by a multi-series graph.
     *
     * @param name series label displayed in the legend
     * @param color series line and legend color
     * @param values retained sample list; callers coordinate later mutation with rendering because
     * construction and accessors do not copy it
     */
    public record Series(String name, Color color, List<Integer> values) {}
}
