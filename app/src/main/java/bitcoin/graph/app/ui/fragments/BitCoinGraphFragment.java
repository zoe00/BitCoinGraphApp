package bitcoin.graph.app.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import bitcoin.graph.app.R;
import bitcoin.graph.app.data.control.BitCoinRetroDataSource;
import bitcoin.graph.app.data.retrofit.BitCoinBlock;
import bitcoin.graph.app.domain.action.GetBitCoinAction;
import bitcoin.graph.app.ui.customviews.GraphView;
import bitcoin.graph.app.ui.presenter.BitCoinGraphPresenter;
import bitcoin.graph.app.ui.view.BitCoinGraphView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BitCoinGraphFragment extends Fragment implements BitCoinGraphView {

    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;

    @InjectView(R.id.container)
    RelativeLayout containerView;

    private BitCoinGraphPresenter bitCoinGraphPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        bitCoinGraphPresenter = new BitCoinGraphPresenter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bitCoinGraphPresenter.onAttachView(this);
    }

    @Override
    public void onDestroyView() {
        bitCoinGraphPresenter.onDetachView();
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        bitCoinGraphPresenter.onStart();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showGraph(double[] xAxisValues, double[] yAxisValues) {
        GraphView graphView = new GraphView(getActivity(), xAxisValues, yAxisValues);
        containerView.addView(graphView);
    }

    @Override
    public void showMessage(String message) {
        if (getView() == null)
            return;
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void initRequest() {
        getBitCoinAction().execute(getBitCoinCallback());
    }

    private Callback<BitCoinBlock> getBitCoinCallback() {
        return new Callback<BitCoinBlock>() {
            @Override
            public void success(BitCoinBlock bitCoinBlock, Response response) {
                bitCoinGraphPresenter.onSuccessDataFetch(bitCoinBlock.values);
            }

            @Override
            public void failure(RetrofitError error) {
                bitCoinGraphPresenter.onFailureDataFetch();
            }
        };
    }

    private GetBitCoinAction getBitCoinAction() {
        return new GetBitCoinAction(new BitCoinRetroDataSource());
    }
}