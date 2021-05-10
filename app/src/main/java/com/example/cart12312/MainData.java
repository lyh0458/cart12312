package com.example.cart12312;

public class MainData {

  private String  profile; //String 을 쓴 이유는 파이어 베이스에서 URL 문자 열을 갖고오기 떄문
  private String  name;
  private int price;


  //겟터셋터를 이용해서 만듬듬

 public MainData(){}{}

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
