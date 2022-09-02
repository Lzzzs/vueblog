import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    token: localStorage.getItem("token"),
    userInfo: JSON.parse(localStorage.getItem("userInfo")),
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
    },
    SET_USERINFO(state, userInfo) {
      state.userInfo = userInfo;
      localStorage.setItem("userInfo", JSON.stringify(userInfo));
    },
    REMOVE_TOKEN(state) {
      state.token = "";
      localStorage.removeItem("token");
    },
    REMOVE_USERINFO(state) {
      state.userInfo = {};
      localStorage.removeItem("userInfo");
    },
  },
  getters: {
    GET_TOKEN({ token }) {
      return token;
    },
    GET_USERINFO({ userInfo }) {
      return userInfo;
    },
  },
  actions: {},
  modules: {},
});
