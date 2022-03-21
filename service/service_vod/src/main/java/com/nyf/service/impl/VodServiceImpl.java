package com.nyf.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.nyf.ExceptionHandler.exception.MyException;
import com.nyf.service.VodService;
import com.nyf.util.ConstantVodUtils;
import com.nyf.util.InitObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAliyun(MultipartFile file) {

        try {
            //accessKeyId,accessKeySecret

            //fileName：上传文件原始名称
            String fileName = file.getOriginalFilename();

            //title：上传之后显示名称
            String title = fileName.substring(0,fileName.lastIndexOf("."));

            //inputStream：上传文件的输入流
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESSKEY_ID
                    , ConstantVodUtils.ACCESSKEY_SECRET
                    , title, fileName
                    , inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeAliyunVideoById(String id) {
        try {
            DefaultAcsClient client = InitObject.initVodClient(ConstantVodUtils.ACCESSKEY_ID, ConstantVodUtils.ACCESSKEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.println("RequestId = "+ response.getRequestId()+"\n");

        } catch (ClientException e) {
            throw new MyException(20001,"视频删除失败");
        }
    }

}
