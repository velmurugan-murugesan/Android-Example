package velmm.com.swipecardviewandroid;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("name")
    private String name;
    @SerializedName("desc")
    private String desc;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @SerializedName("imageUrl")
    private String imageUrl;

    public Movie(String name, String desc, String image) {
        this.name = name;
        this.desc = desc;
        this.imageUrl = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", image=" + imageUrl +
                '}';
    }
}
