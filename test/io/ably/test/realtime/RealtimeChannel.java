package io.ably.test.realtime;

import static org.junit.Assert.*;
import io.ably.realtime.AblyRealtime;
import io.ably.realtime.Channel;
import io.ably.realtime.Channel.MessageListener;
import io.ably.realtime.ChannelState;
import io.ably.realtime.ConnectionState;
import io.ably.test.realtime.Helpers.ChannelWaiter;
import io.ably.test.realtime.Helpers.ConnectionWaiter;
import io.ably.test.realtime.RealtimeSetup.TestVars;
import io.ably.types.AblyException;
import io.ably.types.ErrorInfo;
import io.ably.types.Message;
import io.ably.types.ClientOptions;

import org.junit.Test;

public class RealtimeChannel {

	/**
	 * Connect to the service using the default (binary) protocol
	 * and attach to a channel, confirming that the attached state is reached.
	 */
	@Test
	public void attach_binary() {
		AblyRealtime ably = null;
		try {
			TestVars testVars = RealtimeSetup.getTestVars();
			ClientOptions opts = testVars.createOptions(testVars.keys[0].keyStr);
			ably = new AblyRealtime(opts);

			/* wait until connected */
			(new ConnectionWaiter(ably.connection)).waitFor(ConnectionState.connected);
			assertEquals("Verify connected state reached", ably.connection.state, ConnectionState.connected);

			/* create a channel and attach */
			final Channel channel = ably.channels.get("attach_binary");
			channel.attach();
			(new ChannelWaiter(channel)).waitFor(ChannelState.attached);
			assertEquals("Verify attached state reached", channel.state, ChannelState.attached);

		} catch (AblyException e) {
			e.printStackTrace();
			fail("init0: Unexpected exception instantiating library");
		} finally {
			if(ably != null)
				ably.close();
		}
	}

	/**
	 * Connect to the service using the text protocol
	 * and attach to a channel, confirming that the attached state is reached.
	 */
	@Test
	public void attach_text() {
		AblyRealtime ably = null;
		try {
			TestVars testVars = RealtimeSetup.getTestVars();
			ClientOptions opts = testVars.createOptions(testVars.keys[0].keyStr);
			opts.useBinaryProtocol = false;
			ably = new AblyRealtime(opts);

			/* wait until connected */
			(new ConnectionWaiter(ably.connection)).waitFor(ConnectionState.connected);
			assertEquals("Verify connected state reached", ably.connection.state, ConnectionState.connected);

			/* create a channel and attach */
			final Channel channel = ably.channels.get("attach_text");
			channel.attach();
			(new ChannelWaiter(channel)).waitFor(ChannelState.attached);
			assertEquals("Verify attached state reached", channel.state, ChannelState.attached);

		} catch (AblyException e) {
			e.printStackTrace();
			fail("init0: Unexpected exception instantiating library");
		} finally {
			if(ably != null)
				ably.close();
		}
	}

	/**
	 * Connect to the service using the default (binary) protocol
	 * and attach before the connected state is reached.
	 */
	@Test
	public void attach_before_connect_binary() {
		AblyRealtime ably = null;
		try {
			TestVars testVars = RealtimeSetup.getTestVars();
			ClientOptions opts = testVars.createOptions(testVars.keys[0].keyStr);
			ably = new AblyRealtime(opts);

			/* create a channel and attach */
			final Channel channel = ably.channels.get("attach_before_connect_binary");
			channel.attach();
			(new ChannelWaiter(channel)).waitFor(ChannelState.attached);
			assertEquals("Verify attached state reached", channel.state, ChannelState.attached);

		} catch (AblyException e) {
			e.printStackTrace();
			fail("init0: Unexpected exception instantiating library");
		} finally {
			if(ably != null)
				ably.close();
		}
	}

	/**
	 * Connect to the service using the text protocol
	 * and attach before the connected state is reached.
	 */
	@Test
	public void attach_before_connect_text() {
		AblyRealtime ably = null;
		try {
			TestVars testVars = RealtimeSetup.getTestVars();
			ClientOptions opts = testVars.createOptions(testVars.keys[0].keyStr);
			opts.useBinaryProtocol = false;
			ably = new AblyRealtime(opts);

			/* create a channel and attach */
			final Channel channel = ably.channels.get("attach_before_connect_text");
			channel.attach();
			(new ChannelWaiter(channel)).waitFor(ChannelState.attached);
			assertEquals("Verify attached state reached", channel.state, ChannelState.attached);

		} catch (AblyException e) {
			e.printStackTrace();
			fail("init0: Unexpected exception instantiating library");
		} finally {
			if(ably != null)
				ably.close();
		}
	}

