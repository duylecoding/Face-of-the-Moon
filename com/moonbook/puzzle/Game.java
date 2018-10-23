/*     */
package com.moonbook.puzzle;
/*     */ 
/*     */

import com.moonbook.puzzle.entity.mob.Player;
import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.input.Keyboard;
import com.moonbook.puzzle.input.Mouse;
import com.moonbook.puzzle.level.Level;
import com.moonbook.puzzle.level.TileCoord;
import com.moonbook.puzzle.sounds.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public class Game extends Canvas
/*     */ implements Runnable
/*     */ {
    /*     */   private static final long serialVersionUID = 1L;
    /*  37 */   public static int score = 0;
    /*  38 */   public static int highscore = 0;
    /*  39 */   public static String title = "Face of the Moon";
    /*  34 */   private static int width = 300;
    /*  35 */   private static int height = width / 16 * 9;
    /*  36 */   private static int scale = 3;
    /*     */   private static Scanner scan;
    /*     */   private Thread thread;
    /*     */   private JFrame frame;
    /*     */   private Keyboard key;
    /*     */   private Level level;
    /*     */   private Player player;
    /*  46 */   private boolean running = false;
    /*     */   private TileCoord playerspawn;
    /*     */   private Screen screen;
    /*  53 */   private BufferedImage image = new BufferedImage(width, height,
/*  54 */     1);
    /*     */
/*  56 */   private int[] pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer())
/*  56 */.getData();
    /*     */
/* 148 */   private int time = 0;

    /*     */
/*     */
    public Game()
/*     */     throws IOException
/*     */ {
/*  59 */
        Dimension size = new Dimension(width * scale, height * scale);
/*  60 */
        setPreferredSize(size);
/*     */ 
/*  62 */
        this.frame = new JFrame();
/*  63 */
        this.frame.setResizable(false);
/*  64 */
        this.frame.setTitle(title);
/*  65 */
        this.frame.add(this);
/*  66 */
        this.frame.pack();
/*  67 */
        this.frame.setDefaultCloseOperation(3);
/*  68 */
        this.frame.setLocationRelativeTo(null);
/*  69 */
        this.frame.setVisible(true);
/*     */ 
/*  71 */
        this.screen = new Screen(width, height);
/*  72 */
        this.key = new Keyboard();
/*  73 */
        this.level = Level.level1;
/*  74 */
        this.playerspawn = new TileCoord(14, 13);
/*  75 */
        this.player = new Player(this.playerspawn.x(), this.playerspawn.y(), this.key);
/*  76 */
        this.level.add(this.player);
/*     */ 
/*  78 */
        Sounds.BOSS.loop();
/*     */ 
/*  80 */
        addKeyListener(this.key);
/*     */ 
/*  82 */
        Mouse mouse = new Mouse();
/*  83 */
        addMouseListener(mouse);
/*  84 */
        addMouseMotionListener(mouse);
/*     */
    }

    /*     */
/*     */
    public static int getWindowWidth()
/*     */ {
/*  90 */
        return width * scale;
/*     */
    }

    /*     */
/*     */
    public static int getWindowHeight() {
/*  94 */
        return height * scale;
/*     */
    }

    /*     */
/*     */
    public static void addScore(int amount)
/*     */ {
/* 209 */
        score += amount;
/*     */
    }

    /*     */
/*     */
    public static void main(String[] args) throws IOException {
/* 213 */
        Game game = new Game();
/* 214 */
        game.start();
/*     */
/* 216 */
        scan = new Scanner(new File("res/highscore.txt"));
/* 217 */
        int s = 0;
/* 218 */
        if (scan.hasNext())
/* 219 */ s = scan.nextInt();
/* 220 */
        if (s > highscore) highscore = s;
/* 221 */
        scan.close();
/*     */
    }

    /*     */
/*     */
    public void run()
