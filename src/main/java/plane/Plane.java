package plane;

import bullet.Bullet;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * @author like
 * @since 2020-10-15  15:33
 * 飞机父类
 */
public abstract class Plane {
    // 飞机图片
    protected BufferedImage aircraftImg;
    // 飞机横坐标
    protected int x;
    // 飞机纵坐标
    protected int y;
    // 飞机宽度
    protected int w;
    // 飞机高度
    protected int h;

    /**
     * 敌机移动
     */
    public void move() {

    }

    /**
     * 移动飞机，使用鼠标控制
     *
     * @param e 鼠标事件
     */
    public void move(MouseEvent e) {
        //a.获取鼠标的位置
        int mx = e.getX();
        int my = e.getY();
        //b.飞机移动到鼠标的位置
        setX(mx - w / 2);
        setY(my - h / 2);
    }

    /**
     * 移动飞机，使用键盘控制
     *
     * @param e    键盘事件
     * @param step 移动的距离
     */
    public void move(KeyEvent e, int step) {
        // 判断用户输入的键盘
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            // 向上移动
            setY(y - step);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // 向下移动
            setY(y + step);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // 向左移动
            setX(x - step);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // 向右移动
            setX(x + step);
        }
    }

    /**
     * 判断是否碰撞到了飞机
     *
     * @param bullet 打击的子弹
     *
     * @return 是否打击到了子弹
     */
    public boolean shootBy(Bullet bullet) {
        return this.x <= bullet.x + bullet.w && this.x >= bullet.x - this.w && this.y <= bullet.y + bullet.h &&
                this.y > bullet.y - this.h;

    }



































    /**
     * Gets aircraft img.
     *
     * @return the aircraft img
     */
    public BufferedImage getAircraftImg() {
        return aircraftImg;
    }

    /**
     * Sets aircraft img.
     *
     * @param aircraftImg the aircraft img
     */
    public void setAircraftImg(BufferedImage aircraftImg) {
        this.aircraftImg = aircraftImg;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets w.
     *
     * @return the w
     */
    public int getW() {
        return w;
    }

    /**
     * Sets w.
     *
     * @param w the w
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * Gets h.
     *
     * @return the h
     */
    public int getH() {
        return h;
    }

    /**
     * Sets h.
     *
     * @param h the h
     */
    public void setH(int h) {
        this.h = h;
    }
}
