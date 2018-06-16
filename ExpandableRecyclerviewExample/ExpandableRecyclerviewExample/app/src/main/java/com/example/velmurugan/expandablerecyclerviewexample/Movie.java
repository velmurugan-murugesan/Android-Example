package com.example.velmurugan.expandablerecyclerviewexample;

import com.google.gson.annotations.SerializedName;

public class Movie {

  @SerializedName("name")
  private String name;
  @SerializedName("desc")
  private String desc;
  @SerializedName("imageUrl")
  private String imageUrl;
  @SerializedName("category")
  private String categoty;

  public Movie(String name, String desc, String imageUrl, String categoty) {
    this.name = name;
    this.desc = desc;
    this.imageUrl = imageUrl;
    this.categoty = categoty;
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

  public String getCategoty() {
    return categoty;
  }

  public void setCategoty(String categoty) {
    this.categoty = categoty;
  }

  @Override
  public String toString() {
    return "Movie{" +
        "name='" + name + '\'' +
        ", desc='" + desc + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        ", categoty='" + categoty + '\'' +
        '}';
  }
}

