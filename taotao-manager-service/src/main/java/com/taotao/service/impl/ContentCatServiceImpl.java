package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCatService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/26 23:18
 * @Description:
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getCategoryList(long parentId) {
        //设置查询条件
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

        //分类列表转换成EasyUITreeNode的列表
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbContentCategory contentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode(contentCategory.getId(), contentCategory.getName(),
                    contentCategory.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }

        return resultList;
    }

    @Override
    public TbContentCategory getCategory(long id) {
        return contentCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public TaotaoResult addContentCategory(long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setIsParent(false);
        contentCategory.setSortOrder(1);
        //状态值：1，正常；2，删除。用于逻辑删除，暂时不用
        contentCategory.setStatus(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());

        contentCategoryMapper.insert(contentCategory);

        //查看父节点，如果父节点是叶节点，则改为非叶节点
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }

        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public TaotaoResult deleteContentCategory(long id) {
        //查询父节点
        long parentId = contentCategoryMapper.selectByPrimaryKey(id).getParentId();

        //删除节点
        contentCategoryMapper.deleteByPrimaryKey(id);

        //查看父节点是否还有其他子节点，如果没有则将parentId节点设为叶节点
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        if (list.isEmpty()) {
            TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
            parentCat.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContentCategory(long id, String name) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);

        return TaotaoResult.ok(contentCategory);
    }
}
