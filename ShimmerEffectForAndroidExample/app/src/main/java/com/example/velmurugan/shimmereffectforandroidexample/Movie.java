package com.example.velmurugan.shimmereffectforandroidexample;

import com.google.gson.annotations.SerializedName;

public class Movie {

  @SerializedName("name")
  private String name;
  @SerializedName("desc")
  private String desc;
  @SerializedName("imageUrl")
  private String imageUrl;

  public Movie(String name, String desc, String imageUrl) {
    this.name = name;
    this.desc = desc;
    this.imageUrl = imageUrl;
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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
