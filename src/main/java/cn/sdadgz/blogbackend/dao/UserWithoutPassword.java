package cn.sdadgz.blogbackend.dao;

import cn.sdadgz.blogbackend.entity.User;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 无密码用户
 *
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2024/7/17 14:06:29
 */
@Getter
@Setter
@NoArgsConstructor
public class UserWithoutPassword {

    public UserWithoutPassword(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.create = user.getCreate();
        this.lastModified = user.getLastModified();
    }

    private Long userId;

    private String username;

    private String email;

    @TableField("`create`")
    private LocalDateTime create;

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;

}
