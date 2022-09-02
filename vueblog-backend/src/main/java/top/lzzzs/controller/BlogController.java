package top.lzzzs.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import top.lzzzs.common.lang.R;
import top.lzzzs.entity.Blog;
import top.lzzzs.service.BlogService;
import top.lzzzs.utils.ShiroUtil;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @since 2022-08-20
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;


    @GetMapping("/blogs")
    public R list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return R.s(pageData);
    }

    @GetMapping("/blogs/{id}")
    public R detail(@PathVariable("id") Long id) {

        Blog blog = blogService.getById(id);

        return R.s(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blogs/edit")
    public R edit(@RequestBody Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            // edit...
            temp = blogService.getById(blog.getId());
            // 判断当前要修改的文章id是否与当前用户id相同
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()), "没有权限编辑");

        } else {
            // add...

            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);

        }

        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);

        return R.s(null);
    }

    @RequiresAuthentication
    @PostMapping("/blogs/delete/{id}")
    public R delete(@PathVariable("id") Long id) {

        Blog blog = blogService.getById(id);
        // 判断当前要删除的文章id是否与当前用户id相同
        Assert.isTrue(blog.getUserId().equals(ShiroUtil.getProfile().getId()), "没有权限删除");

        blogService.removeById(id);

        return R.s(null);
    }
}
