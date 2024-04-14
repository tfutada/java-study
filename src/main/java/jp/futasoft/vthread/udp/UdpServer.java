package jp.futasoft.vthread.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class UdpServer {
    public static void main(String[] args) {
        final int serverPort = 5106;
        byte[] buffer = new byte[1024]; // Buffer size can be adjusted as needed

        try (DatagramSocket socket = new DatagramSocket(serverPort)) {
            socket.setReceiveBufferSize(1024 * 10);
            System.out.println("Java asynchronous UDP server has started. Port: " + serverPort);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // Receive packet from client

                Thread.startVirtualThread(() -> { // Handle each packet in a separate virtual thread
                    try {
                        String data = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
                        System.out.println("Received: " + data);

                        // Simulate processing time
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Thread was interrupted");
                        }

                        String responseData = "Received: " + data;
                        DatagramPacket responsePacket = new DatagramPacket(
                                responseData.getBytes(StandardCharsets.UTF_8),
                                responseData.getBytes(StandardCharsets.UTF_8).length,
                                packet.getAddress(),
                                packet.getPort());

                        socket.send(responsePacket); // Send response back to client
                    } catch (IOException e) {
                        System.err.println("Error handling a packet: " + e.getMessage());
                    }
                });
            }
        } catch (IOException e) {
            System.err.println("Could not start server: " + e.getMessage());
        }
    }
}
