package com.example.administrator.newsclient.UrlManage;

/**
 * Created by yls on 2017/6/28.
 */

public class UrlManage {
// 请求的url格式：
    // http://c.m.163.com/nc/article/headline/新闻类别id/偏移量-每页条数.html

    // 请求的url示例：
    // 头条： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
    // 头条： 第2页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348647909107/20-20.html
    // 头条： 第3页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348647909107/40-20.html

    // 社会： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348648037603/0-20.html
    // 科技： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348649580692/0-20.html
    // 财经： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348648756099/0-20.html
    // 体育： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348649079062/0-20.html
    // 汽车： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348654060988/0-20.html

    // 支持的一些新闻类别类别id如下：
    public final String[] channelId = new String[] {
            "T1348647909107",   // 头条
            "T1348648037603",   // 社会
            "T1348649580692",   // 科技
            "T1348648756099",   // 财经
            "T1348649079062",   // 体育
            "T1348654060988",   // 汽车
    };

    /**
     * 获取一页新闻数据
     * @param channelId 新闻类别id
     * @return
     */
    public static String getUrl(String channelId) {
        // http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
        return "http://c.m.163.com/nc/article/headline/" + channelId + "/0-20.html";
    }

    // 视频url路径
    public static final String VideoURL =
            "http://c.m.163.com/nc/video/list/V9LG4B3A0/y/0-20.html";


}
