package de.rgbpixl

import de.rgbpixl.portals.PlayerMover
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import org.slf4j.LoggerFactory

object Crossserverportal : DedicatedServerModInitializer, ServerPlayNetworking.PlayPayloadHandler<PlayerMover.ChangeServerPayload> {
    private val logger = LoggerFactory.getLogger("crossserverportal")

	override fun onInitializeServer() {
		ServerLifecycleEvents.SERVER_STARTED.register { server ->
			onEnable()
		}

		ServerLifecycleEvents.SERVER_STOPPING.register { server ->
			onDisable()
		}
	}

	private fun onEnable() {
		logger.info("Crossserverportal enabled")
	}

	private fun onDisable() {
		logger.info("Crossserverportal disabled")
	}

	override fun receive(payload: PlayerMover.ChangeServerPayload?, context: ServerPlayNetworking.Context?) {
	}
}