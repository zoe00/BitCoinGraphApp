package bitcoin.graph.app.domain;

import retrofit.Callback;
import bitcoin.graph.app.data.retrofit.BitCoinBlock;

public interface BitCoinDataSource {

    void getBitCoinData(Callback<BitCoinBlock> callback);

}
