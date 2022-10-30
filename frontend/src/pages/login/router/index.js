import { createRouter, createWebHashHistory } from "vue-router";
import Login from "../views/Login";

const routes = [
  {
    path: "/",
    component: Login,
  },
  {
    path: "/Login",
    name: "Login",
    component: () => import("../views/Login"),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
