package com.alireza.ahani.mixin;

import com.alireza.ahani.PersianTextFixer;
import com.ibm.icu.text.ArabicShapingException;
import com.alireza.ahani.GiveMeTheString;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TextRenderer.class)
public abstract class TextRendererMixin {

	/**
	 * @author Alireza Ahani
	 */
	@Overwrite
	public String mirror(String text) throws ArabicShapingException {
		PersianTextFixer fixer = new PersianTextFixer();
		return fixer.convert(text);
	}

	/**
	 * @author Alireza Ahani
	 * I HATE MOJANG
	 */
	@Overwrite
	public String trimToWidth(String text, int maxWidth) throws ArabicShapingException {
		PersianTextFixer fixer = new PersianTextFixer();
		return fixer.convert(text);
	}

	/**
	 * @author Alireza Ahani
	 * I HATE NESTED CLASSES
	 */
	@Overwrite
	private float drawLayer(OrderedText text, float x, float y, int color, boolean shadow, Matrix4f matrix, VertexConsumerProvider vertexConsumerProvider, boolean seeThrough, int underlineColor, int light) throws ArabicShapingException {
		GiveMeTheString visitor = new GiveMeTheString();
		PersianTextFixer fixer = new PersianTextFixer();
		var textRenderer = (TextRenderer) (Object) this;

		text.accept(visitor);
		String normal_text = fixer.convert(visitor.toString());

		text = OrderedText.styledForwardsVisitedString(normal_text, Style.EMPTY);

		TextRenderer.Drawer drawer = textRenderer.new Drawer(vertexConsumerProvider, x, y, color, shadow, matrix, seeThrough, light);
		text.accept(drawer);
		return drawer.drawLayer(underlineColor, x);
	}
}
