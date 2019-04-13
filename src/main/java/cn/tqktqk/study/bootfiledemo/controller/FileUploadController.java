package cn.tqktqk.study.bootfiledemo.controller;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-04-13 13:21
 * 文件上传controller
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    /**
     * 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String fileUpload(@RequestParam(value = "fileName") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        String fileMd5 = DigestUtils.md5DigestAsHex(file.getBytes());
        System.out.println("文件 MD5 ："+fileMd5);
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        String path="/Users/tuqikang/Desktop/study-try/boot-file-demo/test";
        File dest = new File(path + "/" + fileMd5);

        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }


    /**
     * 下载文件
     * @param res
     * @param path
     * @return
     */
    @RequestMapping("/download")
    public boolean downLoad(HttpServletResponse res,String path){
        File file = new File("/Users/tuqikang/Desktop/study-try/boot-file-demo/test/"+path);
        String fileName = "test.docx";
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
        return false;
    }


}
