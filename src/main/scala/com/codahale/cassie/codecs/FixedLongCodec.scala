package com.codahale.cassie.codecs

import com.codahale.cassie.types.FixedLong
import java.nio.ByteBuffer

/**
 * Encodes and decodes 64-bit integers as 8-byte, big-endian byte arrays.
 *
 * @author coda
 */
object FixedLongCodec extends Codec[FixedLong] {
  private val length = 8

  def encode(v: FixedLong) = {
    val b = ByteBuffer.allocate(length)
    b.putLong(v.value)
    b.array
  }

  def decode(buf: Array[Byte]) = {
    require(buf.length == length)
    FixedLong(ByteBuffer.wrap(buf).getLong)
  }
}
