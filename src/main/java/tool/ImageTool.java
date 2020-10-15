package tool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author like
 * @since 2020-10-15  12:36
 */
public class ImageTool {
    /**
     * 获取path上的图片,使用BufferedImage保存
     *
     * @param path 需要读取图片的地址
     *
     * @return BufferedImage
     */
    public static BufferedImage getImg(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ImageTool.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
