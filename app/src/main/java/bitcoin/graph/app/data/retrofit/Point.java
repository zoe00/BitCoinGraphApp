
package bitcoin.graph.app.data.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Point {

    @SerializedName("x")
    @Expose
    public long x;
    @SerializedName("y")
    @Expose
    public double y;

}
