package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "endpoints")
public class EndpointsConfig {

    private Ipne ipne;
    private Npis npis;

    public Ipne getIpne() {
        return ipne;
    }

    public void setIpne(Ipne ipne) {
        this.ipne = ipne;
    }

    public Npis getNpis() {
        return npis;
    }

    public void setNpis(Npis npis) {
        this.npis = npis;
    }

    public static class Ipne {

        private OAuth oAuth;
        private String url638_1;
        private String url638_2;

        private String ipneUrl;

        public OAuth getOAuth() {
            return oAuth;
        }

        public void setOAuth(OAuth oAuth) {
            this.oAuth = oAuth;
        }


        public String getUrl638_1() {
            return url638_1;
        }

        public void setUrl638_1(String url638_1) {
            this.url638_1 = url638_1;
        }

        public String getUrl638_2() {
            return url638_2;
        }

        public void setUrl638_2(String url638_2) {
            this.url638_2 = url638_2;
        }

        public String getIpneUrl() {
            return ipneUrl;
        }

        public void setIpneUrl(String ipneUrl) {
            this.ipneUrl = ipneUrl;
        }

        public static class OAuth {

            private String url;
            private String username;
            private String password;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }
        }
    }

    public static class Npis {

        private OAuth oAuth;
        private String npisUrl;
        private String npisUserName;
        private String npisPassword;

        public OAuth getOAuth() {
            return oAuth;
        }

        public void setOAuth(OAuth oAuth) {
            this.oAuth = oAuth;
        }

        public String getNpisUrl() {
            return npisUrl;
        }

        public void setNpisUrl(String npisUrl) {
            this.npisUrl = npisUrl;
        }

        public String getNpisUserName() {
            return npisUserName;
        }

        public void setNpisUserName(String npisUserName) {
            this.npisUserName = npisUserName;
        }

        public String getNpisPassword() {
            return npisPassword;
        }

        public void setNpisPassword(String npisPassword) {
            this.npisPassword = npisPassword;
        }

        public static class OAuth {

            private String url;
            private String username;
            private String password;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }
        }
    }
}
