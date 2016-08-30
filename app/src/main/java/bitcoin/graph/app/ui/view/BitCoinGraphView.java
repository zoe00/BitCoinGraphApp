package bitcoin.graph.app.ui.view;

import java.util.ArrayList;

import bitcoin.graph.app.data.retrofit.Point;

public interface BitCoinGraphView {

    void showProgress();

    void hideProgress();

    void showGraph(double[] xAxisValues, double[] yAxisValues);

    void showMessage(String message);

    void initRequest();

}
