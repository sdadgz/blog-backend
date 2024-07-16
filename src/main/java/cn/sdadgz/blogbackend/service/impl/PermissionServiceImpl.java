package cn.sdadgz.blogbackend.service.impl;

import cn.sdadgz.blogbackend.entity.Permission;
import cn.sdadgz.blogbackend.mapper.PermissionMapper;
import cn.sdadgz.blogbackend.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
