<template>
  <div>
    <Header></Header>

    <div class="mblog">
      <h2>{{ blog.title }}</h2>
      <el-link icon="el-icon-edit" v-if="ownBlog">
        <router-link :to="{ name: 'BlogEdit', params: { blogId: blog.id } }">
          编辑
        </router-link>
      </el-link>
      <br />
      <el-button @click="handleDelteBlog">删除</el-button>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>
  </div>
</template>

<script>
import "github-markdown-css";
import Header from "../components/Header";
import { getBlogDetails, deleteBlog } from "@/network/blog/blog.js";
export default {
  name: "BlogDetail.vue",
  components: { Header },
  data() {
    return {
      blog: {
        id: "",
        title: "",
        content: "",
      },
      ownBlog: false,
    };
  },
  methods: {
    handleDelteBlog() {
      const blogId = this.$route.params.blogId;
      deleteBlog(blogId).then((res) => {
        console.log(res);
        this.$router.push("/blogs");
      });
    },
  },
  created() {
    const blogId = this.$route.params.blogId;
    console.log(blogId);
    const _this = this;
    getBlogDetails(blogId).then((res) => {
      const blog = res.data.data;
      _this.blog.id = blog.id;
      _this.blog.title = blog.title;

      var MardownIt = require("markdown-it");
      var md = new MardownIt();

      var result = md.render(blog.content);
      _this.blog.content = result;
      _this.ownBlog = blog.userId === _this.$store.getters.GET_USERINFO.id;
    });
  },
};
</script>

<style scoped>
.mblog {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-height: 700px;
  padding: 20px 15px;
}
</style>
