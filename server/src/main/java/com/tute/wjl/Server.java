package com.tute.wjl;
import com.tute.wjl.net.NettyServer;

public class Server {
    public static void main(String[] args) throws Exception {
        NettyServer server = new NettyServer();
        server.init(8888);
    }
}
