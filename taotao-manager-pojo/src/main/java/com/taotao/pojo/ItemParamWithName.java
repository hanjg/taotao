package com.taotao.pojo;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/8 22:16
 * @Description: 扩展TbItemParam，增加itemCatName变量
 */
public class ItemParamWithName extends TbItemParam {

    private String itemCatName;

    public ItemParamWithName(TbItemParam itemParam, String itemCatName) {
        this.itemCatName = itemCatName;
        this.setUpdated(itemParam.getUpdated());
        this.setCreated(itemParam.getCreated());
        this.setId(itemParam.getId());
        this.setItemCatId(itemParam.getItemCatId());
        this.setParamData(itemParam.getParamData());
    }

    public String getItemCatName() {
        return itemCatName;
    }

    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName;
    }
}
