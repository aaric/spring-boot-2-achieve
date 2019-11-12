package io.sparrow.sb2.demo.controller;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.common.share.log.DbLog;
import io.sparrow.sb2.demo.api.StdCrudApi;
import io.sparrow.sb2.dto.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 标准增删改查示例控制器
 *
 * @author Aaric, created on 2019-10-24T10:00.
 * @version 1.1.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/plat/demo/stdCrud")
public class StdCrudController implements StdCrudApi {

    @Autowired
    private HttpSession httpSession;

    /**
     * 设置格式化日志内容参数对象
     *
     * @param objects 格式化日志内容参数对象
     */
    private void setDbLogObjects(Object[] objects) {
        // 设置格式化日志内容参数对象
        httpSession.setAttribute(DbLog.DEFAULT_CONTENT_OBJECTS_KEY, objects);
    }

    @Override
    @PostMapping(value = "/save")
    @DbLog(title = "测试模块", content = "保存人员信息：{0}")
    public ResponseData<Integer> save(@Valid @RequestBody PersonDto personDto) throws ApiException {
        // 设置日志内容参数对象
        setDbLogObjects(new Object[]{personDto.getFullName()});

        return ResponseData.ok(1).extraMsg("保存人员信息");
    }

    @Override
    @PutMapping(value = "/update")
    @DbLog(title = "测试模块", content = "更新人员信息：{0}")
    public ResponseData<Object> update(@Valid @RequestBody PersonDto personDto) throws ApiException {
        // 设置日志内容参数对象
        setDbLogObjects(new Object[]{personDto.getFullName()});

        return ResponseData.ok(null).extraMsg("更新人员信息");
    }

    @Override
    @GetMapping(value = "/get/{id}")
    @DbLog(title = "测试模块", content = "查询人员信息：{0}")
    public ResponseData<PersonDto> get(@PathVariable("id") Integer id) throws ApiException {
        // 设置日志内容参数对象
        setDbLogObjects(new Object[]{id});

        return ResponseData.ok(new PersonDto("jenkins", "jenkins@incarcloud.com")).extraMsg("查询人员信息");
    }

    @Override
    @GetMapping(value = "/query")
    public ResponseData<List<PersonDto>> query() throws ApiException {
        return ResponseData.ok(null).extraMsg("批量查询人员信息");
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    @DbLog(title = "测试模块", content = "删除人员信息：{0}")
    public ResponseData<Object> delete(@PathVariable("id") Integer id) throws ApiException {
        // 设置日志内容参数对象
        setDbLogObjects(new Object[]{id});

        // 测试DbLog捕获异常日志
        if (0 < System.currentTimeMillis()) {
            throw new ApiException(ResponseData.ERROR_0002, "DbLog自定义异常捕获", null);
        }

        return ResponseData.ok(null).extraMsg("删除人员信息");
    }
}
