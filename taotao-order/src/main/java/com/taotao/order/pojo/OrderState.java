package com.taotao.order.pojo;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/23 14:18
 * @Description:
 */
public enum OrderState {
    NOT_PAID(1, "未付款"), PAID(2, "已付款"), NOT_DELIVER(3, "未发货"),
    DELIVER(4, "已发货"), DEAL_SUCCESSFUL(5, "交易成功"), DEAL_CLOSE(6, "交易关闭");

    private int status;
    private String stateInfo;

    OrderState(int status, String stateInfo) {
        this.status = status;
        this.stateInfo = stateInfo;
    }

    public int getStatus() {
        return status;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static OrderState stateOf(int state) {
        for (OrderState orderState : values()) {
            if (orderState.getStatus() == state) {
                return orderState;
            }
        }
        return null;
    }
}
