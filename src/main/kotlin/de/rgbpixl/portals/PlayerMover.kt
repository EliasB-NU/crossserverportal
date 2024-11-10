package de.rgbpixl.portals

import net.minecraft.network.PacketByteBuf
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

object PlayerMover {
    init {

    }

    class ChangeServerPayload(private val bytes: ByteArray) : CustomPayload {
        companion object {
            fun read(buf: PacketByteBuf): ChangeServerPayload {
                return ChangeServerPayload(getWrittenBytes(buf))
            }
            private fun getWrittenBytes(buf: PacketByteBuf): ByteArray {
                val bs = ByteArray(buf.readableBytes())
                buf.readBytes(bs)
                return bs
            }

            val CHANNEL_ID: CustomPayload.Id<ChangeServerPayload> = CustomPayload.Id(
                Identifier.of("bungeecord", "main")
            )

            val CODEC: PacketCodec<RegistryByteBuf, ChangeServerPayload> =
                PacketCodec.of(
                    { value: ChangeServerPayload, buf: RegistryByteBuf ->
                        writeBytes(
                            buf,
                            value.bytes
                        )
                    },
                    ChangeServerPayload::read
                )

            private fun writeBytes(buf: PacketByteBuf, v: ByteArray) {
                buf.writeBytes(v)
            }


        }

        override fun getId(): CustomPayload.Id<out CustomPayload?>? {
            return CHANNEL_ID
        }
    }
}
