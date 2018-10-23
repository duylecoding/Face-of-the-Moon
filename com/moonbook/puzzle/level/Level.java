/*     */
package com.moonbook.puzzle.level;
/*     */ 
/*     */

import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.entity.mob.Player;
import com.moonbook.puzzle.entity.particle.Particle;
import com.moonbook.puzzle.entity.projectile.Projectile;
import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.level.tile.Tile;
import com.moonbook.puzzle.util.Vector2i;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
/*     */ public class Level
/*     */ {
    /*     */
/*  39 */   public static Level spawn = new SpawnLevel("/levels/spawn.png");
    /*  40 */   public static Level level1 = new SpawnLevel("/levels/LEVEL.png");
    /*     */   protected int width;
    /*     */   protected int height;
    /*     */   protected int[] tilesInt;
    /*     */   protected int[] tiles;
    /*     */   protected int tile_size;
    /*  23 */   private List<Entity> entities = new ArrayList();
    /*  24 */   private List<Projectile> projectiles = new ArrayList();
    /*  25 */   private List<Particle> particles = new ArrayList();
    /*     */
/*  27 */   private List<Player> players = new ArrayList();
    /*     */
/*  29 */   private Comparator<Node> nodeSorter = new Comparator<Node>()
/*     */ {
        /*     */
        public int compare(Node n0, Node n1) {
/*  32 */
            if (n1.fCost < n0.fCost) return 1;
/*  33 */
            if (n1.fCost > n0.fCost) return -1;
/*     */
/*  35 */
            return 0;
/*     */
        }
/*  29 */
    };

    /*     */
/*     */
    public Level(int width, int height) {
/*  43 */
        this.width = width;
/*  44 */
        this.height = height;
/*  45 */
        this.tilesInt = new int[width * height];
/*  46 */
        generateLevel();
/*     */
    }

    /*     */
/*     */
    public Level(String path) {
/*  50 */
        loadLevel(path);
/*  51 */
        generateLevel();
/*     */
    }

    /*     */
/*     */
    protected void generateLevel()
/*     */ {
/*  56 */
        for (int y = 0; y < 64; y++) {
/*  57 */
            for (int x = 0; x < 64; x++) {
/*  58 */
                getTile(x, y);
/*     */
            }
/*     */
        }
/*  61 */
        this.tile_size = 16;
/*     */
    }

    /*     */
/*     */
    protected void loadLevel(String path) {
/*     */
    }

    /*     */
/*     */
    public void update() {
/*  68 */
        for (int i = 0; i < this.entities.size(); i++) {
/*  69 */
            ((Entity) this.entities.get(i)).update();
/*     */
        }
/*  71 */
        for (int i = 0; i < this.projectiles.size(); i++) {
/*  72 */
            ((Projectile) this.projectiles.get(i)).update();
/*     */
        }
/*  74 */
        for (int i = 0; i < this.particles.size(); i++) {
/*  75 */
            ((Particle) this.particles.get(i)).update();
/*     */
        }
/*  77 */
        for (int i = 0; i < this.players.size(); i++) {
/*  78 */
            ((Player) this.players.get(i)).update();
/*     */
        }
/*  80 */
        remove();
/*     */
    }

    /*     */
/*     */
    private void remove() {
/*  84 */
        for (int i = 0; i < this.entities.size(); i++) {
/*  85 */
            if (((Entity) this.entities.get(i)).isRemoved()) this.entities.remove(i);
/*     */
        }
/*  87 */
        for (int i = 0; i < this.projectiles.size(); i++) {
/*  88 */
            if (((Projectile) this.projectiles.get(i)).isRemoved()) this.projectiles.remove(i);
/*     */
        }
/*  90 */
        for (int i = 0; i < this.particles.size(); i++) {
/*  91 */
            if (((Particle) this.particles.get(i)).isRemoved()) this.particles.remove(i);
/*     */
        }
/*  93 */
        for (int i = 0; i < this.players.size(); i++)
/*  94 */
            if (((Player) this.players.get(i)).isRemoved()) this.players.remove(i);
/*     */
    }

    /*     */
/*     */
    public List<Projectile> getProjectiles()
/*     */ {
/* 101 */
        return this.projectiles;
/*     */
    }

    /*     */
/*     */
    public List<Entity> getEntities() {
/* 105 */
        return this.entities;
/*     */
    }

    /*     */
/*     */
    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
/* 109 */
        boolean solid = false;
/* 110 */
        for (int c = 0; c < 4; c++) {
/* 111 */
            int xt = x - c % 2 * size + xOffset >> 4;
/* 112 */
            int yt = y - c / 2 * size + yOffset >> 4;
/* 113 */
            if (getTile(xt, yt).solid()) solid = true;
/*     */
        }
/* 115 */
        return solid;
/*     */
    }

    /*     */
