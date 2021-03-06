package com.weixin.pay.sdk;

/**
 * 域名管理，实现主备域名自动切换
 *
 * @author WXPay
 * @version 3.0.9
 */
public interface IWXPayDomain {
    /**
     * 上报域名网络状况
     *
     * @param domain            域名。 比如：api.mch.weixin.qq.com
     * @param elapsedTimeMillis 耗时
     * @param ex                网络请求中出现的异常。
     *                          null表示没有异常
     *                          ConnectTimeoutException，表示建立网络连接异常
     *                          UnknownHostException， 表示dns解析异常
     */
    void report(String domain, long elapsedTimeMillis, Exception ex);

    /**
     * 获取域名
     *
     * @param config 配置
     * @return 域名
     */
    DomainInfo getDomain(WXPayConfig config);

    /**
     * DomainInfo
     *
     * @author WXPay
     * @version 3.0.9
     */
    class DomainInfo {

        private String domain; //域名
        private boolean primaryDomain; //该域名是否为主域名。例如:api.mch.weixin.qq.com为主域名

        public DomainInfo(String domain, boolean primaryDomain) {
            this.domain = domain;
            this.primaryDomain = primaryDomain;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public boolean isPrimaryDomain() {
            return primaryDomain;
        }

        public void setPrimaryDomain(boolean primaryDomain) {
            this.primaryDomain = primaryDomain;
        }

        @Override
        public String toString() {
            return "DomainInfo{"
                    + "domain='" + domain + '\''
                    + ", primaryDomain=" + primaryDomain
                    + "}";
        }
    }

}
