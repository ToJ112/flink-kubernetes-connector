package org.apache.flink.connector.kubernetes.table;

import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.ConfigOptions;
import org.apache.flink.configuration.ExecutionOptions;
import org.apache.flink.configuration.ReadableConfig;
import org.apache.flink.connector.kubernetes.connection.KubernetesClientOptions;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.connector.source.DynamicTableSource;
import org.apache.flink.table.factories.DynamicTableSourceFactory;
import org.apache.flink.table.factories.FactoryUtil;
import org.apache.flink.table.types.DataType;
import org.apache.flink.table.utils.TableSchemaUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KubernetesDynamicTableFactory implements DynamicTableSourceFactory {
    public static final String IDENTIFIER = "kubernetes";

    public static final ConfigOption<String> ADDRESS = ConfigOptions
            .key("address")
            .stringType()
            .defaultValue("127.0.0.1")
            .withDescription("the kubernetes api-server address.");

    public static final ConfigOption<String> TOKEN = ConfigOptions
            .key("token")
            .stringType()
            .noDefaultValue()
            .withDescription("the kubernetes api-server token");

    @Override
    public DynamicTableSource createDynamicTableSource(Context context) {
        final FactoryUtil.TableFactoryHelper helper =
                FactoryUtil.createTableFactoryHelper(this, context);
        helper.validate();
        final ReadableConfig readableConfig = helper.getOptions();
        //TODO 自定义校验
        KubernetesClientOptions kubernetesClientOptions = getClientOptions(readableConfig);
        final DataType producedDataType = context.getCatalogTable().getSchema().toPhysicalRowDataType();
        TableSchema physicalSchema = TableSchemaUtils.getPhysicalSchema(context.getCatalogTable().getSchema());
        return new KubernetesDynamicTableSource(kubernetesClientOptions);
    }

    private KubernetesClientOptions getClientOptions(ReadableConfig config) {
        return new KubernetesClientOptions.KubernetesClientOptionsBuilder()
                .setAddress(config.get(ADDRESS))
                .setToken(config.get(TOKEN))
                .build();
    }

    @Override
    public String factoryIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public Set<ConfigOption<?>> requiredOptions() {
        Set<ConfigOption<?>> set = new HashSet<>();
        set.add(ADDRESS);
        set.add(TOKEN);
        return set;
    }

    @Override
    public Set<ConfigOption<?>> optionalOptions() {
        return null;
    }
}
