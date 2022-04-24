package com.tute.wjl.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tute.wjl.entity.StuCourseLogInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtil {
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "accessKeyId";
    private static String accessKeySecret = "accessKeySecret";

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    private static String bucketName = "bucketName";

    // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
    static OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    public static void main(String[] args){
        StuCourseLogInfo stuCourseLogInfo = new StuCourseLogInfo();
        stuCourseLogInfo.setContent("老师，我到了");
        stuCourseLogInfo.setStuAccount("student");
        stuCourseLogInfo.setStuName("student123");
        stuCourseLogInfo.setCreateTime(DateUtil.formatDateTime(new Date()));
        StuCourseLogInfo s2 = new StuCourseLogInfo();
        s2.setContent("老师，我到了123");
        s2.setStuAccount("s2");
        s2.setStuName("s2");
        s2.setCreateTime(DateUtil.formatDateTime(new Date()));
        List<StuCourseLogInfo> data = new ArrayList<>();
        data.add(stuCourseLogInfo);
        data.add(s2);
        data.add(s2);
        export(data,"高等数学-网络1801班-"+ DateUtil.formatDateTime(new Date()));
    }

    public static void export(List<StuCourseLogInfo> data, String fileName) {
        String[] header = {"序号", "学生姓名", "学生账号","签到内容","签到时间"};
        String[] fieldNames = fieldName(StuCourseLogInfo.class);
        Workbook wb = new HSSFWorkbook();
        int rowSize = 0;
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(rowSize);
        for (int i = 0; i < header.length; i++) {
            row.createCell(i).setCellValue(header[i]);
        }
        try {
            for (int x = 0; x < data.size(); x++) {
                rowSize = 1;
                Row rowNew = sheet.createRow(rowSize + x);
                for (int i = 0; i < header.length; i++) {
                    StuCourseLogInfo user = data.get(x);
                    user.setId(rowSize+x);
                    for (int i1 = 0; i1 < fieldNames.length; i1++) {
                        String methodName = "get" + fieldNames[i].substring(0,1).toUpperCase() + fieldNames[i].substring(1);//获取属性的get方法名
                        Method method = user.getClass().getMethod(methodName);
                        Object invoke = method.invoke(user); //获取属性值
                        System.out.println(invoke);
                        rowNew.createCell(i).setCellValue(invoke.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        OutputStream outputStream = null;
        try {
            fileName = fileName+".xls";
            outputStream = new FileOutputStream(fileName);
            wb.write(outputStream);
            File file = new File(fileName);
            uploadFile(file,fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                wb.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private static String uploadFile(File file,String filename){
        try {
            ossClient.putObject(bucketName, "attend/"+filename, file);
            System.out.println("Object：" + filename + "存入OSS成功。");
            return filename;
        } catch (Exception e) {
            System.out.println("保存失败");
            return null;
        }
    }

    private static String[] fieldName(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        String[] fieldNames = new String[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            fieldNames[i] = declaredFields[i].getName(); //通过反射获取属性名
        }
        return fieldNames;
    }
}