package com.iteratively.objectify.translate;

import com.google.cloud.datastore.testing.LocalDatastoreHelper;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LocalDatastoreExtension implements BeforeAllCallback, BeforeEachCallback {

    @Override
    public void beforeAll(final ExtensionContext context) throws Exception {
        if (getHelper(context) == null) {
            final LocalDatastoreHelper helper = LocalDatastoreHelper.newBuilder()
                    .setConsistency(1.0)	// we always have strong consistency now
                    .setStoreOnDisk(false)
                    .build();

            context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put(LocalDatastoreHelper.class, helper);
            helper.start();
        }

    }

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final LocalDatastoreHelper helper = getHelper(context);
        helper.reset();
    }

    /** Get the helper created in beforeAll; it should be global so there will one per test run */
    public static LocalDatastoreHelper getHelper(final ExtensionContext context) {
        return context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).get(LocalDatastoreHelper.class, LocalDatastoreHelper.class);
    }
}

