import { createStore } from "vuex";
const store = createStore({
  state() {
    return {
      fullname: "",
    };
  },
  mutations: {
    updateName(state, username) {
      state.fullname = username;
    },
  },
});
export default store;
