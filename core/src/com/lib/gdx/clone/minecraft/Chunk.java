package com.lib.gdx.clone.minecraft;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lib.gdx.clone.minecraft.block.Block;
import com.lib.gdx.clone.minecraft.block.BlockGrass;
import com.lib.gdx.clone.minecraft.block.BlockNull;
import com.lib.gdx.clone.minecraft.block.BlockStone;

public class Chunk {

    private Model model;

//    private BoundingBox chunkBounds;
    private Vector2 chunkPosition;
    //how many blocks in the chunk on the X Z plane
    private final int CHUNK_SIZE = 16;

    //the max height you can have the blocks in a chunk
    private final int HEIGHT_LIMIT = 100;

    private com.lib.gdx.clone.minecraft.block.Block[][][] blocks = new com.lib.gdx.clone.minecraft.block.Block[CHUNK_SIZE][HEIGHT_LIMIT][CHUNK_SIZE];

    public Chunk(int gridX, int gridY, Model model){
        chunkPosition = new Vector2(gridX * CHUNK_SIZE, gridY * CHUNK_SIZE);
        this.model = model;

        //set all the blocks in the block array to a value
        for(int x = blocks.length - 1; x >= 0; x--) {
            for (int y = blocks[x].length - 1; y >= 0; y--) {
                for (int z = blocks[x][y].length - 1; z >= 0; z--) {
                    if(y < 45){
                        double r = Math.random();
                        //TODO generation of chunk
                        if(r > 0.8){
                            blocks[x][y][z] = new BlockStone(model, new Vector3(x, y, z));
                        }else if(r > 0.5){
                            blocks[x][y][z] = new BlockGrass(model, new Vector3(x, y, z));
                        }else{
                            blocks[x][y][z] = new BlockNull(model, new Vector3(x, y, z));
                        }
                    }else {
                        blocks[x][y][z] = new BlockNull(model, new Vector3(x, y, z));
                    }
                }
            }
        }
    }

    public boolean inView(Camera camera){
        float dist = chunkDistance(camera);
        if(dist < camera.far + CHUNK_SIZE){
            return true;
        }
        return false;
    }

    public void update(Camera camera, ModelBatch batch, Environment environment){
        for(int x = blocks.length - 1; x >= 0; x--){
            for(int y = blocks[x].length - 1; y >= 0; y--){
                for(int z = blocks[x][y].length - 1; z >= 0; z--){
                    Block block = blocks[x][y][z];

                    //if the block is Air then nothing needs to be rendered
                    if(block.isAir()){
                        continue;
                    }

                    block.update();

                    if(camera.frustum.boundsInFrustum(block.getBoundingBox())) {
                        block.render(batch, environment);
                    }
                }
            }
        }
    }

    private float chunkDistance(Camera camera){
        float dx = camera.position.x - chunkPosition.x;
        float dz = camera.position.z - chunkPosition.x;

        return (float)Math.sqrt(dx * dx + dz * dz);
    }

    public Vector2 getChunkPosition() {
        return chunkPosition;
    }
}
