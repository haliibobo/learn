package com.github.haliibobo.learn.metric;

public class Summary {

    private long totalCount;
    private long successCount;
    private long errorCount;
    private float errorPercentage;
    private int qps;

    /**
     *
     * @param totalCount
     * @param errorCount
     */
    public Summary(long totalCount, long errorCount) {
        this.totalCount = totalCount;
        this.errorCount = errorCount;
        this.errorPercentage = 1.0f * errorCount / totalCount * 100;
    }

    /**
     *
     * @param successCount
     * @param errorCount
     * @param qps
     */
    public Summary(long successCount, long errorCount, int qps) {
        this.totalCount = successCount + errorCount;
        this.successCount = successCount;
        this.errorCount = errorCount;
        this.qps = qps;
    }

    public Summary plus(Health health) {
        long successCount = this.successCount + health.getSuccessCount();
        long failedCount = this.errorCount + health.getErrorCount();

        Summary summary = new Summary(successCount, failedCount, qps);
        long totalCount = summary.getTotalRequests();
        if (totalCount > 0) {
            summary.setErrorPercentage(1.0f * failedCount / totalCount * 100);
        }
        return summary;
    }

    public void clear() {
        this.qps = 0;
        this.totalCount = 0;
        this.successCount = 0;
        this.errorCount = 0;
        this.errorPercentage = 0;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public long getTotalRequests() {
        return totalCount;
    }

    public float getErrorPercentage() {
        return errorPercentage;
    }

    public void setErrorPercentage(float errorPercentage) {
        this.errorPercentage = errorPercentage;
    }

    @Override
    public String toString() {
        return "HealthCounts [" + errorCount + " / " + totalCount + " : " + errorPercentage + "%]";
    }

    public String toJson() {
        StringBuilder builder = new StringBuilder("{");
        builder.append("\"totalCount\":");
        builder.append(totalCount);
        builder.append(",");
        builder.append("\"errorCount\":");
        builder.append(errorCount);
        builder.append(",");
        builder.append("\"errorPercentage\":");
        builder.append(errorPercentage);
        builder.append(",");
        builder.append("\"qps\":");
        builder.append(qps);
        builder.append("}");
        return builder.toString();
    }
}
