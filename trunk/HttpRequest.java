//package programmingAssignment1;

import java.io.* ;
import java.net.* ;
import java.util.* ;

final class HttpRequest implements Runnable{
    final static String CRLF = "\r\n";
    Socket socket;
	DataOutputStream os;
	static BufferedReader br;
    // Constructor
    
    public HttpRequest(Socket socket) throws Exception {
        this.socket = socket;
    }

    // Implement the run() method of the Runnable interface.
    public void run(){
        try {
            processRequest();
			socket.close();
			//br.close();
        } catch (Exception e) {
            System.out.println(e);
       	}
    }

    private void processRequest() throws Exception{
        // Get a reference to the socket's input and output streams.
            InputStream is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());

	// Set up input stream filters. 
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Get the request line of the HTTP request message.
            String requestLine = br.readLine();
        // Display the request line.
            System.out.println();
            System.out.println(requestLine);
       // Get and display the header lines.
            String headerLine = null;
            while ((headerLine = br.readLine()).length() != 0) {
            	System.out.println(headerLine);
            }
      // Extract the filename from the request line.
            StringTokenizer tokens = new StringTokenizer(requestLine);
            tokens.nextToken();  // skip over the method, which should be "GET"
            String fileName = tokens.nextToken();
     // Prepend a "." so that file request is within the current directory.
            fileName = "." + fileName;
            String _404_img = "/www/404.jpg";
    // Open the requested file.
            FileInputStream fis = null;
            boolean fileExists = true;
            try {
                fis = new FileInputStream(fileName);
            }catch (FileNotFoundException e) {
                fileExists = false;
            }      
			// Writing The Status Line and The Response Content Type
			String statusLine = null;
			String contentTypeLine = null;
			String entityBody = null;
			if (fileExists) {
				statusLine = "HTTP/1.0 200 OK";
				contentTypeLine = "Content-type: " + contentType( fileName ) + CRLF;
			} else {
				statusLine = "HTTP/1.0 404 Not Found";
				contentTypeLine = "Content-type: text/html" + CRLF;
				entityBody = "<HTML>" + "<HEAD><TITLE>Not Found</TITLE><style>.bottom{position:absolute;bottom:0;}</style></HEAD>" + "<BODY><center><img class='bottom' src="+ _404_img +" /></center></BODY></HTML>";
			}
			
			// Send the status line.
			os.writeBytes(statusLine);

			// Send the content type line.
			os.writeBytes(contentTypeLine);

			// Send a blank line to indicate the end of the header lines.
			os.writeBytes(CRLF);
			
			// Send the entity body.
			if (fileExists)	{
				sendBytes(fis, os);
				fis.close();
			} else {
				os.writeBytes(entityBody);
			}
	      		// Close streams and socket.
		        os.close();
		        br.close();
    	
	}
	
	private static void  sendBytes(FileInputStream fis, OutputStream os) throws Exception
	{
	   // Construct a 1K buffer to hold bytes on their way to the socket.
	   byte[] buffer = new byte[1024];
	   int bytes = 0;

	   // Copy requested file into the socket's output stream.
	   while((bytes = fis.read(buffer)) != -1 ) {
	      os.write(buffer, 0, bytes);
	   }
		
	}
	
	
	private static String contentType(String fileName)
	{
		if(fileName.endsWith(".htm") || fileName.endsWith(".html")) {
			return "text/html";
		}
		if(fileName.endsWith(".txt")) {
			return "text/plain";
		}
		if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
			return "image/jpeg";
		}
		if(fileName.endsWith(".gif")) {
			return "image/gif";
		}
		if(fileName.endsWith(".bmp")) {
			return "image/bmp";
		}
		if(fileName.endsWith(".png")) {
			return "image/png";
		}
		if(fileName.endsWith(".xls")) {
			return "application/vnd.ms-excel";
		}
		if(fileName.endsWith(".pdf")) {
			return "application/pdf";
		}
		if(fileName.endsWith(".doc")) {
			return "application/msword";
		}
		if(fileName.endsWith(".ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if(fileName.endsWith(".swf")) {
			return "application/x-shockwave-flash";
		}
		if(fileName.endsWith(".mp3") || fileName.endsWith(".mpga")) {
			return "audio/mpeg";
		}
		if(fileName.endsWith(".ram") || fileName.endsWith(".rm")) {
			return "audio/x-pn-realaudio";
		}
		if(fileName.endsWith(".wav")) {
			return "audio/x-wav";
		}
		if(fileName.endsWith(".rar")) {
			return "application/x-rar-compressed";
		}
		if(fileName.endsWith(".zip")) {
			return "application/zip";
		}
		if(fileName.endsWith(".xml") || fileName.endsWith(".xsl")) {
			return "text/xml";
		}
		if(fileName.endsWith(".css")) {
			return "text/css";
		}
		if(fileName.endsWith(".mov") || fileName.endsWith(".qt")) {
			return "video/quicktime";
		}
		
		return "application/octet-stream";
	}
}        