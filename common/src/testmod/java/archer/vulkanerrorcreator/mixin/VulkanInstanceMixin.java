package archer.vulkanerrorcreator.mixin;

import com.mojang.renderpearl.backend.vulkan.VulkanInstance;
import org.lwjgl.vulkan.VkInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = VulkanInstance.class, remap = false)
public class VulkanInstanceMixin {
    @Final
    @Shadow
    private VkInstance vkInstance;

    @Inject(method = "<init>", at = @At("RETURN"), require = 1)
    private void onInit(CallbackInfo info) {
        System.getProperties().put("vulkanerrorcreator_vkInstance", this.vkInstance);
    }
}