
package bitcoin.graph.app.data.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BitCoinBlock {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("unit")
    @Expose
    public String unit;
    @SerializedName("period")
    @Expose
    public String period;
    @SerializedName("values")
    @Expose
    public ArrayList<Point> values = new ArrayList<Point>();

}
