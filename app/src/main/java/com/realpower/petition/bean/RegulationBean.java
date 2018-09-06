package com.realpower.petition.bean;

import java.util.List;

/**
 * Created by ruipu on 2018/7/12.
 */

public class RegulationBean {

    /**
     * total : 2
     * list : [{"content":"修改法规1222222","createTime":"2018-07-12 09:35:18","id":11,"title":"新增法规3","type":2,"userId":6},{"content":"新增法规1新增法规1新增法规1新增法规1新增法规1新增法规1","createTime":"2018-07-12 09:34:43","id":9,"title":"新增法规1","type":2,"userId":6}]
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * content : 修改法规1222222
         * createTime : 2018-07-12 09:35:18
         * id : 11
         * title : 新增法规3
         * type : 2
         * userId : 6
         */

        private String content;
        private String createTime;
        private int id;
        private String title;
        private int type;
        private int userId;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
