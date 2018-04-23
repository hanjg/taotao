package com.taotao.portal.pojo;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/22 22:26
 * @Description: 购物车中的商品信息
 */
public class CartItem {

    private long id;
    private String title;
    private Integer num;
    private long price;
    private String image;

    /**
     * 图片url的数组
     */
    private String[] images;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
