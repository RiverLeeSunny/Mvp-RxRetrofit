package top.riversunny.mvp_rxretrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lijiang on 2017/8/22.
 */

public class TopicModel extends V2EXModel implements Parcelable {
    private static final long serialVersionUID = 2017082201L;

    public int id;
    public String title;
    public String url;
    public String content;
    public String contentRendered;
    public int replies;
    public MemberModel member;
    public NodeModel node;
    public long created;
    public long lastModified;
    public long lastTouched;

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getUrl(){return url;}
    public void setUrl(String url){this.url = url;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public String getContentRendered(){return contentRendered;}
    public void setContentRendered(String contentRendered){this.contentRendered = contentRendered;}
    public int getReplies(){return replies;}
    public void setReplies(int replies){this.replies = replies;}
    public MemberModel getMember(){return member;}
    public void setMember(MemberModel member){this.member = member;}
    public NodeModel getNode(){return node;}
    public void setNode(NodeModel node){this.node = node;}
    public long getCreated(){return created;}
    public void setCreated(long created){this.created = created;}
    public long getLastModified(){return lastModified;}
    public void setLastModified(long lastModified){this.lastModified = lastModified;}
    public long getLastTouched(){return lastTouched;}
    public void setLastTouched(long lastTouched){this.lastTouched = lastTouched;}

    @Override
    public String toString() {
        return "TopicModel.id=" + id
                + " TopicModel.title=" + title
                + " TopicModel.content=" + content
                + " Subject.created=" + created + member.toString() + node.toString() + " | ";
    }

    public TopicModel(){}

    protected TopicModel(Parcel in) {
        int[] ints= new int[2];
        in.readIntArray(ints);
        id = ints[0];
        replies = ints[1];
        String[] strings = new String[4];
        in.readStringArray(strings);
        title = strings[0];
        url = strings[1];
        content = strings[2];
        contentRendered = strings[3];
        long[] longs = new long[3];
        in.readLongArray(longs);
        created = longs[0];
        lastModified = longs[1];
        lastTouched = longs[2];
        member = (MemberModel) in.readValue(MemberModel.class.getClassLoader());
        node = (NodeModel) in.readValue(NodeModel.class.getClassLoader());
    }

    public static final Creator<TopicModel> CREATOR = new Creator<TopicModel>() {
        @Override
        public TopicModel createFromParcel(Parcel in) {
            return new TopicModel(in);
        }

        @Override
        public TopicModel[] newArray(int size) {
            return new TopicModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(new int[]{
                id,
                replies
        });
        parcel.writeStringArray(new String[]{
                title,
                url,
                content,
                contentRendered
        });
        parcel.writeLongArray(new long[]{
                created,
                lastModified,
                lastTouched
        });
        parcel.writeValue(member);
        parcel.writeValue(node);
    }

    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        title = jsonObject.getString("title");
        url = jsonObject.getString("url");
        content = jsonObject.getString("content");
        contentRendered = jsonObject.getString("content_rendered")
                .replace("href=\"/member/", "href=\"http://www.v2ex.com/member/")
                .replace("href=\"/i/", "href=\"https://i.v2ex.co/")
                .replace("href=\"/go/", "href=\"http://www.v2ex.com/go/")
                .replace("href=\"/t/", "href=\"http://www.v2ex.com/t/");
        replies = jsonObject.getInt("replies");
        member = new MemberModel();
        member.parse(jsonObject.getJSONObject("member"));
        node = new NodeModel();
        node.parse(jsonObject.getJSONObject("node"));
        created = jsonObject.getLong("created");
        lastModified = jsonObject.getLong("last_modified");
        lastTouched = jsonObject.getLong("last_touched");
    }
}
