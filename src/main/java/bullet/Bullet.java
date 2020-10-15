package bullet;

import plane.Plane;

import java.awt.image.BufferedImage;

/**
 * @author like
 * @since 2020-10-15  18:05
 * 子弹类
 */
public class Bullet extends Plane {
    // 当前子弹移动的方向 0->左上角，1->垂直，2->右上角
    private int dir;

    public Bullet(BufferedImage aircraftImg, int ax, int ay, int dir) {
        // 确定子弹的图片
        this.aircraftImg = aircraftImg;
        // 确定子弹的大小
        w = this.aircraftImg.getWidth();
        h = this.aircraftImg.getHeight();
        // 定义子弹的初始位置
        x = ax;
        y = ay;
        // 设定子弹飞的方向
        this.dir = dir;
    }


    // 子弹移动的速度
    @Override
    public void move() {
        if (dir == 0) {
            // 左上角
            x -= 1;
            y -= 18;
        } else if (dir == 1) {
            // 垂直
            y -= 18;
        } else if (dir == 2) {
            // 右上角
            x += 1;
            y -= 18;
        }
    }
}
