package cn.sdadgz.blogbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

/**
 * <p>
 * 
 * </p>
 *
 * @author sdadgz
 * @since 2024-07-16
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "")
@FieldNameConstants
@Accessors(chain = true)
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String username;

//    @JsonIgnore
    private String password;

    private String email;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("`create`")
    private LocalDateTime create;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;


}
