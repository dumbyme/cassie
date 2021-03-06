package com.codahale.cassie

import org.apache.cassandra.thrift

/**
 * The level of consistency required for a write operation.
 *
 * @author coda
 */
sealed case class WriteConsistency(level: thrift.ConsistencyLevel) {
    override def toString = "WriteConsistency." +
          level.toString.toLowerCase.capitalize
}

object WriteConsistency {
  /**
   * Ensure nothing. A write happens asynchronously in background. (Cassandra as
   * of 0.6 does not have back pressure for asynchronous writes. If these are
   * not throttled, clients will exhaust the servers' memory.
   */
  val Zero = WriteConsistency(thrift.ConsistencyLevel.ZERO)

  /**
   * Ensure that the write has been written to at least 1 node, including hinted
   * recipients.
   */
  val Any = WriteConsistency(thrift.ConsistencyLevel.ANY)

  /**
   * Ensure that the write has been written to at least 1 node's commit log and
   * memory table before responding to the client.
   */
  val One = WriteConsistency(thrift.ConsistencyLevel.ONE)

  /**
   * Ensure that the write has been written to ReplicationFactor / 2 + 1 nodes
   * before responding to the client.
   */
  val Quorum = WriteConsistency(thrift.ConsistencyLevel.QUORUM)

  /**
   * Ensure that the write is written to all ReplicationFactor nodes before
   * responding to the client. Any unresponsive nodes will fail the operation.
   */
  val All = WriteConsistency(thrift.ConsistencyLevel.ALL)
}
