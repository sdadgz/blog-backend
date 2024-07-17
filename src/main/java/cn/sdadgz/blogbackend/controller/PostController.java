package cn.sdadgz.blogbackend.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Dict;
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
    public Result getByUid(@RequestParam(value = "uid", required = false) Long uid,
                           @RequestParam("pageNum") Integer pageNum,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam(value = "desc", defaultValue = "true") Boolean desc) { // 默认倒叙
        // 不封装一遍Controller了，就这样吧
        // 导过来的功夫够手写完了都

        uid = UserUtil.getUserId();

        PageHelper.startPage(pageNum, pageSize);

        // 讲道理不应该这样写，要手写sql，实习再写sql吧，
        List<Post> list = postService.lambdaQuery()
                .eq(Post::getUserId, uid)
                .orderBy(true, !desc, Post::getCreate)
                .orderByAsc(Post::getPostId) // 加上，教训这是
                .list();

        PageInfo<Post> pageInfo = new PageInfo<>(list);
//        System.out.println(pageInfo);

        // 到时候再写dao吧
        return Result.success(Dict.create()
                .set(Constants.ROWS, pageInfo.getList())
                .set(Constants.TOTAL, pageInfo.getTotal()));
    }

    @GetMapping("/{postId}")
    public Result getById(@PathVariable("postId") Long postId) {
        return Result.success(postService.getById(postId));
    }

    @PutMapping("/{postId}")
    public Result updateById(@PathVariable("postId") Long postId, @RequestBody Post post) {

        ExceptionUtil.throwIf(!UserUtil.getUserId().equals(postService.getById(postId).getUserId()), Constants.CODE_401, "无权限");

        post.setPostId(postId);
        post.setCreate(null);
        post.setLastModified(LocalDateTimeUtil.now());
        postService.updateById(post);
        return Result.success();
    }

    @DeleteMapping("/{postId}")
    public Result deleteById(@PathVariable("postId") Long postId) {

        ExceptionUtil.throwIf(!UserUtil.getUserId().equals(postService.getById(postId).getUserId()), Constants.CODE_401, "无权限");

        postService.removeById(postId);
        return Result.success();
    }

}
