import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import VueCookies from "vue-cookies";
import "vue-universal-modal/dist/index.css";
import VueUniversalModal from "vue-universal-modal";
import VueGoogleMaps from "@fawmi/vue-google-maps";
import store from "./store/store.js";
createApp(App)
  .use(router)
  .use(VueCookies)
  .use(store)
  .use(VueUniversalModal, { teleportTarget: "#modals" })
  .use(VueGoogleMaps, {
    load: {
      key: "AIzaSyBtDAv9h6RhX6aPJdnYZu50m_q3sn2EKAQ",
    },
  })
  .mount("#app");
