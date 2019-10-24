package io.sparrow.sb2.demo.controller;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.common.share.log.DbLog;
import io.sparrow.sb2.demo.api.StdCrudApi;
import org.springframework.web.bind.annotation.*;

/**
 * 标准增删改查示例控制器
 *
 * @author Aaric, created on 2019-10-24T10:00.
 * @version 1.1.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/plat/demo/stdCrud")
public class StdCrudController implements StdCrudApi {

    @Override
    @PostMapping(value = "/save")
    @DbLog(title = "测试模块/标准增删改查示例", content = "保存数据")
    public ResponseData<Object> save() throws ApiException {
        return ResponseData.ok(null).extraMsg("保存数据");
    }

    @Override
    @PutMapping(value = "/update")
    @DbLog(title = "测试模块/标准增删改查示例", content = "更新数据")
    public ResponseData<Object> update() throws ApiException {
        return ResponseData.ok(null).extraMsg("更新数据");
    }

    @Override
    @GetMapping(value = "/get")
    @DbLog(title = "测试模块/标准增删改查示例", content = "查询数据")
    public ResponseData<Object> get() throws ApiException {
        return ResponseData.ok(null).extraMsg("查询数据");
    }

    @Override
    @GetMapping(value = "/query")
    @DbLog(title = "测试模块/标准增删改查示例", content = "批量查询数据")
    public ResponseData<Object> query() throws ApiException {
        return ResponseData.ok(null).extraMsg("批量查询数据");
    }

    @Override
    @DeleteMapping(value = "/delete")
    @DbLog(title = "测试模块/标准增删改查示例", content = "删除数据")
    public ResponseData<Object> delete() throws ApiException {
        return ResponseData.ok(null).extraMsg("删除数据");
    }
}
