package bitcoin.graph.app.domain.action;

import android.support.annotation.NonNull;

import retrofit.Callback;
import bitcoin.graph.app.data.control.BitCoinRetroDataSource;
import bitcoin.graph.app.data.retrofit.BitCoinBlock;

public class GetBitCoinAction {

    private final BitCoinRetroDataSource bitCoinRetroDataSource;

    public GetBitCoinAction(final BitCoinRetroDataSource bitCoinRetroDataSource){
        this.bitCoinRetroDataSource = bitCoinRetroDataSource;
    }

    public void execute(@NonNull Callback<BitCoinBlock> callback){
        bitCoinRetroDataSource.getBitCoinData(callback);
    }
}
