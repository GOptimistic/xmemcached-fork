package net.rubyeye.xmemcached;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import net.rubyeye.xmemcached.command.Command;
import net.rubyeye.xmemcached.command.CommandType;
import net.rubyeye.xmemcached.transcoders.Transcoder;

public interface CommandFactory {

	/**
	 * 创建delete命令
	 *
	 * @param key
	 * @param time
	 * @return
	 */
	public abstract Command createDeleteCommand(final String key,
			final byte[] keyBytes, final int time);

	/**
	 * 创建version command
	 *
	 * @return
	 */
	public abstract Command createVersionCommand();

	/**
	 * create flush_all command
	 *
	 * @return
	 */
	public abstract Command createFlushAllCommand(CountDownLatch latch);

	/**
	 * create flush_all command
	 *
	 * @return
	 */
	public abstract Command createStatsCommand(InetSocketAddress server,
			CountDownLatch latch);

	/**
	 *创建get,gets命令
	 *
	 * @param key
	 * @param keyBytes
	 * @param cmdBytes
	 *            命令的字节数组，如"get".getBytes()
	 * @param cmdType
	 *            命令类型
	 * @return
	 */
	public abstract Command createGetCommand(final String key,
			final byte[] keyBytes, final CommandType cmdType);

	/**
	 * 创建批量获取 command
	 *
	 * @param <T>
	 * @param keys
	 * @param latch
	 * @param result
	 * @param cmdBytes
	 * @param cmdType
	 * @param transcoder
	 * @return
	 */
	public abstract <T> Command createGetMultiCommand(Collection<String> keys,
			CountDownLatch latch, CommandType cmdType, Transcoder<T> transcoder);

	public abstract Command createIncrDecrCommand(final String key,
			final byte[] keyBytes, final int num, CommandType cmdType);

	@SuppressWarnings("unchecked")
	public Command createCASCommand(final String key, final byte[] keyBytes, final int exp,
			final Object value, long cas, Transcoder transcoder);

	@SuppressWarnings("unchecked")
	public Command createSetCommand(final String key, final byte[] keyBytes, final int exp,
			final Object value, Transcoder transcoder);

	@SuppressWarnings("unchecked")
	public Command createAddCommand(final String key, final byte[] keyBytes, final int exp,
			final Object value, Transcoder transcoder);

	@SuppressWarnings("unchecked")
	public Command createReplaceCommand(final String key, final byte[] keyBytes, final int exp,
			final Object value, Transcoder transcoder);

	@SuppressWarnings("unchecked")
	public Command createAppendCommand(final String key, final byte[] keyBytes, final Object value,
			Transcoder transcoder);

	@SuppressWarnings("unchecked")
	public Command createPrependCommand(final String key, final byte[] keyBytes, final Object value,
			Transcoder transcoder);

}