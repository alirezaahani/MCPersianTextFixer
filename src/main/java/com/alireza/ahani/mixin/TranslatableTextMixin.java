package com.alireza.ahani.mixin;

import com.alireza.ahani.PersianTextFixer;
import com.ibm.icu.text.ArabicShapingException;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TranslatableText.class)
public abstract class TranslatableTextMixin {

    @Inject(at = @At("RETURN"), method = "Lnet/minecraft/text/TranslatableText;getArg(I)Lnet/minecraft/text/StringVisitable;",  cancellable = true)
    private void getArg(int index, CallbackInfoReturnable<StringVisitable> cir) throws ArabicShapingException {
        PersianTextFixer fixer = new PersianTextFixer();
        String converted = fixer.convert(cir.getReturnValue().getString());

        cir.setReturnValue(StringVisitable.plain(converted));
    }

}
