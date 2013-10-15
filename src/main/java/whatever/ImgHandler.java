package whatever;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;

@Named
@RequestScoped
public class ImgHandler {

  private final static int COLOR_THUMB_WIDTH = 200;
  private final static int COLOR_THUMB_HEIGHT = 100;
  
  public byte[] getColorCode() {
    return "ffff00".getBytes(); //I could pass a String but I want to reproduce a certain error, so it's going to be a [B
  }
  
  
  public void paint(OutputStream out, Object data) throws IOException {
    String colorCode = new String((byte[])data);
    int rgb = Integer.parseInt(colorCode, 16);
    Color color = new Color(rgb);
    
    //obtaining graphics
    BufferedImage img = new BufferedImage(COLOR_THUMB_WIDTH, COLOR_THUMB_HEIGHT, BufferedImage.TYPE_INT_RGB);
    Graphics g = img.getGraphics();

    //fill
    g.setColor(color);
    g.fillRect(0, 0, COLOR_THUMB_WIDTH, COLOR_THUMB_HEIGHT);

    //border
    g.setColor(Color.black);
    g.drawRect(0, 0, COLOR_THUMB_WIDTH-1, COLOR_THUMB_HEIGHT-1);
    
    ImageIO.write(img, "png", out);
  }
}
