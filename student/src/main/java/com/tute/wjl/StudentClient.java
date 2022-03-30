package com.tute.wjl;

import com.tute.wjl.net.NettyClient;
import com.tute.wjl.ui.Client;

public class StudentClient {
    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient(new Client());
        client.init("localhost",8888);
    }
}
