package com.extra.power.client.renderer.blockentity.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

import java.util.List;

public class FrostControllerRenderState extends BlockEntityRenderState {
    private BlockModelRenderState cube;
    private float elevation;
    private List<Double> actionState;
    public BlockModelRenderState getCube() {
        return this.cube;
    }

    public float getElevation() {
        return this.elevation;
    }

    public List<Double> getActionState() {
        return actionState;
    }

    public void setActionState(List<Double> actionState) {
        this.actionState = actionState;
    }

    public void setCube(final BlockModelRenderState cube) {
        this.cube = cube;
    }

    public void setElevation(final float elevation) {
        this.elevation = elevation;
    }

}
