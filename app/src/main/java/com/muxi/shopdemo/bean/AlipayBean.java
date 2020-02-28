package com.muxi.shopdemo.bean;

public class AlipayBean {
    private static String appId = "2016101500689960";
    private static String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHI/rLpJxxjtcvSbPG48Cb9si8syOvIHaVy7KtZoXJLwTN3o+JQ96woi4AvDjVAQEtCPru8Ly/kPWytbLWrYbLDLsAjT9gDkoaTOTIq2Rovi9DQBbzQnHGjF3nU3BsIETh3Na5m30cj/DG+eUpoIy+smWFe+nm0T9N7fWldA+hwE1+A50AL010NA4t9OQhT+/4AXGDhYy2JzBQA+uvkCYjNZnDrge4g0fpibxyYUzHvXTdKRg/xEA/Po7CKFUT/VS8YCITFgFTCFhm/lbbQMJkp44EC8lQXqONW5JZdcwVJQS0EoShaU4GIiQWRSQa1DpqsEvps7wpq76Gm7ZQvkOzAgMBAAECggEAHlBu2LGDOmHtWTkyL6I1OYchPlr83TLuBuobmZQO3LNEY53SGttzn7Gxmg6evQEtr1l9WI39+QbeZoklpcxlGTUw2Y3VsoliDAZFp9HygyBuX6qHu2ejOAOSqOpppIeUjAnKzPXZGcgcHbY+tpjCGaDQ7yF9tX4osiV33fZr7z9tbJe29GcBXH483AINiZpoMLRu/8b+84qHvKnve3gHOHD//38/K7swNscUCdWptyv+nDWeqqr9J5M1jx4S7+IcgV0IB9cxG/EE8lABaIUeJur0finEPKAbbn7xk3ZL0AVwzffu/OQkiF4ZyK+vvW4QzUS/zVYy83+cIJpm2UO++QKBgQC9eCYGBoAYO6eBiQrf1wVKpXrnTM4m2H5r/WdOcvCwEx/KX/NdAeFAc5uizjB3HhdE2Ap/oPNfXgNSmVcfYtXM+j7aioenoaakld6yQ9w8upg0Lon8k3VjevMaMBHy4+QetbrkRlUYOVwVG31ZZCaK6Y6OQ28fK48YoY7/KlEyRQKBgQC2mBYxQmexkGQgOMI3fiIlGk2RMWfAgz0OzE4TVH0BNUtrZBcxKmS7zGJo9nD1wljSatjJVtDhq8g7Z6uCCObQXZLPd7aK0Jox1atgQCP1IRlFBcGP4ynI5h5JEVgFCObAE5vsi0uhfsiiE9kcc6xx9VMJupKNZSgqQM+jLqt5lwKBgGxOfTXei2TO3P7wLtYr3OuB0tLbldCg2wuyLFas36dXF0ohNUWKaI/mRiZ3xT6iZdzFvqKch+KRJEcYPrRDVEI7HqjgnZEWl+LnhQjUP9Y7N/xESj6msGjwhH0I04nL+cAQVEhfaA9T4q5yK2JpHJs1zXbHV4My7DbBS7PTJd0dAoGBAIJtPqTvFxyp1CuD/48eBQvVrwSAcuj7lbilTTvXhPdA9199fmd7LbtLshvhtSyHJ2UeNz4SSmB4kS2FzueyrhS2UnDpxTWA7jOcua6mAklN3DwGcY3zKNy9K3Lr9aCbDCC1oRIlKd/wrSXA4bvGEK7SGv9/CsH/OOJ9qxOX+Zp/AoGAFwNVopkmk2LoPxd/VEt/81hW5l8ryGGgKE5nKyuUi3VyPhRM8Wcsn06nXK55dKAtWQ4xlInBxmXiT76Scs1TSFj9ulGEFgkhECvSFe6IaRB2yXBRW7Y1tHTWph1aJOzDhkHEzUFHHDw4Sd17lJ96Z2jHENrchouKKupFZ9mbKc4=";
    private static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu7F+qpLbEut2K9FyMv5mdVt+ZPFzGk2zoYgxwhn3MG8c4g/rYsQT5uDulmzdbG6sFqIsw2NZzGInTpix9Z/CYXQYPGR+YoYmE9Ftd22uVL/j+3WitZFoHJy3VjNXfuIok84g6zfn1Wnq48nNC0wUyBI4DUKy5wuEqJhT1sZgulWTQG0c5TlolWhduaU2G9eRYo7Um+9TIJ1g9BfCwPQXE2goyzvhSgtu/07TLp5TGZUDp9Ghvx1mRe6sENUU3tsG5iUsQpxJsZTaFDKtVl8KpPONMn3poawe3claTbtgdqUV5Uz/qsFDFgXJ15za/k7pcke97rgQKosOLsy8SBOk2QIDAQAB";
    private static String notifyUrl = "http://外网ip:端口/error.html";
    private static String returnUrl = "http://外网ip:端口/sccess.html";
    private static String signType = "RSA2";
    private static String charset = "utf-8";
    private static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    private static String logPath = "C:\\";
    public static String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public static String getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    public static String getPublicKey() {
        return publicKey;
    }
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    public static String getNotifyUrl() {
        return notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
    public static String getReturnUrl() {
        return returnUrl;
    }
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    public static String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public static String getCharset() {
        return charset;
    }
    public void setCharset(String charset) {
        this.charset = charset;
    }
    public static String getGatewayUrl() {
        return gatewayUrl;
    }
    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }
    public String getLogPath() {
        return logPath;
    }
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}