/*     */
    public void render(int xScroll, int yScroll, Screen screen)
/*     */ {
/* 120 */
        screen.setOffset(xScroll, yScroll);
/* 121 */
        int x0 = xScroll >> 4;
/* 122 */
        int x1 = xScroll + screen.width + 16 >> 4;
/* 123 */
        int y0 = yScroll >> 4;
/* 124 */
        int y1 = yScroll + screen.height + 16 >> 4;
/*     */ 
/* 126 */
        for (int y = y0; y < y1; y++) {
/* 127 */
            for (int x = x0; x < x1; x++) {
/* 128 */
                getTile(x, y).render(x, y, screen);
/*     */
            }
/*     */
        }
/*     */ 
/* 132 */
        for (int i = 0; i < this.entities.size(); i++) {
/* 133 */
            ((Entity) this.entities.get(i)).render(screen);
/*     */
        }
/* 135 */
        for (int i = 0; i < this.projectiles.size(); i++) {
/* 136 */
            ((Projectile) this.projectiles.get(i)).render(screen);
/*     */
        }
/* 138 */
        for (int i = 0; i < this.particles.size(); i++) {
/* 139 */
            ((Particle) this.particles.get(i)).render(screen);
/*     */
        }
/* 141 */
        for (int i = 0; i < this.players.size(); i++)
/* 142 */
            ((Player) this.players.get(i)).render(screen);
/*     */
    }

    /*     */
/*     */
    public void add(Entity e)
/*     */ {
/* 147 */
        e.init(this);
/* 148 */
        if ((e instanceof Particle))
/* 149 */ this.particles.add((Particle) e);
/* 150 */
        else if ((e instanceof Projectile))
/* 151 */ this.projectiles.add((Projectile) e);
/* 152 */
        else if ((e instanceof Player))
/* 153 */ this.players.add((Player) e);
/*     */
        else
/* 155 */       this.entities.add(e);
/*     */
    }

    /*     */
/*     */
    public List<Player> getPlayers()
/*     */ {
/* 161 */
        return this.players;
/*     */
    }

    /*     */
/*     */
    public Player getPlayerAt(int index) {
/* 165 */
        return (Player) this.players.get(index);
/*     */
    }

    /*     */
/*     */
    public Player getClientPlayer() {
/* 169 */
        return (Player) this.players.get(0);
/*     */
    }

    /*     */
/*     */
    public List<Node> findPath(Vector2i start, Vector2i goal) {
/* 173 */
        List openList = new ArrayList();
/* 174 */
        List closedList = new ArrayList();
/* 175 */
        Node current = new Node(start, null, 0.0D, getDistance(start, goal));
/* 176 */
        openList.add(current);
/* 177 */
        while (openList.size() > 0)
/*     */ {
/* 178 */
            Collections.sort(openList, this.nodeSorter);
/* 179 */
            current = (Node) openList.get(0);
/* 180 */
            if (current.tile.equals(goal)) {
/* 181 */
                List path = new ArrayList();
/* 182 */
                while (current.parent != null) {
/* 183 */
                    path.add(current);
/* 184 */
                    current = current.parent;
/*     */
                }
/* 186 */
                openList.clear();
/* 187 */
                closedList.clear();
/* 188 */
                return path;
/*     */
            }
/* 190 */
            openList.remove(current);
/* 191 */
            closedList.add(current);
        }
/* 192 */
        for (int i = 0; i < 9; i++) {
/* 193 */
            if (i != 4) continue;
/* 194 */
            int x = current.tile.getX();
/* 195 */
            int y = current.tile.getY();
/* 196 */
            int xi = i % 3 - 1;
/* 197 */
            int yi = i / 3 - 1;
/* 198 */
            Tile at = getTile(x + xi, y + yi);
/* 199 */
            if ((at != null) &&
/* 200 */           (!at.solid())) {
/* 201 */
                Vector2i a = new Vector2i(x + xi, y + yi);
/* 202 */
                double gCost = current.gCost + (getDistance(current.tile, a) == 1.0D ? 1.0D : 0.91D);
/* 203 */
                double hCost = getDistance(a, goal);
/* 204 */
                Node node = new Node(a, current, gCost, hCost);
/* 205 */
                if (((!vecInList(closedList, a)) || (gCost < node.gCost)) && (
/* 206 */             (!vecInList(openList, a)) || (gCost < node.gCost))) openList.add(node);
/*     */
            }
/*     */
        }
/*     */
/*     */ 
/* 209 */
        closedList.clear();
/* 210 */
        return null;
/*     */
    }

    /*     */
