package enemy;
import java.awt.Rectangle;

import handlers.EnemyHandler;

import static extras.Constant.*;

public class Enemy{

    private double x;
    private double y;
    private int hp;
    private int maxHp;
    private int typeOfEnemy;
    private int enemyID;
    private int lastDirection;
    private Rectangle size;
    protected boolean alive = true;
    protected int slowTickLimit = 180;
    protected int slowTick = slowTickLimit;
    protected EnemyHandler enemyHandler;
    public Enemy(double x, double y, int typeOfEnemy, int enemyID, EnemyHandler enemyHandler){
        this.x = x;
        this.y = y;
        this.size = new Rectangle((int)x, (int)y, 32, 32);
        this.typeOfEnemy = typeOfEnemy;
        this.enemyID = enemyID;
        this.lastDirection = -1;
        this.enemyHandler = enemyHandler;
        setStartHp();
    }
    private void setStartHp(){
        hp = extras.Constant.EnemyConstants.getStartHp(typeOfEnemy);
        maxHp = hp;
    }
    public double getHpBar(){
        return hp/(double) maxHp;
    }
    public void initLoc(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void changeLoc(double changeSpeed, int direction){
        this.lastDirection = direction;

        if(slowTick < slowTickLimit){
            slowTick++;
            changeSpeed *= 0.5;
        }
        if (direction == DirectionOfEnemy.UP){
            this.y -= changeSpeed;
        }
        else if (direction == DirectionOfEnemy.DOWN){
            this.y += changeSpeed;
        }
        else if (direction == DirectionOfEnemy.RIGHT){
            this.x += changeSpeed;
        }
        else if (direction == DirectionOfEnemy.LEFT){
            this.x -= changeSpeed;
        }
        updateHitbox();
    }
    private void updateHitbox() {
        size.x = (int) x;
        size.y = (int) y;
    }
    public void kill(){
        // killing the enemies that reach end
        alive = false;
        hp = 0;
    }
    public void hurt(int damage){
        this.hp -= damage;
        if(hp <= 0){
            alive = false;
            enemyHandler.rewardPlayer(typeOfEnemy);
        }
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getLastDirection() {
        return lastDirection;
    }
    public int getEnemyID() {
        return enemyID;
    }
    public int getHp() {
        return hp;
    }
    public Rectangle getSize() {
        return size;
    }
    public int getTypeOfEnemy() {
        return typeOfEnemy;
    }
    public double getEnemyChangeSpeed(){
        if(this.typeOfEnemy == EnemyConstants.ENEMY_1){
            return 0.7;
        }
        else if(this.typeOfEnemy == EnemyConstants.ENEMY_2){
            return 0.6;
        }
        else{
            return 0.5;
        }
    }
    public boolean isAlive(){
        return alive;
    }
    public void slow() {
        slowTick = 0;
    }
    public boolean isSlowed(){
        return slowTick < slowTickLimit;
    }
}
