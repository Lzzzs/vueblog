import axios from "../index";

export function handleLogin(loginInfo) {
  return axios.post("/login", loginInfo);
}

export function handleLogout() {
  return axios.get("/logout", {
    headers: {
      Authorization: localStorage.getItem("token"),
    },
  });
}
