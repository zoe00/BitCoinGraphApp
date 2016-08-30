package bitcoin.graph.app.data.retrofit.api;

import bitcoin.graph.app.data.retrofit.BitCoinBlock;
import retrofit.Callback;
import retrofit.http.GET;

public interface BitCoinApi {

    @GET("/charts/market-price?cors=true&format=json&lang=en?timespan=30days")
    void getBitCoinData(Callback<BitCoinBlock> callback);
}
