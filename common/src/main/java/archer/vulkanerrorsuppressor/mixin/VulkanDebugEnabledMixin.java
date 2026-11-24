package archer.vulkanerrorsuppressor.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.renderpearl.backend.vulkan.VulkanDebug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(value = VulkanDebug.Enabled.class, remap = false)
public class VulkanDebugEnabledMixin {
    @Unique
    private static final Set<String> vulkanerrorsuppressor$LOGGED_VK_ERRORS = ConcurrentHashMap.newKeySet();

    @Inject(method = "onDebugMessage(IIJJ)I",
            at = {
                    @At(value = "INVOKE", target = "Lorg/slf4j/Logger;*(Ljava/lang/String;Ljava/lang/Object;)V"),
                    @At(value = "INVOKE", target = "Lorg/slf4j/Logger;*(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V")
            },
            cancellable = true,
            require = 5)
    private void onDebugMessage(int messageSeverity, int messageTypes, long pCallbackData, long pUserData, CallbackInfoReturnable<Integer> info, @Local(ordinal = 0) String message) {
        message = (message != null) ? message : "";
        if (vulkanerrorsuppressor$LOGGED_VK_ERRORS.contains(message) || !vulkanerrorsuppressor$LOGGED_VK_ERRORS.add(message)) {
            info.setReturnValue((messageSeverity & 4096) == 0 ? 0 : 1);
        }
    }
}