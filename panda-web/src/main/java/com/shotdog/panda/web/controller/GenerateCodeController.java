package com.shotdog.panda.web.controller;

import com.shotdog.panda.combi.component.GenerateCodeComponent;
import com.shotdog.panda.combi.model.GenerateConfig;
import com.shotdog.panda.common.Result;
import com.shotdog.panda.common.callback.Callback;
import com.shotdog.panda.common.callback.support.ExecuteCallbackTemplate;
import com.shotdog.panda.web.check.GenerateConfigCheckComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/***
 *  生成代码 controller
 * @author ziming  Create At 2018-12-02 20:42
 *
 */
@RestController
@RequestMapping("/generate")
public class GenerateCodeController {


    private final static Logger log = LoggerFactory.getLogger(GenerateCodeController.class);

    @Resource
    private ExecuteCallbackTemplate executeCallbackTemplate;

    @Resource
    private GenerateCodeComponent generateCodeComponent;

    @Resource
    private GenerateConfigCheckComponent generateConfigCheckComponent;

    @Value("${download.prefix}")
    private String downloadPrefix;

    /**
     * 根据自定义配置生成对应的java 代码
     * @param generateConfig 指定的配置
     * @return
     */
    @PostMapping("/code")
    public Result<String> customGenerate(@RequestBody final GenerateConfig generateConfig){


        return  this.executeCallbackTemplate.execute(new Callback<String>() {
            public Result<String> doExecute() {

                generateConfigCheckComponent.checkParam(generateConfig);

                String url = generateCodeComponent.generate(generateConfig);
                return Result.buildSuc(downloadPrefix.concat(url));
            }
        },log,"生成失败,请重试~");


    }




    /**
     * 根据配置生成对应的java 代码
     * @param generateConfig 指定的配置
     * @return
     */
    @PostMapping("/custom/code")
    public Result<String> generate(@RequestBody final GenerateConfig generateConfig){


        return  this.executeCallbackTemplate.execute(new Callback<String>() {
            public Result<String> doExecute() {

                generateConfigCheckComponent.checkParam(generateConfig);
                String url = generateCodeComponent.generateCustom(generateConfig);
                return Result.buildSuc(downloadPrefix.concat(url));
            }
        },log,"生成失败,请重试~");


    }



    /**
     * 下载生成的代码文件
     *
     * @param file 文件
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/down")
    public ResponseEntity<?> create(String file) throws IOException {

        FileSystemResource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
        headers.add("Content-Disposition", String.format("attachment;filename=\"%s\"", resource.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        InputStreamResource inputStreamResource = new InputStreamResource(resource.getInputStream());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(inputStreamResource);
    }


}
