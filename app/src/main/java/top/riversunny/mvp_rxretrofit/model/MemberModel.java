package top.riversunny.mvp_rxretrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lijiang on 2017/8/22.
 */

public class MemberModel extends V2EXModel implements Parcelable {

    private static final long serialVersionUID = 2017082202L;

    public int id;
    public String username;
    public String tagline;
    public String avatar;
    public String website;
    public String github;
    public String twitter;
    public String location;

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String getTagline(){return tagline;}
    public void setTagline(String tagline){this.tagline = tagline;}
    public String getAvatar(){return avatar;}
    public void setAvatar(String avatar){this.avatar = avatar;}
    public String getWebsite(){return website;}
    public void setWebsite(String website){this.website = website;}
    public String getGithub(){return github;}
    public void setGithub(String github){this.github = github;}
    public String getTwitter(){return twitter;}
    public void setTwitter(String twitter){this.twitter = twitter;}
    public String getLocation(){return location;}
    public void setLocation(String location){this.location = location;}

    public String toString() {
        return "MemberModel.id=" + id
                + " MemberModel.username=" + username
                + " NodeModel.tagline=" + tagline
                + " NodeModel.github=" + github  + " | ";
    }

    public MemberModel(){

    }

    protected MemberModel(Parcel in) {
        id = in.readInt();
        String[] strings = new String[7];
        in.readStringArray(strings);
        username = strings[0];
        tagline = strings[1];
        avatar = strings[2];
        website = strings[3];
        github = strings[4];
        twitter = strings[5];
        location = strings[6];
    }

    public static final Creator<MemberModel> CREATOR = new Creator<MemberModel>() {
        @Override
        public MemberModel createFromParcel(Parcel in) {
            return new MemberModel(in);
        }

        @Override
        public MemberModel[] newArray(int size) {
            return new MemberModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeStringArray(new String[]{
                username,
                tagline,
                avatar,
                website,
                github,
                twitter,
                location
        });
    }

    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        username = jsonObject.getString("username");
        tagline = jsonObject.getString("tagline");
        website = jsonObject.optString("website");
        github = jsonObject.optString("github");
        twitter = jsonObject.optString("twitter");
        location = jsonObject.optString("location");
        if (!website.isEmpty()
                && !website.startsWith("http://")
                && !website.startsWith("https://"))
            website = "http://" + website;
        avatar = jsonObject.getString("avatar_large");
        if (avatar.startsWith("//")) {
            avatar = "http:" + avatar;
        }

    }
}
