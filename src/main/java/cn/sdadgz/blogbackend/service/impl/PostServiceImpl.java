package cn.sdadgz.blogbackend.service.impl;

import cn.sdadgz.blogbackend.entity.Post;
import cn.sdadgz.blogbackend.mapper.PostMapper;
import cn.sdadgz.blogbackend.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sdadgz
 * @since 2024-07-16
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
