package com.extra.power.client.renderer.blockentity.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class MushroomCloudRenderState extends BlockEntityRenderState {
    private float epicenterScale;
    private float cloudSize;
    private float rotation;
    private float partialTicks;

    private BlockModelRenderState headModel;
    private BlockModelRenderState bottomModel;
    private BlockModelRenderState topSideModel;
    private BlockModelRenderState epicenterModel;
    private BlockModelRenderState circleModel;

    public float getEpicenterScale() { return epicenterScale; }
    public void setEpicenterScale(float scale) { this.epicenterScale = scale; }
    public float getCloudSize() { return cloudSize; }
    public void setCloudSize(float size) { this.cloudSize = size; }
    public float getRotation() { return rotation; }
    public void setRotation(float rotation) { this.rotation = rotation; }
    public float getPartialTicks() { return partialTicks; }
    public void setPartialTicks(float partialTicks) { this.partialTicks = partialTicks; }

    public BlockModelRenderState getHeadModel() { return headModel; }
    public void setHeadModel(BlockModelRenderState headModel) { this.headModel = headModel; }
    public BlockModelRenderState getBottomModel() { return bottomModel; }
    public void setBottomModel(BlockModelRenderState bottomModel) { this.bottomModel = bottomModel; }
    public BlockModelRenderState getTopSideModel() { return topSideModel; }
    public void setTopSideModel(BlockModelRenderState topSideModel) { this.topSideModel = topSideModel; }
    public BlockModelRenderState getCube() { return epicenterModel; }
    public void setCube(BlockModelRenderState epicenterModel) { this.epicenterModel = epicenterModel; }
    public BlockModelRenderState getCircleModel() { return circleModel; }
    public void setCircleModel(BlockModelRenderState circleModel) { this.circleModel = circleModel; }
}