package cn.conststar.wall.controller;

import cn.conststar.wall.pojo.PojoComment;
import cn.conststar.wall.pojo.PojoUser;
import cn.conststar.wall.service.ServiceComment;
import cn.conststar.wall.service.ServiceUser;
import cn.conststar.wall.utils.UtilsMain;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comment", produces = {"application/json;charset=UTF-8"})
public class ControllerComment {
    private Logger logger = Logger.getLogger(ControllerComment.class);

    @Autowired
    @Qualifier("serviceComment")
    private ServiceComment serviceComment;


    @Autowired
    @Qualifier("serviceUser")
    private ServiceUser serviceUser;


    //获取帖子分页评论列表
    @GetMapping("/pageList")
    public String getPageList(@RequestParam("tableId") int tableId,
                              @RequestParam("pageIndex") int pageIndex,
                              @RequestParam("pageSize") int pageSize) throws Exception {

        JSONObject jsonObject = new JSONObject();
        List<PojoComment> comments = serviceComment.getCommentsPage(tableId, pageIndex, pageSize);

        JSONArray listJson = (JSONArray) JSON.toJSON(comments);
        jsonObject.put("list", listJson);
        jsonObject.put("msg", "获取成功");
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    //获取帖子评论总数
    @GetMapping("/count")
    public String getCount(@RequestParam("tableId") int tableId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        int count = serviceComment.getCount(tableId);

        jsonObject.put("count", count);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "获取成功");

        return jsonObject.toJSONString();
    }

    //发布评论
    @PostMapping("/add")
    public String post(@RequestParam("tableId") int tableId,
                       @RequestParam("name") String name,
                       @RequestParam("anonymous") boolean anonymous,
                       @RequestParam("content") String content,
                       @RequestParam("images") String images,
                       @RequestHeader(value = "token", required = false) String token) throws Exception {
        JSONObject jsonObject = new JSONObject();

        PojoUser user = serviceUser.getUser(token); //验证用户登录状态

        serviceComment.addComment(tableId, user.getId(), name, anonymous, content, images);

        jsonObject.put("code", 0);
        jsonObject.put("msg", "发布成功");

        return jsonObject.toJSONString();
    }
}
