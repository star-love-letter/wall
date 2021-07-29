package cn.conststar.wall.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PojoUserPublic implements Serializable {
    //账号id
    private int id;

    //邮箱
    private String email;

    //名称
    private String name;

    //学号
    private String studentId;

    //注册时间
    private Date createTime;

    //最近登录时间
    private Date lastTime;

}