/*     */
    private boolean vecInList(List<Node> list, Vector2i vector) {
/* 214 */
        for (Node n : list) {
/* 215 */
            if (n.tile.equals(vector)) return true;
/*     */
        }
/* 217 */
        return false;
/*     */
    }

    /*     */
/*     */
    private double getDistance(Vector2i tile, Vector2i goal) {
/* 221 */
        double dx = tile.getX() - goal.getX();
/* 222 */
        double dy = tile.getY() - goal.getY();
/* 223 */
        return Math.sqrt(dx * dx + dy * dy);
/*     */
    }

    /*     */
/*     */
    public List<Entity> getEntities(Entity e, int radius) {
/* 227 */
        List result = new ArrayList();
/* 228 */
        int ex = e.getX();
/* 229 */
        int ey = e.getY();
/* 230 */
        for (int i = 0; i < this.entities.size(); i++) {
/* 231 */
            Entity entity = (Entity) this.entities.get(i);
/* 232 */
            if (!entity.equals(e)) {
/* 233 */
                int x = entity.getX();
/* 234 */
                int y = entity.getY();
/* 235 */
                int dx = x - ex;
/* 236 */
                int dy = y - ey;
/* 237 */
                double distance = Math.sqrt(dx * dx + dy * dy);
/* 238 */
                if (distance <= radius) result.add(entity);
/*     */
            }
/*     */
        }
/* 240 */
        return result;
/*     */
    }

    /*     */
/*     */
    public List<Player> getPlayers(Entity e, int radius) {
/* 244 */
        List result = new ArrayList();
/* 245 */
        int ex = e.getX();
/* 246 */
        int ey = e.getY();
/* 247 */
        for (int i = 0; i < this.players.size(); i++) {
/* 248 */
            Player player = (Player) this.players.get(i);
/* 249 */
            int x = player.getX();
/* 250 */
            int y = player.getY();
/* 251 */
            int dx = x - ex;
/* 252 */
            int dy = y - ey;
/* 253 */
            double distance = Math.sqrt(dx * dx + dy * dy);
/* 254 */
            if (distance <= radius) result.add(player);
/*     */
        }
/* 256 */
        return result;
/*     */
    }

    /*     */
/*     */
    public Tile getTile(int x, int y) {
/* 260 */
        if ((x < 0) || (y < 0) || (x >= this.width) || (y >= this.height)) return Tile.voidTile;
/* 261 */
        if (this.tiles[(x + y * this.width)] == -14903040) return Tile.grass;
/* 262 */
        if (this.tiles[(x + y * this.width)] == -65536) return Tile.grass_flower;
/* 263 */
        if (this.tiles[(x + y * this.width)] == -16767233) return Tile.grass_rock;
/* 264 */
        if (this.tiles[(x + y * this.width)] == -5888) return Tile.stone_wall;
/* 265 */
        if (this.tiles[(x + y * this.width)] == -65316) return Tile.stone_floor;
/* 266 */
        if (this.tiles[(x + y * this.width)] == -8192020) return Tile.bed;
/* 267 */
        if (this.tiles[(x + y * this.width)] == -6587547) return Tile.path;
/* 268 */
        if (this.tiles[(x + y * this.width)] == -41357) return Tile.ironDoor_closed;
/* 269 */
        if (this.tiles[(x + y * this.width)] == -7581697) return Tile.bookshelf;
/* 270 */
        if (this.tiles[(x + y * this.width)] == -16777216) return Tile.dark;
/*     */ 
/* 272 */
        return Tile.voidTile;
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.level.Level
 * JD-Core Version:    0.6.2
 */