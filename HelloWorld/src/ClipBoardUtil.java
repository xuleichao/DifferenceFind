import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
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
