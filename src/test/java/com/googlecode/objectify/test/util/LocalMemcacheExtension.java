package com.googlecode.objectify.test.util;

import net.spy.memcached.MemcachedClient;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

import java.net.InetSocketAddress;

public class LocalMemcacheExtension implements BeforeAllCallback, BeforeEachCallback {

    @Override
    public void beforeAll(final ExtensionContext context) throws Exception {
        if (getClient(context) == null) {
            final MemcachedClient client = new MemcachedClient(new InetSocketAddress("localhost", 11211));
            context.getRoot().getStore(Namespace.GLOBAL).put(MemcachedClient.class, client);
        }

    }

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final MemcachedClient client = getClient(context);
        client.flush();
    }

    /** Get the helper created in beforeAll; it should be global so there will one per test run */
    public static MemcachedClient getClient(final ExtensionContext context) {
        return context.getRoot().getStore(Namespace.GLOBAL).get(MemcachedClient.class, MemcachedClient.class);
    }
}