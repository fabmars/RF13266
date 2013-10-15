package whatever;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HrefHandler {

  public void download(OutputStream out, Object data) throws IOException {
    String fileName = (String)data;
    InputStream in = HrefHandler.class.getResourceAsStream(fileName);
    copyStreams(in, out);
    in.close();
  }
  
  private final static void copyStreams(InputStream inputStream, OutputStream outputStream) throws IOException {
    int read = -1;
    byte[] buffer = new byte[32*1024];

    inputStream = new BufferedInputStream(inputStream);
    
    while ((read = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, read);
    }
  }
}
