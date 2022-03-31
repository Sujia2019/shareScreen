package com.tute.wjl.context;


import com.tute.wjl.entity.User;
import com.tute.wjl.ui.ErrorTips;
import lombok.Data;

import javax.swing.*;

// 用于上下文传输数据 保存的临时对象 应是全局只创建一个
@Data
public class FrameContext {
    private JLabel label;
    private ErrorTips error;
    private JFrame shareFrame;
    private JFrame loginFrame;
}
