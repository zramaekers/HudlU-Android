package ramaekers.zach.hudlu.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zach.ramaekers on 11/22/2015.
 */
public class MashableNews {
    @SerializedName("new")
    public List<MashableNewsItem> newsItems;
}
