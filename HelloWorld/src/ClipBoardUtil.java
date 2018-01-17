import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
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
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.*;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.text.html.HTMLDocument.Iterator;

public class ClipBoardUtil{
    public static void main(String[] args) throws Exception{
    	cutPic("test.png", "result_test_1.jpg", 545, 310, 392, 294);
		cutPic("test.png", "result_test_2.jpg", 88, 310, 392, 294);
    	Image im;
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	System.out.println("我们开始做对比");
		BufferedImage ima1 = ImageIO.read(new File("G:/Github_codes/DifferenceFind/HelloWorld/result_test_1.jpg"));
		BufferedImage ima2 = ImageIO.read(new File("G:/Github_codes/DifferenceFind/HelloWorld/result_test_2.jpg"));
		BufferedImage a = (BufferedImage) getPictureArray(ima1,ima2);
		ImageIO.write(a, "JPG", new File("G:/Github_codes/DifferenceFind/HelloWorld/result.jpg"));
		System.out.println("搞定");
		
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
		BufferedImage result_pic = new BufferedImage(pic1_Width, pic1_Height,BufferedImage.TYPE_INT_RGB);
		
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
				result_pic.setRGB(x, y, 0xff000000
						|abs(rgb_pic1[0]-rgb_pic2[0])|(rgb_pic1[1]-rgb_pic2[1])|(rgb_pic1[2]-rgb_pic2[2]));
				//return (Image)pic1;
				
				
			}
			
		}
		return result_pic;
    	
    }
    
    private static double abs(float f) {
		// TODO Auto-generated method stub
		return 0;
	}
	private static int abs(int f) {
		// TODO Auto-generated method stub
		return 0;
	}
	//Make a program cut the pictures what I want from original picture.
    // from CSDN:7893562
    public static boolean cutPic(String srcFile, String outFile, int x, int y,  
            int width, int height) {  
        FileInputStream is = null;  
        ImageInputStream iis = null;  
        try {  
            // 如果源图片不存在  
            if (!new File(srcFile).exists()) {  
                return false;  
            }  
  
            // 读取图片文件  
            is = new FileInputStream(srcFile);  
  
            // 获取文件格式  
            String ext = srcFile.substring(srcFile.lastIndexOf(".") + 1);  
            System.out.println(ext);
            // ImageReader声称能够解码指定格式  
            java.util.Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);  
            ImageReader reader = it.next();  
  
            // 获取图片流  
            iis = ImageIO.createImageInputStream(is);  
  
            // 输入源中的图像将只按顺序读取  
            reader.setInput(iis, true);  
  
            // 描述如何对流进行解码  
            ImageReadParam param = reader.getDefaultReadParam();  
  
            // 图片裁剪区域  
            Rectangle rect = new Rectangle(x, y, width, height);  
  
            // 提供一个 BufferedImage，将其用作解码像素数据的目标  
            param.setSourceRegion(rect);  
  
            // 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象  
            BufferedImage bi = reader.read(0, param);  
  
            // 保存新图片  
            File tempOutFile = new File(outFile);  
            if (!tempOutFile.exists()) {  
                tempOutFile.mkdirs();  
            }  
            ImageIO.write(bi, ext, new File(outFile));  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        } finally {  
            try {  
                if (is != null) {  
                    is.close();  
                }  
                if (iis != null) {  
                    iis.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
                return false;  
            }  
        }  
    }   
}
