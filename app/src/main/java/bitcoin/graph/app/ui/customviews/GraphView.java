package bitcoin.graph.app.ui.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.View;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Custom GraphView of type Histogram
 */
@SuppressLint("ViewConstructor")
public class GraphView extends View {

    /*
    Spacing for labels on the bottom for y axis and left for x axis.
     */
    private static final float SPACING_FOR_LABELS = 45;
    private static final float Y_LABELS_TEXT_SIZE = 25f;
    private static final float X_LABELS_TEXT_SIZE = 25f;
    private static final int TOTAL_LABELS = 6;
    private static final int LABELS_COLOR = Color.BLACK;
    private static final int GRAPH_BARS_COLOR = Color.rgb(73, 43, 111);

    private double[] yAxisValues;
    private double[] xAxisValues;
    private String[] xLabels;
    private String[] yLabels;
    private double[] points;
    private Paint paint;

    public GraphView(Context context, double[] xValues, double[] yValues) {
        super(context);
        if (yValues== null)
            this.yAxisValues = new double[0];
        else
            this.yAxisValues = yValues;
        if (xValues == null)
            this.xAxisValues = new double[0];
        else
            this.xAxisValues = xValues;
        this.xLabels = new String[0];
        this.yLabels = new String[0];
        paint = new Paint();
        points = new double[this.yAxisValues.length];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float startX = SPACING_FOR_LABELS;
        float height = getHeight();
        float width = getWidth();
        double max = getMax(yAxisValues);
        double min = getMin(yAxisValues);
        double diff = max - min;
        float graphHeight = height - startX;
        float graphWidth = width - startX;
        double currentPoint, ratio, point;
        float left, top, right, bottom;
        float barWidth = (width - (SPACING_FOR_LABELS)) / yAxisValues.length;

        ////////// Draw bar
        paint.setColor(GRAPH_BARS_COLOR);
        for (int i = 0; i < yAxisValues.length; i++) {
            currentPoint = yAxisValues[i];
            ratio = currentPoint / diff;
            point = (graphHeight - (4 * startX)) * ratio;
            right = ((i * barWidth) + startX) + (barWidth - 1);
            left = (i * barWidth) + startX;
            bottom = height - SPACING_FOR_LABELS;
            top = (float) ((startX - point) + (graphHeight));
            canvas.drawRect(left, top, right, bottom, paint);
            points[i] = point;
        }

        ////////// Draw y-axis label

        float y, actual, previous = 0;
        paint.setColor(LABELS_COLOR);
        paint.setTextSize(Y_LABELS_TEXT_SIZE);
        yLabels = getYLabels();

        for (int i = 0; i < yLabels.length; i++) {
            y = graphHeight - Float.parseFloat(yLabels[i]) + SPACING_FOR_LABELS;
            actual = Float.parseFloat(yLabels[i]);
            if(actual > previous + 40)
                canvas.drawText(yLabels[i], 0, y, paint);
            previous = actual;
        }

        ////////// Draw x-axis labels

        this.xLabels = getXLabels();
        paint.setColor(LABELS_COLOR);
        paint.setTextSize(X_LABELS_TEXT_SIZE);
        float x;
        for (int i = 0; i < xLabels.length; i++) {
            x = ((graphWidth / xLabels.length-1) * i) + startX;
            canvas.drawText(xLabels[i], x, height - (SPACING_FOR_LABELS / 2), paint);
        }
    }

    /**
     * @return string array of labels for x axis
     */
    private String[] getXLabels() {
        String[] labels = new String[TOTAL_LABELS];
        int gap = xAxisValues.length / TOTAL_LABELS;
        int nextIndex;
        for (int i = 0; i < TOTAL_LABELS; i++) {
            nextIndex = (i + 1) * gap;
            if (nextIndex == xAxisValues.length)
                nextIndex = xAxisValues.length - 1;
            labels[i] = formatDate((long) xAxisValues[nextIndex]);
        }
        return labels;
    }

    /**
     *
     * @return array with labels to be drawn on y axis according to input
     */
    private String[] getYLabels() {
        if(points.length<1)
            return new String[]{};
        Arrays.sort(points);
        String[] labels = new String[TOTAL_LABELS];
        int gap = points.length / TOTAL_LABELS;
        int nextIndex;
        for (int i = 0; i < TOTAL_LABELS; i++) {
            nextIndex = (i + 1) * gap;
            if (nextIndex == points.length)
                nextIndex = points.length - 1;
            Log.e("next index", nextIndex + ";;" );
            int point = (int) points[nextIndex];
            labels[i] = String.valueOf(point);
        }
        return labels;
    }

    private String formatDate(long timestamp) {
        Date date = new Date(new Timestamp(timestamp * 1000).getTime());
        Format formatter = new SimpleDateFormat("MMM yyyy");
        return formatter.format(date);
    }

    /**
     *  Calculates max number in the graph data.
     * @return maximum
     */
    @VisibleForTesting
    public double getMax(double[] values) {
        double largest = Integer.MIN_VALUE;
        for (double value : values)
            if (value > largest)
                largest = value;
        return largest;
    }

    /**
     *  Calculates min number in the graph data.
     * @return minimum
     */
    @VisibleForTesting
    public double getMin(double[] values) {
        double smallest = Integer.MAX_VALUE;
        for (double value : values)
            if (value < smallest)
                smallest = value;
        return smallest;
    }

}
