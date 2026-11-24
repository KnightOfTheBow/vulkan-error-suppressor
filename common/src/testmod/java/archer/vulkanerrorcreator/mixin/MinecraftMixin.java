package archer.vulkanerrorcreator.mixin;

import net.minecraft.client.Minecraft;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.EXTDebugUtils;
import org.lwjgl.vulkan.VkDebugUtilsMessengerCallbackDataEXT;
import org.lwjgl.vulkan.VkInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class, remap = false)
public class MinecraftMixin {
    @Inject(method = "runTick", at = @At("TAIL"), require = 1)
    private void runTick(boolean advanceGameTime, CallbackInfo info) {
        try (MemoryStack vulkanerrorcreator$stack = MemoryStack.stackPush()) {
            VkInstance vulkanerrorcreator$vkInstance = (VkInstance) System.getProperties().get("vulkanerrorcreator_vkInstance");

            EXTDebugUtils.vkSubmitDebugUtilsMessageEXT(
                    vulkanerrorcreator$vkInstance,
                    EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT,
                    EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT,
                    VkDebugUtilsMessengerCallbackDataEXT.calloc(vulkanerrorcreator$stack)
                            .sType(EXTDebugUtils.VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CALLBACK_DATA_EXT)
                            .pMessageIdName(vulkanerrorcreator$stack.UTF8("VUID-Test-vulkanerrorcreator"))
                            .pMessage(vulkanerrorcreator$stack.UTF8("Error triggered for testing.")));
            EXTDebugUtils.vkSubmitDebugUtilsMessageEXT(
                    vulkanerrorcreator$vkInstance,
                    EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT,
                    EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT,
                    VkDebugUtilsMessengerCallbackDataEXT.calloc(vulkanerrorcreator$stack)
                            .sType(EXTDebugUtils.VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CALLBACK_DATA_EXT)
                            .pMessageIdName(vulkanerrorcreator$stack.UTF8("VUID-Test-NullMessage-A"))
                            .messageIdNumber(1001));
            EXTDebugUtils.vkSubmitDebugUtilsMessageEXT(
                    vulkanerrorcreator$vkInstance,
                    EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT,
                    EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT,
                    VkDebugUtilsMessengerCallbackDataEXT.calloc(vulkanerrorcreator$stack)
                            .sType(EXTDebugUtils.VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CALLBACK_DATA_EXT)
                            .pMessageIdName(vulkanerrorcreator$stack.UTF8("VUID-Test-NullMessage-B"))
                            .messageIdNumber(1002));
            // An intentional repeat
            EXTDebugUtils.vkSubmitDebugUtilsMessageEXT(
                    vulkanerrorcreator$vkInstance,
                    EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT,
                    EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT,
                    VkDebugUtilsMessengerCallbackDataEXT.calloc(vulkanerrorcreator$stack)
                            .sType(EXTDebugUtils.VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CALLBACK_DATA_EXT)
                            .pMessageIdName(vulkanerrorcreator$stack.UTF8("VUID-Test-NullMessage-A"))
                            .messageIdNumber(1001));
        }
    }
}