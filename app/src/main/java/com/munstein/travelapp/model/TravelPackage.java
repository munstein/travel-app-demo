package com.munstein.travelapp.model;

import java.math.BigDecimal;

/**
 * Created by @Munstein on 06/01/2018.
 */

public class TravelPackage {

    private String description;
    private String title;
    private String imgUrl;
    private BigDecimal price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFormattedPrice(){
        return this.price.toString().replace(".", ",");
    }

}
