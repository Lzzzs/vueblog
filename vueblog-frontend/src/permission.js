import router from "./router";

router.beforeEach((to, from, next) => {
  console.log(to);
  if (to.meta.requireAuth) {
    if (localStorage.getItem("token")) {
      console.log(to.path);
      if (to.path == "/login") {
        next({
          path: "/blogs",
        });
      } else {
        next();
      }
    } else {
      next({
        path: "/login",
      });
    }
  } else {
    next();
  }
});
