package org.apache.flink.connector.kubernetes.connection;

import java.io.Serializable;

public class KubernetesClientOptions implements Serializable {
    private static final long serialVersionUID = 5685521189643221125L;

    private final String address;

    private final String token;

    public KubernetesClientOptions(String address, String token) {
        this.address = address;
        this.token = token;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Builder for {@link KubernetesClientOptions}
     */
    public static class KubernetesClientOptionsBuilder {
        private String address;
        private String token;

        public KubernetesClientOptionsBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public KubernetesClientOptionsBuilder setToken(String token) {
            this.token = token;
            return this;
        }

        public KubernetesClientOptions build() {
            if (address == null) {
                throw new IllegalArgumentException("address can not be empty.");
            }
            if (token == null) {
                throw new IllegalArgumentException("address can not be empty.");
            }
            return new KubernetesClientOptions(address, token);
        }
    }
}
