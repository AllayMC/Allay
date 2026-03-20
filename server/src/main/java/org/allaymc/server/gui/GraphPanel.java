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
 * @author daoge_cmd
 */
public final class GraphPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int PADDING = 10;
    private static final int LABEL_PADDING = 25;
    private static final int LEGEND_PADDING = 20;
    private static final int POINT_WIDTH = 4;
    private static final int NUMBER_Y_DIVISIONS = 20;

    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(40, 40, 40);
    private static final Color GRID_COLOR = Color.GRAY;

    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

    private static final int MAX_SCORE_THRESHOLD = 100;
    private static final int MIN_SCORE_THRESHOLD = 0;

    private final Color lineColor = new Color(44, 102, 230, 200);
    private final Color pointColor = new Color(44, 102, 230, 200);
    private final List<Integer> values = new ArrayList<>();
    private List<Series> seriesList = new ArrayList<>();
    private int minScore = MIN_SCORE_THRESHOLD;
    private int maxScore = MAX_SCORE_THRESHOLD;
    @Setter
    private boolean variableExtreme = true;
    @Setter
    private String xLabel = "";

    public GraphPanel() {
        setPreferredSize(new Dimension(700 - (PADDING * 2), 700 - (PADDING * 2)));
    }

    public void setValues(Collection<Integer> newValues) {
        seriesList.clear();
        values.clear();
        addValues(newValues);
        calculateExtremum();
    }

    public void addValues(Collection<Integer> newValues) {
        values.addAll(newValues);
        updateUI();
    }

    public void setSeries(List<Series> series) {
        this.seriesList = new ArrayList<>(series);
        this.values.clear();
        calculateExtremum();
        updateUI();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (!(graphics instanceof final Graphics2D g)) {
            graphics.drawString("Graphics is not Graphics2D, unable to render", 0, 0);
            return;
        }

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        var multiSeries = !seriesList.isEmpty();
        var length = multiSeries
                ? seriesList.stream().mapToInt(s -> s.values().size()).min().orElse(0)
                : values.size();
        var width = getWidth();
        var height = getHeight();
        var maxScore = this.maxScore;
        var minScore = this.minScore;
        var scoreRange = maxScore - minScore;
        var effectiveLabelPadding = LABEL_PADDING + (multiSeries ? LEGEND_PADDING : 0);

        // draw background
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

        // create hatch marks and grid lines for y-axis.
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

        // and for x-axis
        if (length > 1) {
            for (int i = 0; i < length; i++) {
                var x = i * (width - PADDING * 2 - LABEL_PADDING) / (length - 1) + PADDING + LABEL_PADDING;
                var y1 = height - PADDING - effectiveLabelPadding;
                var y2 = y1 - POINT_WIDTH;
                if ((i % ((int) ((length / 20.0)) + 1)) == 0) {
                    g.setColor(GRID_COLOR);
                    g.drawLine(x, height - PADDING - effectiveLabelPadding - 1 - POINT_WIDTH, x, PADDING);

                    g.setColor(Color.BLACK);
                }
                g.drawLine(x, y1, x, y2);
            }
        }

        // create x and y axes
        g.drawLine(PADDING + LABEL_PADDING, height - PADDING - effectiveLabelPadding, PADDING + LABEL_PADDING, PADDING);
        g.drawLine(PADDING + LABEL_PADDING, height - PADDING - effectiveLabelPadding, width - PADDING, height - PADDING - effectiveLabelPadding);

        g.setColor(TEXT_COLOR);
        var labelWidth = fontMetrics.stringWidth(xLabel);
        var labelX = ((PADDING + LABEL_PADDING) + (width - PADDING)) / 2;
        var labelY = height - PADDING - effectiveLabelPadding;
        g.drawString(xLabel, labelX - labelWidth / 2, labelY + fontHeight + 3);

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

            // Draw legend below xLabel
            var legendY = labelY + fontHeight + 3 + fontHeight;
            var legendRectSize = fontHeight - 4;
            var legendSpacing = 15;
            // Calculate total legend width
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

        minScore = Math.min(MIN_SCORE_THRESHOLD, minScore);
        maxScore = (int) Math.max(MAX_SCORE_THRESHOLD, maxScore * 1.2);
    }

    public record Series(String name, Color color, List<Integer> values) {}
}