/*     */ {
/*  99 */
        long lastTime = System.nanoTime();
/* 100 */
        long timer = System.currentTimeMillis();
/* 101 */
        double ns = 16666666.666666666D;
/* 102 */
        double delta = 0.0D;
/* 103 */
        int frames = 0;
/* 104 */
        while (this.running) {
/* 105 */
            long now = System.nanoTime();
/* 106 */
            delta += (now - lastTime) / 16666666.666666666D;
/* 107 */
            lastTime = now;
/* 108 */
            while (delta >= 1.0D) {
/*     */
                try {
/* 110 */
                    update();
/*     */
                }
/*     */ catch (IOException e) {
/* 113 */
                    e.printStackTrace();
/*     */
                }
/*     */
/* 116 */
                delta -= 1.0D;
/*     */
            }
/*     */
/* 119 */
            if (this.time % 1 == 0) render();
/* 120 */
            frames++;
/*     */
/* 122 */
            if (System.currentTimeMillis() - timer > 1000L) {
/* 123 */
                timer += 1000L;
/* 124 */
                this.frame.setTitle(title + " | fps: " + frames + " Score: " + score);
/* 125 */
                frames = 0;
/*     */
            }
/*     */
/*     */
        }
/*     */
/* 130 */
        stop();
/*     */
    }

    /*     */
/*     */
    public synchronized void start() {
/* 134 */
        this.running = true;
/* 135 */
        this.thread = new Thread(this, "Display");
/* 136 */
        this.thread.start();
/*     */
    }

    /*     */
/*     */
    public synchronized void stop() {
/* 140 */
        this.running = false;
/*     */
        try {
/* 142 */
            this.thread.join();
/*     */
        } catch (InterruptedException e) {
/* 144 */
            e.printStackTrace();
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void update()
/*     */     throws IOException
/*     */ {
/* 151 */
        com.moonbook.puzzle.entity.mob.BlueTotem.count = 0;
/* 152 */
        this.key.update();
/* 153 */
        this.level.update();
/* 154 */
        this.time += 1;
/* 155 */
        if (score > 25) com.moonbook.puzzle.entity.projectile.BasicAttack.FIRE_RATE = 25;
/* 156 */
        else if (score > 50) com.moonbook.puzzle.entity.projectile.BasicAttack.FIRE_RATE = 15;
/* 157 */
        else if (score > 100) com.moonbook.puzzle.entity.projectile.BasicAttack.FIRE_RATE = 10;
/*     */
/* 159 */
        if (this.player.isDamaged()) {
/* 160 */
            if (this.time % 2 == 0)
/* 161 */ this.player.takeDmg(1);
/* 162 */
            this.player.resetDmg();
/*     */
        }
/* 164 */
        if (this.player.getHealth() <= 0) {
/* 165 */
            this.running = false;
/* 166 */
            this.player.remove();
/* 167 */
            this.frame.setVisible(false);
/* 168 */
            End end = new End();
/* 169 */
            end.start();
/* 170 */
            stop();
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void render()
/*     */ {
/* 177 */
        BufferStrategy bs = getBufferStrategy();
/* 178 */
        if (bs == null) {
/* 179 */
            createBufferStrategy(3);
/* 180 */
            requestFocus();
/* 181 */
            return;
/*     */
        }
/*     */
/* 184 */
        this.screen.clear();
/* 185 */
        double xScroll = this.player.getX() - this.screen.width / 2;
/* 186 */
        double yScroll = this.player.getY() - this.screen.height / 2;
/* 187 */
        this.level.render((int) xScroll, (int) yScroll, this.screen);
/*     */
/* 189 */
        for (int i = 0; i < this.pixels.length; i++) {
/* 190 */
            this.pixels[i] = this.screen.pixels[i];
/*     */
        }
/*     */
/* 193 */
        Graphics g = bs.getDrawGraphics();
/* 194 */
        g.setFont(new Font("Arial", 1, 28));
/* 195 */
        g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
/* 196 */
        g.setColor(Color.gray);
/*     */
/* 198 */
        g.drawRect(15, 15, 400, 23);
/* 199 */
        g.setColor(Color.yellow);
/* 200 */
        g.fillRect(15, 15, this.player.getHealth() * 2, 23);
/* 201 */
        g.setColor(Color.cyan);
/* 202 */
        g.drawString("Stuns: " + Player.stuns, 700, 30);
/* 203 */
        g.dispose();
/* 204 */
        bs.show();
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.Game
 * JD-Core Version:    0.6.2
 */