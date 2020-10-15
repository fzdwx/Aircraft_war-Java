package plane;

import java.awt.image.BufferedImage;

/**
 * @author like
 * @since 2020-10-15  14:03
 * 用户操作的飞机
 */
public class Aircraft extends Plane {

    /**
     * 构造器
     *
     * @param aircraftImg 飞机的图片
     */
    public Aircraft(BufferedImage aircraftImg) {
        // 定义飞机的图片
        this.aircraftImg = aircraftImg;
        // 定义飞机的初始位置
        this.x = 180;
        this.y = 500;
        // 定义飞机的宽和高
        this.w = aircraftImg.getWidth();
        this.h = aircraftImg.getHeight();
    }

}
