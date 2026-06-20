package com.extra.power.client.renderer.blockentity;


import com.extra.power.block.blockentity.MushroomCloudBlockEntity;
import com.extra.power.client.renderer.blockentity.state.MushroomCloudRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.dubhe.anvilcraft.client.support.FeatureRendererSupport;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ItemClusterRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;
import org.jspecify.annotations.Nullable;

public class MushroomCloudRenderer implements BlockEntityRenderer<MushroomCloudBlockEntity, MushroomCloudRenderState> {

    public static final StandaloneModelKey<BlockStateModel> HEAD_MODEL = new StandaloneModelKey<>(
            () -> "AnvilCraftExtrapower: Mushroom Cloud Head"
    );
    public static final StandaloneModelKey<BlockStateModel> BOTTOM_MODEL = new StandaloneModelKey<>(
            () -> "AnvilCraftExtrapower: Mushroom Cloud Bottom"
    );
    public static final StandaloneModelKey<BlockStateModel> TOP_SIDE_MODEL = new StandaloneModelKey<>(
            () -> "AnvilCraftExtrapower: Mushroom Cloud Top Side"
    );
    public static final StandaloneModelKey<BlockStateModel> EPICENTER_MODEL = new StandaloneModelKey<>(
            () -> "AnvilCraftExtrapower: Mushroom Cloud Epicenter"
    );
    public static final StandaloneModelKey<BlockStateModel> CIRCLE_MODEL = new StandaloneModelKey<>(
            () -> "AnvilCraftExtrapower: Nuclear Bomb Circle"
    );

    public MushroomCloudRenderer(BlockEntityRendererProvider.Context ignored) {}

    @Override
    public MushroomCloudRenderState createRenderState() {
        return new MushroomCloudRenderState();
    }

    @Override
    public void extractRenderState(
            MushroomCloudBlockEntity be,
            MushroomCloudRenderState state,
            float partialTicks,
            Vec3 cameraPosition,
            ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
    ) {
        BlockEntityRenderer.super.extractRenderState(be, state, partialTicks, cameraPosition, breakProgress);
        state.setEpicenterScale(be.getEpicenterScale());
        state.setCloudSize(be.getC_size());
        state.setRotation(be.getRotation());

        state.setHeadModel(FeatureRendererSupport.initialize(HEAD_MODEL, be));
        state.setBottomModel(FeatureRendererSupport.initialize(BOTTOM_MODEL, be));
        state.setTopSideModel(FeatureRendererSupport.initialize(TOP_SIDE_MODEL, be));
        state.setCube(FeatureRendererSupport.initialize(EPICENTER_MODEL, be));
        state.setCircleModel(FeatureRendererSupport.initialize(CIRCLE_MODEL, be));
    }

    @Override
    public void submit(
            MushroomCloudRenderState state,
            PoseStack pose,
            SubmitNodeCollector collector,
            CameraRenderState camera
    ) {
        float scale = state.getCloudSize();
        float partialTick = state.getPartialTicks();
        int light = state.lightCoords;  // 使用正确的光照坐标

        // 1. 渲染光球（爆心）
        pose.pushPose();
        pose.translate(0.5f, 0.2f, 0.5f);
        pose.scale(state.getEpicenterScale(), state.getEpicenterScale(), state.getEpicenterScale());
        state.getCube().submit(pose, collector, light, OverlayTexture.NO_OVERLAY, 0);
        pose.popPose();

        renderCloudPart(pose, collector, state.getHeadModel(), 1.0f, scale, light);
        renderCloudPart(pose, collector, state.getBottomModel(), 1.0f, scale, light);
        renderCloudPart(pose, collector, state.getTopSideModel(), 1.2f, scale, light);
        renderCirclePart(pose, collector, state.getCircleModel(), 0, scale, state.getRotation(), partialTick, light);
        renderCirclePart(pose, collector, state.getCircleModel(), 0.5f, scale, -state.getRotation(), partialTick, light);
    }

    private void renderCloudPart(
            PoseStack pose,
            SubmitNodeCollector collector,
            BlockModelRenderState model,
            float plus,
            float scale,
            int light
    ) {
        if (model == null) return;
        pose.pushPose();
        pose.translate(-0.5 * scale, scale - (plus - 1) * scale, -0.5 * scale);
        pose.scale(scale * plus, scale * plus, scale * plus);
        model.submit(pose, collector, light, OverlayTexture.NO_OVERLAY, 0);
        pose.popPose();
    }

    private void renderCirclePart(
            PoseStack pose,
            SubmitNodeCollector collector,
            BlockModelRenderState model,
            float a,                // 0 ~ 1，控制垂直偏移和大小系数
            float scale,
            float rotation,
            float partialTick,
            int light
    ) {
        if (model == null) return;
        pose.pushPose();
        float yOffset = -a * 5 + a * scale * 2; // 自定义参数，可调整
        pose.translate(0.25, yOffset, 0.25);
        float sizeFactor = scale * (1 + a * 0.3f);
        pose.scale(sizeFactor, sizeFactor, sizeFactor);
        pose.mulPose(Axis.YP.rotationDegrees(rotation + partialTick * 2));
        model.submit(pose, collector, light, OverlayTexture.NO_OVERLAY, 0);
        pose.popPose();
    }

    @Override
    public int getViewDistance() {
        return 128;
    }
}