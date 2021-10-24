package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElementInfo {

  @SerializedName("key")
  @Expose
  private String key;
  @SerializedName("androidValue")
  @Expose
  private String androidValue;
  @SerializedName("androidType")
  @Expose
  private String androidType;
  @SerializedName("androidIndex")
  @Expose
  private int androidIndex;
  @SerializedName("iosValue")
  @Expose
  private String iosValue;
  @SerializedName("iosType")
  @Expose
  private String iosType;
  @SerializedName("iosIndex")
  @Expose
  private int iosIndex;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getAndroidValue() {
    return androidValue;
  }

  public String getAndroidType() {
    return androidType;
  }

  public int getAndroidIndex() {
    return androidIndex;
  }

  public String getIosValue() {
    return iosValue;
  }

  public String getIosType() {
    return iosType;
  }

  public int getIosIndex() {
    return iosIndex;
  }


  @Override
  public String toString() {
    return "ElementInfo{" +
        "key='" + key + '\'' +
        ", androidValue='" + androidValue + '\'' +
        ", androidType='" + androidType + '\'' +
        ", androidIndex=" + androidIndex +
        ", iosValue='" + iosValue + '\'' +
        ", iosType='" + iosType + '\'' +
        ", iosIndex=" + iosIndex +
        '}';
  }


}
