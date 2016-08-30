package bitcoin.graph.app.ui.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import bitcoin.graph.app.data.retrofit.Point;
import bitcoin.graph.app.ui.view.BitCoinGraphView;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BitCoinGraphPresenterTest {

    @Mock
    private BitCoinGraphView view;

    private BitCoinGraphPresenter bitCoinGraphPresenter;

    @Before
    public void setUp() throws Exception {
        bitCoinGraphPresenter = new BitCoinGraphPresenter();
    }

    @Test
    public void onAttachView_Should_AttachView_WHEN_ViewIsNotNull() throws Exception {
        bitCoinGraphPresenter.onAttachView(view);
        assertTrue(bitCoinGraphPresenter.bitCoinGraphView != null);
    }

    @Test
    public void onDetachView_Should_DetachView() throws Exception {
        bitCoinGraphPresenter.onDetachView();
        assertTrue(bitCoinGraphPresenter.bitCoinGraphView == null);
    }

    @Test
    public void onStart_Should_PerfromCorrectly() throws Exception {
        bitCoinGraphPresenter.onAttachView(view);
        bitCoinGraphPresenter.onStart();
        verify(bitCoinGraphPresenter.bitCoinGraphView).showProgress();
        verify(bitCoinGraphPresenter.bitCoinGraphView).initRequest();
    }

    @Test
    public void onFailureDataFetch_Should_PerformCorrectly() throws Exception {
        bitCoinGraphPresenter.onAttachView(view);
        bitCoinGraphPresenter.onFailureDataFetch();
        verify(bitCoinGraphPresenter.bitCoinGraphView).hideProgress();
        verify(bitCoinGraphPresenter.bitCoinGraphView).showMessage("O Snap! Something went wrong.");
    }

    @Test
    public void onSuccessDataFetch_Should_PerformCorrectly() throws Exception {
        bitCoinGraphPresenter.onAttachView(view);
        ArrayList<Point> arrayList = new ArrayList<>();
        bitCoinGraphPresenter.onSuccessDataFetch(arrayList);
        verify(bitCoinGraphPresenter.bitCoinGraphView).hideProgress();
        verify(bitCoinGraphPresenter.bitCoinGraphView).showGraph(new double[]{}, new double[]{});
    }

}