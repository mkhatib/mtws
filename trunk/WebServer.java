//package programmingAssignment1;

import java.io.* ;
import java.net.* ;
import java.util.*;

public class WebServer {
    public static void main(String argv[]) throws Exception {
        //set the port number - the port number isn't specified so we used the following.
			System.out.println(argv[0]);
            int portNo = (new Integer(argv[0])).intValue();
        //establish the listen socket.
            ServerSocket listenSocket = new ServerSocket(portNo);
        
        while (true) {
            // Listen for a TCP connection request.  
                Socket connectionSocket = listenSocket.accept();
            // Construct an object to process the HTTP request message.
                HttpRequest request = new HttpRequest(connectionSocket);
            // Create a new thread to process the request.
                Thread thread = new Thread(request);
            // Start the thread.
                thread.start();
        }
    }
}
    

