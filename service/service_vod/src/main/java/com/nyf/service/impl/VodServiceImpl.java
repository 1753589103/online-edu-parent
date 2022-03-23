package com.nyf.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.nyf.ExceptionHandler.exception.MyException;
import com.nyf.service.VodService;
import com.nyf.util.ConstantVodUtils;
import com.nyf.util.InitObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAliyun(MultipartFile file) {

        try {
            //accessKeyId,accessKeySecret

            //fileName：上传文件原始名称
            String fileName = file.getOriginalFilename();

            //title：上传之后显示名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));

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
            System.out.println("RequestId = " + response.getRequestId() + "\n");

        } catch (ClientException e) {
            throw new MyException(20001, "视频删除失败");
        }
    }

    //根据id集合删除多个视频
    @Override
    public void removeMoreVideo(List<String> videoIdList) {
        //将集合转换为1,2,3格式
        String str = StringUtils.join(videoIdList.toArray(), ",");

        try {
            //初始化对象
            DefaultAcsClient client = InitObject.initVodClient(ConstantVodUtils.ACCESSKEY_ID, ConstantVodUtils.ACCESSKEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置要删除视频的id值
            request.setVideoIds(str);
            //调用初始化对象的方法实现删除
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.println("RequestId = " + response.getRequestId() + "\n");
        } catch (Exception e) {
            throw new MyException(20001, "视频删除失败");
        }

    }

    //根据视频id获取视频凭证
    @Override
    public String getPlayAuth(String id) {
        String accesskeyId = "LTAI5t7KpSvi2AEi1jK2Dz9c";
        String accesskeySecret = "VPIm18wLC2oqmgc0Yjtxu4I89MeE2m";
        try {
            //创建初始化对象
            DefaultAcsClient cl = InitObject.initVodClient(accesskeyId,accesskeySecret);
            //创建获取视频地址request对象和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request对象设置视频id值
            request.setVideoId(id);

            GetVideoPlayAuthResponse response = cl.getAcsResponse(request);

            //获取视频播放凭证
            return response.getPlayAuth();

        } catch (ClientException e) {
            e.printStackTrace();
            throw new MyException(20001,"获取视频凭证失败");
        }
    }

}
