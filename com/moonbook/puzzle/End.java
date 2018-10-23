/*     */
package com.moonbook.puzzle;
/*     */ 
/*     */

import com.moonbook.puzzle.input.Keyboard;
import com.moonbook.puzzle.sounds.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
/*     */ public class End extends Canvas
/*     */ implements Runnable
/*     */ {
    /*     */   private static final long serialVersionUID = 1L;
    /*  22 */   private static int width = 300;
    /*  23 */   private static int height = width / 16 * 9;
    /*  24 */   private static int scale = 3;
    /*  25 */   private static String title = "Game Over";
    /*     */   private boolean running;
    /*     */   private JFrame frame;
    /*     */   private Thread thread;
    /*     */   private Keyboard key;
    /*  30 */   private BufferedImage image = new BufferedImage(width, height,
/*  31 */     1);
    /*     */   private BufferedWriter bw;

    /*     */
/*     */
    public End()
/*     */     throws IOException
/*     */ {
/*  35 */
        Dimension size = new Dimension(width * scale, height * scale);
/*  36 */
        setPreferredSize(size);
/*  37 */
        isDoubleBuffered();
/*  38 */
        this.frame = new JFrame();
/*  39 */
        this.frame.setResizable(false);
/*  40 */
        this.frame.setTitle(title);
/*  41 */
        this.frame.add(this);
/*  42 */
        this.frame.setDefaultCloseOperation(3);
/*  43 */
        this.frame.pack();
/*  44 */
        this.frame.setVisible(true);
/*  45 */
        this.frame.setLocationRelativeTo(null);
/*     */ 
/*  47 */
        Sounds.SOOTHING.loop();
/*  48 */
        Sounds.BOSS.stop();
/*  49 */
        this.key = new Keyboard();
/*  50 */
        addKeyListener(this.key);
/*     */
    }

    /*     */
/*     */
    public void run()
/*     */ {
/*  57 */
        long lastTime = System.nanoTime();
/*  58 */
        long timer = System.currentTimeMillis();
/*  59 */
        double ns = 16666666.666666666D;
/*  60 */
        double delta = 0.0D;
/*  61 */
        int frames = 0;
/*  62 */
        while (this.running) {
/*  63 */
            long now = System.nanoTime();
/*  64 */
            delta += (now - lastTime) / 16666666.666666666D;
/*  65 */
            lastTime = now;
/*  66 */
            while (delta >= 1.0D) {
/*     */
                try {
/*  68 */
                    update();
/*     */
                }
/*     */ catch (IOException e) {
/*  71 */
                    e.printStackTrace();
/*     */
                }
/*  73 */
                render();
/*  74 */
                delta -= 1.0D;
/*     */
            }
/*  76 */
            frames++;
/*     */ 
/*  78 */
            if (System.currentTimeMillis() - timer > 1000L) {
/*  79 */
                timer += 1000L;
/*  80 */
                this.frame.setTitle(title + " | Frames: " + frames);
/*  81 */
                frames = 0;
/*     */
            }
/*     */ 
/*     */
        }
/*     */ 
/*  86 */
        stop();
/*     */
    }

    /*     */
/*     */
    public synchronized void start()
/*     */ {
/*  91 */
        this.running = true;
/*  92 */
        this.thread = new Thread(this, "Display");
/*  93 */
        this.thread.start();
/*     */
    }

    /*     */
/*     */
    public synchronized void stop()
/*     */ {
/*  98 */
        this.running = false;
/*     */
        try {
/* 100 */
            this.thread.join();
/*     */
        } catch (InterruptedException e) {
/* 102 */
            e.printStackTrace();
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void update() throws IOException
/*     */ {
/* 108 */
        this.key.update();
/*     */ 
/* 123 */
        if (Game.score > Game.highscore) {
/* 124 */
            this.bw = new BufferedWriter(new FileWriter("res/highscore.txt"));
/*     */
            try {
/* 126 */
                this.bw.write(Game.score);
/*     */
            }
/*     */ catch (IOException e) {
/* 129 */
                e.printStackTrace();
/*     */
            } finally {
/* 131 */
                this.bw.close();
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void render() {
/* 137 */
        BufferStrategy bs = getBufferStrategy();
/* 138 */
        if (bs == null) {
/* 139 */
            createBufferStrategy(3);
/* 140 */
            requestFocus();
/* 141 */
            return;
/*     */
        }
/* 143 */
        Graphics g = bs.getDrawGraphics();
/* 144 */
        g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
/* 145 */
        Font myFont = new Font("Arial", 1, 47);
/* 146 */
        g.setFont(myFont);
/* 147 */
        g.setColor(Color.red);
/* 148 */
        g.drawString("YOU LOSE!!!!", 300, 200);
/* 149 */
        g.drawString("Score: " + Game.score, 300, 255);
/*     */ 
/* 151 */
        g.drawString("Highscore: " + Game.highscore, 300, 310);
/* 152 */
        g.setColor(Color.GREEN);
/* 153 */
        if (Game.score > Game.highscore)
/* 154 */ g.drawString("New Highscore!!!", 260, 145);
/* 155 */
        g.dispose();
/* 156 */
        bs.show();
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.End
 * JD-Core Version:    0.6.2
 */