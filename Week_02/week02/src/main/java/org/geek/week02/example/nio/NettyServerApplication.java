package org.geek.week02.example.nio;


public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false, 8808);
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
