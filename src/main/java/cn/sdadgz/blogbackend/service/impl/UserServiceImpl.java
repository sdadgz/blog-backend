package cn.sdadgz.blogbackend.service.impl;

import cn.sdadgz.blogbackend.entity.User;
import cn.sdadgz.blogbackend.mapper.UserMapper;
import cn.sdadgz.blogbackend.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
