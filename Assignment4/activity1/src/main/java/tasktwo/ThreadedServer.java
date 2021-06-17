/**
  File: ThreadedServer.java
  Author: Student in Fall 2020B, Jason Sandall
  Description: Server class in package tasktwo.
*/

package tasktwo;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

import taskone.Performer;
import taskone.StringList;

/**
 * Class: ThreadedServer
 * Description: Server tasks.
 */
class ThreadedServer extends Thread {
	
	private Socket conn;
	private int id;
	
	public ThreadedServer(Socket sock, int id) {
		this.conn = sock;
		this .id = id;
	}
	
	public void run() {
        StringList strings = new StringList();
        Performer performer = new Performer(conn, strings);
        performer.doPerform();
        try {
            System.out.println("close socket of client ");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public static void main(String[] args) throws Exception {
    	Socket sock;
    	int id = 0;
        int port;

        if (args.length != 1) {
            // gradle runServer -Pport=9099 -q --console=plain
            System.out.println("Usage: gradle runServer -Pport=9099 -q --console=plain");
            System.exit(1);
        }
        port = -1;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("[Port] must be an integer");
            System.exit(2);
        }
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server Started...");
        while (true) {
            System.out.println("Accepting a Request...");
            sock = server.accept();
            System.out.println("Connected to client " + id);
            ThreadedServer serverThread = new ThreadedServer(sock, id++);
            serverThread.start();
        }
    }
}