	/**
	 * Connect to the service using the default (binary) protocol
	 * and attach, then detach
	 */
	@Test
	public void attach_detach_binary() {
		AblyRealtime ably = null;
		try {
			TestVars testVars = RealtimeSetup.getTestVars();
			ClientOptions opts = testVars.createOptions(testVars.keys[0].keyStr);
			ably = new AblyRealtime(opts);

			/* create a channel and attach */
			final Channel channel = ably.channels.get("attach_detach_binary");
			channel.attach();
			(new ChannelWaiter(channel)).waitFor(ChannelState.attached);
			assertEquals("Verify attached state reached", channel.state, ChannelState.attached);

			/* detach */
			channel.detach();
			(new ChannelWaiter(channel)).waitFor(ChannelState.detached);
			assertEquals("Verify detached state reached", channel.state, ChannelState.detached);

		} catch (AblyException e) {
			e.printStackTrace();
			fail("init0: Unexpected exception instantiating library");
		} finally {
			if(ably != null)
				ably.close();
		}
	}

	/**
	 * Connect to the service using the text protocol
	 * and attach, then detach
	 */
	@Test
	public void attach_detach_text() {
		AblyRealtime ably = null;
		try {
			TestVars testVars = RealtimeSetup.getTestVars();
			ClientOptions opts = testVars.createOptions(testVars.keys[0].keyStr);
			opts.useBinaryProtocol = false;
			ably = new AblyRealtime(opts);

			/* create a channel and attach */
			final Channel channel = ably.channels.get("attach_detach_text");
			channel.attach();
			(new ChannelWaiter(channel)).waitFor(ChannelState.attached);
			assertEquals("Verify attached state reached", channel.state, ChannelState.attached);

			/* detach */
			channel.detach();
			(new ChannelWaiter(channel)).waitFor(ChannelState.detached);
			assertEquals("Verify detached state reached", channel.state, ChannelState.detached);

		} catch (AblyException e) {
			e.printStackTrace();
			fail("init0: Unexpected exception instantiating library");
		} finally {
			if(ably != null)
				ably.close();
		}
	}

	/**
	 * Connect to the service using the default (binary) protocol
	 * and attach, then subscribe and unsubscribe
	 */
	@Test
	public void subscribe_unsubscribe() {
		AblyRealtime ably = null;
		try {
			TestVars testVars = RealtimeSetup.getTestVars();
			ClientOptions opts = testVars.createOptions(testVars.keys[0].keyStr);
			ably = new AblyRealtime(opts);

			/* create a channel and attach */
			final Channel channel = ably.channels.get("subscribe_unsubscribe");
			channel.attach();
			(new ChannelWaiter(channel)).waitFor(ChannelState.attached);
			assertEquals("Verify attached state reached", channel.state, ChannelState.attached);

			/* subscribe */
			MessageListener testListener =  new MessageListener() {
				@Override
				public void onMessage(Message[] messages) {
				}};
			channel.subscribe("test_event", testListener);
			/* unsubscribe */
			channel.unsubscribe("test_event", testListener);
		} catch (AblyException e) {
			e.printStackTrace();
			fail("init0: Unexpected exception instantiating library");
		} finally {
			if(ably != null)
				ably.close();
		}
	}

	/**
	 * Connect to the service using the default (binary) protocol
	 * and attempt to attach to a channel with credentials that do
	 * not have access, confirming that the failed state is reached.
	 */
	@Test
	public void attach_fail() {
		AblyRealtime ably = null;
		try {
			TestVars testVars = RealtimeSetup.getTestVars();
			ClientOptions opts = testVars.createOptions(testVars.keys[1].keyStr);
			ably = new AblyRealtime(opts);

			/* wait until connected */
			(new ConnectionWaiter(ably.connection)).waitFor(ConnectionState.connected);
			assertEquals("Verify connected state reached", ably.connection.state, ConnectionState.connected);

			/* create a channel and attach */
			final Channel channel = ably.channels.get("attach_fail");
			channel.attach();
			ErrorInfo fail = (new ChannelWaiter(channel)).waitFor(ChannelState.failed);
			assertEquals("Verify failed state reached", channel.state, ChannelState.failed);
			assertEquals("Verify reason code gives correct failure reason", fail.statusCode, 401);

		} catch (AblyException e) {
			e.printStackTrace();
			fail("init0: Unexpected exception instantiating library");
		} finally {
			if(ably != null)
				ably.close();
		}
	}

}
