package top.riversunny.mvp_rxretrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lijiang on 2017/8/22.
 */

public class NodeModel extends V2EXModel implements Parcelable {
    private static final long serialVersionUID = 2017082203L;

    public int id;
    public String name;
    public String title;
    public String titleAlternative;
    public String url;
    public int topics;

    public String header;
    public String footer;

    public boolean isCollected = false;

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getTitleAlternative(){return titleAlternative;}
    public void setTitleAlternative(String titleAlternative){this.titleAlternative = titleAlternative;}
    public String getUrl(){return url;}
    public void setUrl(String url){this.url = url;}
    public int getTopics(){return topics;}
    public void setTopics(int topics){this.topics = topics;}
    public String getHeader(){return header;}
    public void setHeader(String header){this.header = header;}
    public String getFooter(){return footer;}
    public void setFooter(String footer){this.footer = footer;}
    public boolean getIsCollected(){return isCollected;}
    public void setIsCollected(boolean isCollected){this.isCollected = isCollected;}

    public String toString() {
        return "NodeModel.id=" + id
                + " NodeModel.title=" + title
                + " NodeModel.content=" + url
                + " NodeModel.titleAlternative=" + titleAlternative  + " | ";
    }

    public NodeModel() {
    }

    protected NodeModel(Parcel in) {
        int[] ints = new int[2];
        in.readIntArray(ints);
        id = ints[0];
        topics = ints[1];
        String[] strings = new String[6];
        in.readStringArray(strings);
        name = strings[0];
        title = strings[1];
        url = strings[2];
        if((titleAlternative = strings[3]).equals("")){
            titleAlternative = null;
        }
        if((header = strings[4]).equals("")){
            header = null;
        }
        if((footer = strings[5]).equals("")){
            footer = null;
        }

        boolean[] booleans = new boolean[1];
        in.readBooleanArray(booleans);
        isCollected = booleans[0];

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(new int[]{
                id,
                topics
        });
        String[] strings = new String[6];
        strings[0] = name;
        strings[1] = title;
        strings[2] = url;
        if((strings[3] = titleAlternative) == null){
            strings[3] = "";
        }
        if((strings[4] = header) == null){
            strings[4] = "";
        }
        if((strings[5] = footer) == null){
            strings[5] = "";
        }
        dest.writeStringArray(strings);
        dest.writeBooleanArray(new boolean[]{ isCollected });

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NodeModel> CREATOR = new Creator<NodeModel>() {
        @Override
        public NodeModel createFromParcel(Parcel in) {
            return new NodeModel(in);
        }

        @Override
        public NodeModel[] newArray(int size) {
            return new NodeModel[size];
        }
    };

    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        name = jsonObject.getString("name");
        title = jsonObject.getString("title");
        url = jsonObject.getString("url");
        topics = jsonObject.getInt("topics");
        if(!jsonObject.optString("title_alternative").equals("null")){
            titleAlternative = jsonObject.optString("title_alternative");
        }
        if(!jsonObject.optString("header").equals("null")){
            header = jsonObject.optString("header");
        }
        if(!jsonObject.optString("footer").equals("null")){
            footer = jsonObject.optString("footer");
        }
    }
}
