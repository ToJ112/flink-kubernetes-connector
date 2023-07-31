package org.apache.flink.connector.kubernetes;

import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.ConfigOptions;

import java.util.Properties;
import java.util.function.Function;

public class KubernetesSourceOptions {
    public static final ConfigOption<String> LINKTYPE = ConfigOptions
            .key("linktype")
            .stringType()
            .defaultValue("token")
            .withDescription("the way to connect to kubernetes");

    public static final ConfigOption<String> ADDRESS = ConfigOptions
            .key("address")
            .stringType()
            .noDefaultValue()
            .withDescription("the address of kubernetes");

    @SuppressWarnings("unchecked")
    public static <T> T getOption(
            Properties props, ConfigOption<?> configOption, Function<String, T> parser) {
        String value = props.getProperty(configOption.key());
        return (T) (value == null ? configOption.defaultValue() : parser.apply(value));
    }
}
