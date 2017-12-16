import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.*;
import javax.imageio.ImageIO;

public class ClipBoardUtil{
    public static void main(String[] args) throws Exception{
    	Image im;
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
        //setSysClipboardText("dsdfsfsdf");
        im = getImageFromClipboard();
        System.out.println(im);
        boolean flag = ImageIO.write((RenderedImage) im,"gif",out);
        File file = new File ("a.jpg");
        ImageIO.write((RenderedImage) im,"jpg",file);        		
        byte[] b = out.toByteArray(); 
        System.out.println(b);
        
        /*The codes below, we will get the picture's RGB, 
         * first of all, we will get the picture's scale*/
        int[] rgb = new int[3];// a array for RGB
		int width = ((RenderedImage) im).getWidth();
		int height = ((RenderedImage) im).getHeight();
		int minx = ((RenderedImage) im).getMinX();
		int miny = ((RenderedImage) im).getMinY();
		//System.out.println(width);
		for(int y=miny;y<height;y++){
			for(int x=minx;x<width;x++){
				//to aquire the RGB of the point
				int pointRGB = ((BufferedImage) im).getRGB(x,y);
				//System.out.println(pointRGB);
				rgb[0] = (pointRGB & 0xff0000) >> 16;
				rgb[1] = (pointRGB & 0xff00) >> 8;
				rgb[2] = (pointRGB & 0xff); 
				System.out.println(rgb[0]+"----"+ rgb[1]+"----"+rgb[2]);
			}
		}
    }
    public static void setSysClipboardText(String writeMe){  
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();  
        Transferable tText = new StringSelection(writeMe);  
        clip.setContents(tText, null);  
    } 
    public static Image getImageFromClipboard() throws Exception {  
        Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();  
        Transferable cc = sysc.getContents(null);  
        if (cc == null)  
            return null;  
        else if (cc.isDataFlavorSupported(DataFlavor.imageFlavor))  
            return (Image) cc.getTransferData(DataFlavor.imageFlavor);  
        return null;  
    }  
}
