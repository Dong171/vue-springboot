package com.example.demo.Controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.vo.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${server.port}")
    private String port;
    private static final String ip="http://localhost";
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        String originalFilename=file.getOriginalFilename();
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath="D:/backtest/Week3/src/main/resources/files/"+flag+"_"+originalFilename;
        FileUtil.writeBytes(file.getBytes(),rootFilePath);
        return Result.success(ip+":"+port+"/files/"+flag);


    }
    @GetMapping("/{flag}")
    public void getFiles(HttpServletResponse response, @PathVariable String flag){
        OutputStream os;
        String basePath="D:/backtest/Week3/src/main/resources/files/";
        List<String> fileNames=FileUtil.listFileNames(basePath);
        String avatar =fileNames.stream().filter(name->name.contains(flag)).findAny().orElse("");
        try{
            if(StrUtil.isNotEmpty(avatar)){
                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(avatar,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes=FileUtil.readBytes(basePath+avatar);
                os=response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();

            }

            }
        catch(Exception e){
            System.out.println("文件下载失败");
        }

    }
}
