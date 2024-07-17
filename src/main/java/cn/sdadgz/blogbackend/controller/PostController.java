package cn.sdadgz.blogbackend.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.sdadgz.blogbackend.common.Constants;
import cn.sdadgz.blogbackend.common.Result;
import cn.sdadgz.blogbackend.entity.Post;
import cn.sdadgz.blogbackend.service.IPostService;
import cn.sdadgz.blogbackend.util.CommonUtil;
import cn.sdadgz.blogbackend.util.ExceptionUtil;
import cn.sdadgz.blogbackend.util.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sdadgz
 * @since 2024-07-16
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostService postService;

    @PostMapping
    public Result save(@RequestBody Post post) {

        // 标题不可为空
        ExceptionUtil.throwIf(CommonUtil.isNull(post.getTitle()), Constants.CODE_400, "标题不可为空");
        // 内容不可为空
        ExceptionUtil.throwIf(CommonUtil.isNull(post.getContent()), Constants.CODE_400, "内容不可为空");

        // 获取用户id放到post对象中
        post.setUserId(UserUtil.getUserId());
        // 时间设置当前时间
        post.setCreate(LocalDateTimeUtil.now());
        post.setLastModified(LocalDateTimeUtil.now());


        postService.save(post);

        return Result.success();
    }

    @GetMapping
    public Result getByUid(@RequestParam("uid") String uid,
                           @RequestParam("pageNum") Integer pageNum,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam(value = "desc", required = false) Boolean desc) { // 默认倒叙
        // pageHelper不加了，也不封装一遍Controller了，就这样吧
        // 导过来的功夫够手写完了都

        CommonUtil.thisOrDefault(desc, true);
        PageHelper.startPage(pageNum, pageSize);

        List<Post> list = postService.lambdaQuery()
                .eq(Post::getUserId, uid)
                .orderBy(true, desc, Post::getCreate, Post::getPostId)
                .list();

        PageInfo<Post> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);

        // 讲道理不应该这样写，要手写sql，实习再写sql吧，
        return Result.success();
    }

}
