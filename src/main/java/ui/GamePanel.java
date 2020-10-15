package ui;

import bullet.Bullet;
import plane.Aircraft;
import plane.BadAircraft;
import tool.ImageTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author like
 * @since 2020-10-15  11:32
 * 游戏的面板
 * 1.继承JPanel
 * 2.
 */
public class GamePanel extends JPanel {
    // 背景图片
    private final BufferedImage bgi;
    // 用户飞机
    private final Aircraft aircraft;
    // 敌机集合
    private final List<BadAircraft> badAircraftList = new ArrayList<>();
    // 子弹集合
    private final List<Bullet> bulletList = new ArrayList<>();
    int badAircraftSum = 0;
    int bulletNum = 0;
    int secondBadAircraft = 0;
    int firstBadAircraft = 0;
    // 分数
    private int score;

    /**
     * 构造函数
     */
    public GamePanel(GameFrame frame) {
        // 1.读取背景毒品
        bgi = ImageTool.getImg("/img/background.png");

        // 2.初始化飞机
        // - 用户操作的飞机
        aircraft = new Aircraft(ImageTool.getImg("/img/me1.png"));

        // 3.使用鼠标来控制飞机
        // - 创建鼠标适配器
        MouseAdapter ma = new MouseAdapter() {
            // - 确定鼠标需要监听的事件
            //      a.鼠标移动  b.鼠标点击 c.鼠标按下去  d.鼠标移入窗口 e.鼠标移出窗口
            @Override
            public void mouseMoved(MouseEvent e) {
                // - 鼠标控制飞机移动
                aircraft.move(e);
                // - 重新绘制飞机的位置
                repaint();
            }
        };
        // - 注册鼠标适配器
        addMouseListener(ma);
        addMouseMotionListener(ma);

        // 4.使用键盘适配器
        KeyAdapter ka = new KeyAdapter() {
            // - 确定键盘需要监听的事件
            //      a.按下键盘按键
            @Override
            public void keyPressed(KeyEvent e) {
                // - 键盘控制飞机移动
                aircraft.move(e, 25);
                // - 重新渲染飞机的位置
                repaint();
            }
        };
        // - 注册键盘适配器到游戏窗口中，直接注册到界面中无效
        frame.addKeyListener(ka);
    }

    /**
     * 专用画图方法
     *
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        // 1.添加图片 - 背景
        g.drawImage(bgi, 0, 0, null);

        // 2.添加飞机

        // - 用户操作的飞机
        g.drawImage(aircraft.getAircraftImg(), aircraft.getX(), aircraft.getY(), aircraft.getW(), aircraft.getH(),
                    null);
        // - 敌机（小兵）
        for (int i = 0; i < badAircraftList.size(); i++) {
            g.drawImage(badAircraftList.get(i).getAircraftImg(), badAircraftList.get(i).getX(),
                        badAircraftList.get(i).getY(), badAircraftList.get(i).getW(), badAircraftList.get(i).getH(),
                        null);
        }
        // - 添加子弹
        for (int i = 0; i < bulletList.size(); i++) {
            g.drawImage(bulletList.get(i).getAircraftImg(), bulletList.get(i).getX(), bulletList.get(i).getY(),
                        bulletList.get(i).getW(), bulletList.get(i).getH(), null);
        }

        // 3.添加分数
        g.setColor(Color.black);
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("分数：" + score, 10, 30);
    }

    /**
     * 游戏开始
     */
    public void action() {
        // 启动一个线程
        new Thread(() -> {
            while (true) {
                // 生成敌机
                generateBadAircraft();
                // 敌机移动
                moveBadAircraft();

                // 生成子弹
                generateBullet();
                // 子弹移动
                moveBullet();

                // 用户的飞机的子弹是否击中的敌机
                shootBad();

                // 重新渲染游戏界面
                repaint();

                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "action").start();
    }

    /**
     * 判断是否击中,增加分数。
     */
    private void shootBad() {
        // 遍历所有的子弹 (不能用增强for，有ConcurrentModificationException，因为增强for是使用了迭代器)
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            // 遍历所有的敌机
            for (int j = 0; j < badAircraftList.size(); j++) {
                BadAircraft badAircraft = badAircraftList.get(j);
                // 如果击中，删除并增加粉丝
                if (badAircraft.shootBy(bullet)) {
                    // - 敌机删除
                    badAircraftList.remove(badAircraft);
                    // - 增加分数
                    this.score += badAircraft.getScore();
                    // - 子弹删除
                    bulletList.remove(bullet);
                }
            }
        }
    }

    /**
     * 移动子弹
     */
    private void moveBullet() {
        for (Bullet bullet : bulletList) {
            bullet.move();
        }
    }

    /**
     * 生成子弹
     */
    private void generateBullet() {
        bulletNum++;
        if (bulletNum == 10) {
            // 创建子弹对象
             Bullet bullet1 = new Bullet(ImageTool.getImg("/img/bullet1.png"), aircraft.getX() + 5, aircraft.getY()
            , 0);
            Bullet bullet2 =
                    new Bullet(ImageTool.getImg("/img/bullet1.png"), aircraft.getX() + 45, aircraft.getY() - 20, 1);
             Bullet bullet3 = new Bullet(ImageTool.getImg("/img/bullet1.png"), aircraft.getX() + 90, aircraft.getY
            (), 2);
            // 添加子弹
            bulletList.add(bullet1);
            bulletList.add(bullet2);
              bulletList.add(bullet3);

            bulletNum = 0;
        }
    }

    /**
     * 敌机移动
     */
    private void moveBadAircraft() {
        for (BadAircraft ba : badAircraftList) {
            // 移动
            ba.move();
        }
    }

    /**
     * 生成敌机
     * - 根据当前方法运行了多少次，来决定生成哪种敌机
     */
    private void generateBadAircraft() {
        // 生成敌机并保存
        badAircraftSum++;
        // 当一个敌机移动二十次就生成另一个新的敌机(小兵)
        if (badAircraftSum >= 20) {
            BadAircraft badAircraft = new BadAircraft(ImageTool.getImg("/img/enemy1.png"), 1);
            badAircraftList.add(badAircraft);

            badAircraftSum = 0;
            secondBadAircraft++;
        }
        // 生成第二大的战机
        if (secondBadAircraft >= 5) {
            BadAircraft badAircraft = new BadAircraft(ImageTool.getImg("/img/enemy2.png"), 10);
            badAircraftList.add(badAircraft);

            secondBadAircraft = 0;
            firstBadAircraft++;
        }
        // 生成最大的敌机
        if (firstBadAircraft >= 5) {
            BadAircraft badAircraft = new BadAircraft(ImageTool.getImg("/img/enemy3.png"), 100);
            // 设置移动速度
            badAircraft.setStep(1);
            badAircraftList.add(badAircraft);

            firstBadAircraft = 0;
        }
    }
}
