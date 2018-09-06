package com.realpower.petition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ruipu on 2018/8/29.
 */

public class NewsBean implements Serializable {

    /**
     * total : 4
     * list : [{"content":"新闻5内容","contentImageUrl":"74cff7b4-6d25-4c3b-aec3-099e28b12db3.jpg","createTime":"2018-08-24 10:30:52","id":16,"title":"新闻5","titleImageUrl":"00a74e74-37d3-4748-89b8-37d40771b426.jpg","userId":3},{"content":"新闻4内容","contentImageUrl":"6bfaebc8-3bc6-4860-866b-371e3766059e.jpg","createTime":"2018-08-24 10:30:21","id":15,"title":"新闻4","titleImageUrl":"0fc15dde-8ecc-4c39-9737-0cb40f3010d3.jpg","userId":3},{"content":"新闻3内容","contentImageUrl":"8cb76d77-79bf-4d5f-911c-7af8bd77dd86.jpg","createTime":"2018-08-24 10:30:03","id":14,"title":"新闻3","titleImageUrl":"e3b4e29c-1cc0-4049-a740-f9bcaac12d1c.jpg","userId":3},{"content":"新闻2内容","contentImageUrl":"534701a6-c45e-46e0-af0a-e34ad88fb6e6.jpg","createTime":"2018-08-24 10:29:42","id":13,"title":"新闻2","titleImageUrl":"cdc43591-7aa1-476a-985a-2a06ce2f8f62.jpg","userId":3}]
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

    public static class ListBean implements Serializable {
        /**
         * content : 新闻5内容
         * contentImageUrl : 74cff7b4-6d25-4c3b-aec3-099e28b12db3.jpg
         * createTime : 2018-08-24 10:30:52
         * id : 16
         * title : 新闻5
         * titleImageUrl : 00a74e74-37d3-4748-89b8-37d40771b426.jpg
         * userId : 3
         */

        private String content;
        private String contentImageUrl;
        private String createTime;
        private int id;
        private String title;
        private String titleImageUrl;
        private int userId;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContentImageUrl() {
            return contentImageUrl;
        }

        public void setContentImageUrl(String contentImageUrl) {
            this.contentImageUrl = contentImageUrl;
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

        public String getTitleImageUrl() {
            return titleImageUrl;
        }

        public void setTitleImageUrl(String titleImageUrl) {
            this.titleImageUrl = titleImageUrl;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
