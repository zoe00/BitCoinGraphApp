package bitcoin.graph.app.ui.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;

import bitcoin.graph.app.data.retrofit.Point;
import bitcoin.graph.app.ui.view.BitCoinGraphView;

public class BitCoinGraphPresenter {

    @VisibleForTesting
    BitCoinGraphView bitCoinGraphView;

    public void onAttachView(@NonNull BitCoinGraphView view) {
        this.bitCoinGraphView = view;
    }

    public void onStart() {
        bitCoinGraphView.showProgress();
        initTracksRequest();
    }

    private void initTracksRequest() {
        bitCoinGraphView.initRequest();
    }

    public void onDetachView() {
        this.bitCoinGraphView = null;
    }

    public void onSuccessDataFetch(ArrayList<Point> graphData) {
        bitCoinGraphView.hideProgress();
        bitCoinGraphView.showGraph(getGraphValuesX(graphData), getGraphValuesY(graphData));
    }

    public void onFailureDataFetch() {
        bitCoinGraphView.hideProgress();
        bitCoinGraphView.showMessage("O Snap! Something went wrong.");
    }

    private double[] getGraphValuesY(ArrayList<Point> graphData) {
        if (graphData == null)
            return new double[0];
        double[] values = new double[graphData.size()];
        int i = 0;
        while (i < graphData.size()) {
            values[i] = graphData.get(i).y;
            i++;
        }
        return values;
    }

    private double[] getGraphValuesX(ArrayList<Point> graphData) {
        if (graphData == null)
            return new double[0];
        double[] values = new double[graphData.size()];
        int i = 0;
        while (i < graphData.size()) {
            values[i] = graphData.get(i).x;
            i++;
        }
        return values;
    }
}

