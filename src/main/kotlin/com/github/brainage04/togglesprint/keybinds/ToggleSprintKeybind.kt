package com.github.brainage04.togglesprint.keybinds

import com.github.brainage04.togglesprint.ToggleSprintMain
import com.github.brainage04.togglesprint.ToggleSprintMain.Companion.toggleSprintKeybind
import com.github.brainage04.togglesprint.config.categories.ToggleMovementKeys
import com.github.brainage04.togglesprint.utils.ChatUtils
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.input.Keyboard

class ToggleSprintKeybind : KeyBinding("Toggle Sprint", Keyboard.KEY_RCONTROL, ToggleSprintMain.MOD_NAME) {
    private var toggleSprint = true

    companion object {
        val sprint: ToggleMovementKeys.ToggleSprintCategory get() = ToggleSprintMain.config.toggleMovementKeys.toggleSprintCategory
    }

    @SubscribeEvent
    fun tick(event: TickEvent.ClientTickEvent) {
        if (event.phase == TickEvent.Phase.START) {
            if (Minecraft.getMinecraft().thePlayer == null) return

            if (!sprint.toggleSprint) return

            if (toggleSprintKeybind.isPressed) {
                toggleSprint = !toggleSprint

                if (!toggleSprint) setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.keyCode, false)
            }

            if (toggleSprint) setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.keyCode, true)
        }
    }
}