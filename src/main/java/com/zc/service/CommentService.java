package com.zc.service;

import com.zc.domain.Comment;
import com.zc.mapper.CommentMapper;
import com.zc.utils.CommonPage;
import com.zc.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /*
    * 查询有评论列表
    * @params commonPage
    * @params comment
    * */
    public CommonResult getCommentList(CommonPage commonPage, Comment comment) {

        Map<String, Object> map = new HashMap<String, Object>();

        commonPage.setTotal(commentMapper.countAll(comment));

        List<Comment> all = commentMapper.findAll(commonPage, comment);

        map.put("page", commonPage);
        map.put("list", all);

        if (all.size() == 0) {
            return CommonResult.isNull(map);
        } else {
            return CommonResult.success(map);
        }
    }

    /*
    * 新增一条评论
    * @params comment
    * */
    public CommonResult addOne(Comment comment) {

        if (comment.getArticleId() == null || comment.getCommentContent() == null || comment.getCommentEmail() == null) {
            return CommonResult.paramsError("");
        } else {
            comment.setCreateTime(new Date());
            commentMapper.addOne(comment);

            return CommonResult.success(comment);
        }
    }

    /*
    * 删除一条评论
    * @params comment
    * */
    public CommonResult deleteOne(Comment comment) {
        if (comment.getId() == null) {
            return  CommonResult.paramsError("");
        } else {
            commentMapper.deleteOne(comment);

            return CommonResult.success("");
        }
    }
}
