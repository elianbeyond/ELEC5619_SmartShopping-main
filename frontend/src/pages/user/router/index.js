import { createRouter, createWebHashHistory } from "vue-router";
import Profile from "../views/Profile.vue";

const routes = [
  {
    path: "/profile",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/chpasswd",
    name: "Change Password",
    component: () => import("../views/ChangePassword.vue"),
  },
  {
    path: "/listings",
    name: "Manage Listings",
    component: () => import("../views/ManageListings.vue"),
  },
  {
    path: "/newproduct",
    name: "Add New Product",
    component: () => import("../views/RentWarehouse.vue"),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
