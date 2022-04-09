package com.tute.wjl.service;

import com.tute.wjl.entity.Message;
import javax.sound.sampled.*;

public class VoiceService {
    private TargetDataLine line;
    private SourceDataLine sourceLine;
    private static VoiceService instance;
    final int bufSize = 16384;



    public VoiceService(){
        //AudioFormat(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian)
        AudioFormat format =new AudioFormat(8000,16,1,true,false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);
        AudioFormat format2 =new AudioFormat(8000,16,1,true,true);
        DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class,format2);
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);

            line.open(format, line.getBufferSize());
            sourceLine.open(format, bufSize);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static VoiceService getInstance() {
        if(instance==null) {//先判断是否为null 后上锁进行初始化
            synchronized (VoiceService.class) {
                if (instance == null)//将对象上锁之后再次判断 是否有别的线程初始化了
                    instance = new VoiceService();
            }
        }
        return instance;
    }

    public void sendVoice(Message message) {
        byte[] data = new byte[1024];//此处的1024可以情况进行调整，应跟下面的1024应保持一致
        line.read(data, 0, 1024);//取数据(1024)的大小直接关系到传输的速度，一般越小越快，
        message.setFileContent(data);
    }

    public void stop(){
        line.stop();
        line.close();
    }

    public void startCapture(){
        line.start();
    }

    public void startPlay(){
        sourceLine.start();
    }

    public void playVoice(Message message){
        sourceLine.write(message.getFileContent(), 0,message.getFileContent().length);
    }

}
