package bitcoin.graph.app.ui.customviews;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class GraphViewTest {

    GraphView graphView;

    Context context = Mockito.mock(Context.class);

    @Before
    public void setUp() throws Exception {
        double[] xAxisValues = new double[]{3.0, 2.0, 1.0};
        double[] yAxisValues = new double[]{1.0, 2.0, 1.0};
        graphView = new GraphView(context, xAxisValues, yAxisValues);
    }

    @Test
    public void getMax_Should_ReturnMax() throws Exception {
        double[] values = new double[]{3.0, 2.0, 1.0};
        assertTrue(graphView.getMax(values) == 3.0);
    }

    @Test
    public void getMin_Should_ReturnMin() throws Exception {
        double[] values = new double[]{3.0, 2.0, 1.0};
        assertTrue(graphView.getMin(values) == 1.0);
    }

}