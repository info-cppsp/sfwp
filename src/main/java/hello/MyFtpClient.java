package hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class MyFtpClient {

	private static String serverString = "speedtest.tele2.net";
	private static int port = 21;
	private static String userString = "anonymous";
	private static String passwordString = "anonymous";

	public static void uploadFile(File file) throws FileNotFoundException, IOException {

		FTPClient ftp = new FTPClient();

		try (FileInputStream fileInputStream = new FileInputStream(file)) {
		    ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

		    ftp.connect(serverString, port);
		    int reply = ftp.getReplyCode();
		    if (!FTPReply.isPositiveCompletion(reply)) {
		        ftp.disconnect();
		        throw new IOException("Exception in connecting to FTP Server");
		    }

		    ftp.login(userString, passwordString);
		    ftp.enterLocalPassiveMode();
	    	ftp.storeFile("/upload/report.json", fileInputStream);
	    	ftp.logout();

		} finally {
			try {
				ftp.disconnect();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
