package top.riversunny.mvp_rxretrofit.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by lijiang on 2017/8/22.
 */

public abstract class V2EXModel implements Serializable {
//    private static final long serialVersionUID = 2017082200L;
    abstract public void parse(JSONObject jsonObject) throws JSONException;

}
