package org.apache.flink.connector.kubernetes.table;

import org.apache.flink.connector.kubernetes.connection.KubernetesClientOptions;
import org.apache.flink.table.connector.source.DynamicTableSource;

public class KubernetesDynamicTableSource implements DynamicTableSource {

    private final KubernetesClientOptions kubernetesClientOptions;

    public KubernetesDynamicTableSource(KubernetesClientOptions kubernetesClientOptions) {
        this.kubernetesClientOptions = kubernetesClientOptions;
    }

    @Override
    public DynamicTableSource copy() {
        return new KubernetesDynamicTableSource(kubernetesClientOptions);
    }

    @Override
    public String asSummaryString() {
        return "KubernetesDynamicTableSource";
    }
}
