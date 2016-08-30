package bitcoin.graph.app.data.control;

import com.squareup.okhttp.OkHttpClient;

import bitcoin.graph.app.data.cache.CacheControl;
import bitcoin.graph.app.domain.BitCoinDataSource;
import retrofit.Callback;
import retrofit.RestAdapter;
import bitcoin.graph.app.data.retrofit.BitCoinBlock;
import bitcoin.graph.app.data.retrofit.api.BitCoinApi;
import bitcoin.graph.app.domain.Constants;
import retrofit.client.OkClient;

public class BitCoinRetroDataSource implements BitCoinDataSource {

    @Override
    public void getBitCoinData(Callback<BitCoinBlock> callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.networkInterceptors().add(CacheControl.REWRITE_CACHE_CONTROL_INTERCEPTOR);
        RestAdapter adapter = new RestAdapter.Builder().setClient(new OkClient(okHttpClient)).setEndpoint(Constants.Api.BASE_URL).build();
        BitCoinApi bitCoinApi = adapter.create(BitCoinApi.class);
        bitCoinApi.getBitCoinData(callback);
    }
}
