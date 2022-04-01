package com.tute.wjl;
public class TeacherClient {
    public static void main(String[] args) throws Exception {
        ClientApplication client = new ClientApplication();
        client.start("localhost",8888);
    }
}
