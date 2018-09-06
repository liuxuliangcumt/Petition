package com.realpower.petition.bean;

import java.util.List;

public class AddressBean {

    private String code;
    private String name;
    private List<ChildsBeanX> childs;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildsBeanX> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildsBeanX> childs) {
        this.childs = childs;
    }

    public static class ChildsBeanX {
        /**
         * code : 130502
         * name : 桥东区
         * childs : [{"code":"130502001","name":"南长街街道"}]
         */

        private String code;
        private String name;
        private List<ChildsBean> childs;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildsBean> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBean> childs) {
            this.childs = childs;
        }

        public static class ChildsBean {
            /**
             * code : 130502001
             * name : 南长街街道
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
