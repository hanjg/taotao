package com.taotao.common.pojo;

import java.util.List;

/**
 * @Author: hjg
 * @Date: Create in 2018/2/28 17:39
 * @Description: easyui的datagrid插件需要的参数格式
 */
public class EasyUIDataGridResult {

    private long total;

    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
