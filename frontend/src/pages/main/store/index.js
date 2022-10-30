import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    keywords: "",
    category: null,
    maxPrice: 0,
    signedIn: false,
    page:2,
    cart: {},
  },
  mutations: {
    updateSignedIn(state, token) {
      if (token !== null) {
        state.signedIn = true;
      } else {
        state.signedIn = false;
      }
    },
    updateKeywords(state, kw) {
      state.keywords = kw;
    },
    updateCategory(state, b) {
      state.category = b;
    },
    updateMaxPrice(state, mp) {
      state.maxPrice = mp;
    },
    addToCart(state, params) {
      if (state.cart[params.id] !== undefined) {
        state.cart[params.id].quantity += params.quantity;
      } else {
        state.cart[params.id] = {};
        state.cart[params.id].quantity = params.quantity;
        state.cart[params.id].price = params.price;
        state.cart[params.id].title = params.title;
        state.cart[params.id].isDisabled = false;
        state.cart[params.id].error = "";
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
