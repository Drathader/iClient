package me.ihaq.iClient.utils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class RenderUtils {
	
	
	public static void entityESPBox(Entity entity, int mode)
	{
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		if(mode == 0)// Enemy
			GL11.glColor4d(
				1 - Minecraft.getMinecraft().thePlayer
					.getDistanceToEntity(entity) / 40,
				Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40,
				0, 0.5F);
		else if(mode == 1)// Friend
			GL11.glColor4d(0, 0, 1, 0.5F);
		else if(mode == 2)// Other
			GL11.glColor4d(1, 1, 0, 0.5F);
		else if(mode == 3)// Target
			GL11.glColor4d(1, 0, 0, 0.5F);
		else if(mode == 4)// Team
			GL11.glColor4d(0, 1, 0, 0.5F);
		Minecraft.getMinecraft().getRenderManager();
		RenderGlobal.func_181561_a(
			new AxisAlignedBB(
				entity.boundingBox.minX
					- 0.05
					- entity.posX
					+ (entity.posX - Minecraft.getMinecraft()
						.getRenderManager().renderPosX),
				entity.boundingBox.minY
					- entity.posY
					+ (entity.posY - Minecraft.getMinecraft()
						.getRenderManager().renderPosY),
				entity.boundingBox.minZ
					- 0.05
					- entity.posZ
					+ (entity.posZ - Minecraft.getMinecraft()
						.getRenderManager().renderPosZ),
				entity.boundingBox.maxX
					+ 0.05
					- entity.posX
					+ (entity.posX - Minecraft.getMinecraft()
						.getRenderManager().renderPosX),
				entity.boundingBox.maxY
					+ 0.1
					- entity.posY
					+ (entity.posY - Minecraft.getMinecraft()
						.getRenderManager().renderPosY),
				entity.boundingBox.maxZ
					+ 0.05
					- entity.posZ
					+ (entity.posZ - Minecraft.getMinecraft()
						.getRenderManager().renderPosZ)));
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public static void blockESPBox(BlockPos blockPos)
	{
		double x =
			blockPos.getX()
				- Minecraft.getMinecraft().getRenderManager().renderPosX;
		double y =
			blockPos.getY()
				- Minecraft.getMinecraft().getRenderManager().renderPosY;
		double z =
			blockPos.getZ()
				- Minecraft.getMinecraft().getRenderManager().renderPosZ;
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glLineWidth(2.0F);
		GL11.glColor4d(0, 1, 0, 0.15F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		//drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
		GL11.glColor4d(0, 0, 1, 0.5F);
		RenderGlobal.func_181561_a(new AxisAlignedBB(x, y, z,
			x + 1.0, y + 1.0, z + 1.0));
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public static void drawAltFace(String name, int x, int y, int w, int h, boolean selected) {
        try {
            AbstractClientPlayer.getDownloadImageSkin(AbstractClientPlayer.getLocationSkin(name), name).loadTexture(Minecraft.getMinecraft().getResourceManager());
            Minecraft.getMinecraft().getTextureManager().bindTexture(AbstractClientPlayer.getLocationSkin(name));
            Tessellator var3 = Tessellator.getInstance();
            WorldRenderer var4 = var3.getWorldRenderer();
            GL11.glEnable((int)3042);
            if (selected) {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            } else {
                GL11.glColor4f((float)0.9f, (float)0.9f, (float)0.9f, (float)1.0f);
            }
            double fw = 32.0;
            double fh = 32.0;
            double u = 32.0;
            double v = 32.0;
            var4.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            var4.pos((double)x + 0.0, (double)y + (double)h, 0.0);
            var4.pos((double)x + (double)w, (double)y + (double)h, 0.0);
            var4.pos((double)x + (double)w, (double)y + 0.0, 0.0);
            var4.pos((double)x + 0.0, (double)y + 0.0, 0.0);
            var3.draw();
            fw = 32.0;
            fh = 32.0;
            u = 160.0;
            v = 32.0;
            var4.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            var4.pos((double)x + 0.0, (double)y + (double)h, 0.0);
            var4.pos((double)x + (double)w, (double)y + (double)h, 0.0);
            var4.pos((double)x + (double)w, (double)y + 0.0, 0.0);
            var4.pos((double)x + 0.0, (double)y + 0.0, 0.0);
            var3.draw();
            GL11.glDisable((int)3042);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}