package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    Matcher matcher = Pattern.compile("msg=\\S{1,}").matcher(str);
                    while (matcher.find()) {
                        String rsl = matcher.group();
                        String msg = rsl.substring(4);
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        String answer = switch (msg) {
                            case "Hello":
                                yield "Hello";
                            case "Exit":
                                server.close();
                                yield "Shut down the server";
                            default:
                                yield msg;
                        };
                        out.write(answer.getBytes());
                        out.flush();
                    }
                }
            }
        }
    }
}