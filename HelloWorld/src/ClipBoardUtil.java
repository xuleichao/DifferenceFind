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
import java.io.FileInputStream;

import javax.imageio.*;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

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
				//System.out.println(rgb[0]+"----"+ rgb[1]+"----"+rgb[2]);
			}
		}
		FileImageInputStream ima1 = 
				new FileImageInputStream
				(new File("G:/Github_codes/DifferenceFind/HelloWorld/a1.jpg"));
		FileImageInputStream ima2 = 
				new FileImageInputStream
				(new File("G:/Github_codes/DifferenceFind/HelloWorld/a2.jpg"));
		BufferedImage a = (BufferedImage) getPictureArray((BufferedImage)ima1,(BufferedImage)ima2);
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
    /*So now, I shoul to write a program to get the picture
     * This class is to get a array that contains the RGB of the picture*/
    public static Image getPictureArray(BufferedImage pic1, BufferedImage pic2) throws Exception{
		int[] rgb_pic1 = new int[3];
		int[] rgb_pic2 = new int[3];
		
		int pic1_Width = pic1.getWidth();
		int pic2_Width = pic2.getWidth();
		
		int pic1_Height = pic1.getHeight();
		int pic2_Height = pic2.getHeight();
		
		int pic1_minx = pic1.getMinX();
		int pic2_minx = pic2.getMinX();
		
		int pic1_miny = pic1.getMinY();
		int pic2_miny = pic2.getMinY();
		
		//Define a new pic as the result picture
		BufferedImage result_pic = null;
		for(int x=pic1_minx; x<pic1_Width; x++){
			for(int y=pic1_miny; y<pic1_Height; y++){
				//get the RGBs of two pictures
				int RGB_pic1 = pic1.getRGB(x, y);
				int RGB_pic2 = pic2.getRGB(x, y);
				
				rgb_pic1[0] = (RGB_pic1 & 0xff0000) >> 16;
				rgb_pic1[1] = (RGB_pic1 & 0xff00) >> 8;
				rgb_pic1[2] = (RGB_pic1 & 0xff);
				
				rgb_pic2[0] = (RGB_pic2 & 0xff0000) >> 16;
				rgb_pic2[1] = (RGB_pic2 & 0xff00) >> 8;
				rgb_pic2[2] = (RGB_pic2 & 0xff);
				//System.out.println("1--"+rgb_pic1[0]+"---"+rgb_pic1[1]+"---"+rgb_pic1[2]);
				//System.out.println("2--"+rgb_pic2[0]+"---"+rgb_pic2[1]+"---"+rgb_pic2[2]);
				if ((rgb_pic1[0] == rgb_pic2[0])
						&&(rgb_pic1[1] == rgb_pic2[1])
						&&(rgb_pic1[2] == rgb_pic2[2])){
					System.out.println("yes");
				}
				//return (Image)pic1;
				
				
			}
			
		}
		return result_pic;
    	
    }
}
