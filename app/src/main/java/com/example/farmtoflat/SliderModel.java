package com.example.farmtoflat;

public class SliderModel {

    private int Banner;
    private String backgound_color;

    public SliderModel(int banner, String backgound_color) {
        Banner = banner;
        this.backgound_color = backgound_color;
    }

    public int getBanner() {
        return Banner;
    }

    public void setBanner(int banner) {
        Banner = banner;
    }

    public String getBackgound_color() {
        return backgound_color;
    }

    public void setBackgound_color(String backgound_color) {
        this.backgound_color = backgound_color;
    }
}
