package com.tute.wjl;

public class StudentClient {
    public static void main(String[] args) throws Exception {
        ClientApplication client = new ClientApplication();
        client.start("localhost",8888);

    }
}
