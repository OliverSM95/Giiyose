package events;

import events.entity.Entity;

public class CollisionChecker {

    GatherGamePanel gp;

    public CollisionChecker(GatherGamePanel gp) {

        this.gp = gp;


    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.hitBox.x;
        int entityRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int entityTopWorldY = entity.worldY + entity.hitBox.y;
        int entityBottomWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;


        int tileNum1,tileNum2;

        switch (entity.direction){
            case "up":
            entityTopRow = (entityTopWorldY - entity.speed) /gp.tileSize;
            tileNum1 = gp.tileM.mapTileNumber[gp.currentMap][entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNumber[gp.currentMap][entityRightCol][entityTopRow];


            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) /gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNumber[gp.currentMap][entityRightCol][entityBottomRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) /gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[gp.currentMap][entityLeftCol][entityBottomRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX - entity.speed) /gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[gp.currentMap][entityRightCol][entityBottomRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }



    }
    public int checkObject(Entity entity,boolean player){
        int index = 999;

        for( int i=0; i<gp.object[1].length;i++){
            if(gp.object[gp.currentMap][i] != null){

                // get entity's hit box position
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;

                //get the objects hit box position
                gp.object[gp.currentMap][i].hitBox.x =  gp.object[gp.currentMap][i].worldX + gp.object[gp.currentMap][i].hitBox.x;
                gp.object[gp.currentMap][i].hitBox.y =  gp.object[gp.currentMap][i].worldY + gp.object[gp.currentMap][i].hitBox.y;

                switch(entity.direction){
                    case "up":
                        entity.hitBox.y -= entity.speed;
                        if(entity.hitBox.intersects(gp.object[gp.currentMap][i].hitBox)){// checks if object hotbox rectangles are colliding
                            if(gp.object[gp.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index =i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitBox.y += entity.speed;
                        if(entity.hitBox.intersects(gp.object[gp.currentMap][i].hitBox)){// checks if object hotbox rectangles are colliding
                            if(gp.object[gp.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index =i;
                            }
                        }
                        break;
                    case "left":
                        entity.hitBox.x -= entity.speed;
                        if(entity.hitBox.intersects(gp.object[gp.currentMap][i].hitBox)){// checks if object hotbox rectangles are colliding
                            if(gp.object[gp.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index =i;
                            }
                        }
                        break;
                    case "right":
                        entity.hitBox.x += entity.speed;
                        if(entity.hitBox.intersects(gp.object[gp.currentMap][i].hitBox)){// checks if object hotbox rectangles are colliding
                            if(gp.object[gp.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index =i;
                            }
                        }
                        break;
                }

                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                gp.object[gp.currentMap][i].hitBox.x =  gp.object[gp.currentMap][i].hitBoxDefaultX;
                gp.object[gp.currentMap][i].hitBox.y =  gp.object[gp.currentMap][i].hitBoxDefaultY;
            }
        }

        return index;
    }
}
