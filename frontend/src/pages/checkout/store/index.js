import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    keywords: "",
    brand: "",
    maxPrice: 0,
    signedIn: false,
    cart: {},
  },
  mutations: {
    setItemQuantity(state, params) {
      state.cart[params.id].quantity = params.quantity;
    },

    setItemDisabled(state, params) {
      state.cart[params.id].isDisabled = params.disabled;
    },
    setItemError(state, params) {
      state.cart[params.id].error = params.error;
    },
    removeItem(state, id) {
      delete state.cart[id];
    },
    resetErrorsAndDisabled(state) {
      for (let [item] of Object.entries(state.cart)) {
        state.cart[item].error = "";
        state.cart[item].isDisabled = false;
      }
    },
    clearCart(state) {
      state.cart = {};
    },
  },
  actions: {},
  modules: {},
  plugins: [createPersistedState()],
});
