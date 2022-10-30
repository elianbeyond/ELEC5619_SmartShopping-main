import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueCookies from "vue-cookies";
import VueGoogleMaps from '@fawmi/vue-google-maps';
import Axios from "axios";

const app = createApp(App);
Axios.defaults.baseURL = "/api";
app.config.globalProperties.$ajax = Axios;

app.use(store).use(router).use(VueCookies).use(VueGoogleMaps, {
    load: {
        key: "AIzaSyBtDAv9h6RhX6aPJdnYZu50m_q3sn2EKAQ",
    },
}).mount("#app");

router.beforeEach((to,from,next)=>{
    if(sessionStorage.getItem('isLogin')!='1'){
        alert('You have not logged in, please log in first');
        window.location.href = "/login#";
        // window.history.back(-1);
    }
    next();

}) 