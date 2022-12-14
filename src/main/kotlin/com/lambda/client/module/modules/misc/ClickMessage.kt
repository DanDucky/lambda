package com.lambda.client.module.modules.misc

import com.lambda.client.module.Category
import com.lambda.client.module.Module
import com.lambda.client.util.TickTimer
import net.minecraft.client.entity.EntityOtherPlayerMP
import net.minecraft.client.gui.GuiChat
import net.minecraft.util.math.RayTraceResult

object ClickMessage : Module(
    name = "ClickMsg",
    description = "Opens the message prompt for the player you click on",
    category = Category.MISC,
    showOnArray = false
) {
    private val timer = TickTimer()

    init {
        onEnable {
            mc.objectMouseOver?.let {
                if (it.typeOfHit != RayTraceResult.Type.ENTITY) disable()
                val player = it.entityHit as? EntityOtherPlayerMP ?
                if (player == null) {
                    disable()
                    return@onEnable
                }
                if (timer.tick(5000L) && timer.tick(500L)) {
                    if (mc.currentScreen !is GuiChat) {
                        mc.displayGuiScreen(GuiChat("/w ${player.displayName} "))
                    } else {
                        disable()
                    }
                }
            }

        }
    }
}
