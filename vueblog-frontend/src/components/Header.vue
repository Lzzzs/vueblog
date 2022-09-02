<template>
  <div class="m-content">
    <h3>欢迎来到MarkerHub的博客</h3>
    <div class="block">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <div>{{ user.username }}</div>
    </div>

    <div class="maction">
      <span><el-link href="/blogs">主页</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span><el-link type="success" href="/blog/add">发表博客</el-link></span>

      <el-divider direction="vertical"></el-divider>
      <span v-show="!hasLogin"
        ><el-link type="primary" href="/login">登录</el-link></span
      >

      <span v-show="hasLogin"
        ><el-link type="danger" @click="logout">退出</el-link></span
      >
    </div>
  </div>
</template>

<script>
import { handleLogout } from "@/network/login/loginApi.js";

export default {
  name: "Header",
  data() {
    return {
      user: {
        username: "请先登录",
        avatar:
          "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      },
      hasLogin: false,
    };
  },
  methods: {
    logout() {
      const _this = this;
      handleLogout().then((res) => {
        console.log(res);
        _this.$store.commit("REMOVE_TOKEN");
        _this.$store.commit("REMOVE_USERINFO");
        _this.$router.push("/login");
      });
    },
  },
  created() {
    if (this.$store.getters.GET_USERINFO.username) {
      this.user.username = this.$store.getters.GET_USERINFO.username;
      this.user.avatar = this.$store.getters.GET_USERINFO.avatar;

      this.hasLogin = true;
    }
  },
};
</script>

<style scoped>
.m-content {
  max-width: 960px;
  margin: 0 auto;
  text-align: center;
}
.maction {
  margin: 10px 0;
}
</style>
