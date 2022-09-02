import axios from "axios";
import { Message } from "element-ui";
const baseConfig = {
  baseURL: "http://localhost",
  timeout: 10000,
};

const instance = axios.create(baseConfig);

/**
 * 请求拦截
 */
instance.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    Message.error("请求超时");
    return Promise.reject(error);
  }
);

/**
 * 响应拦截
 */

instance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.code != 200) {
      error.response.data.msg
        ? Message.error(error.response.data.msg)
        : Message.error("服务器错误");
    }
    return Promise.reject(error);
  }
);

export default instance;
