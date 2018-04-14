package com.taotao.portal.pojo;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/10 22:49
 * @Description:
 */
public class Item {

    private String id;
    private String title;
    private String sellPoint;
    private long price;
    /**
     * 使用，分隔的多个图片url
     */
    private String image;
    private String categoryName;
    private String itemDesc;
    /**
     * 图片url的数组
     */
    private String[] images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String[] getImages() {
        if (images == null) {
            setImages(image.split(","));
        }
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
