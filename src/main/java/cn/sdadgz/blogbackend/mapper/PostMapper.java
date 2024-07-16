package cn.sdadgz.blogbackend.mapper;

import cn.sdadgz.blogbackend.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sdadgz
 * @since 2024-07-16
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}
