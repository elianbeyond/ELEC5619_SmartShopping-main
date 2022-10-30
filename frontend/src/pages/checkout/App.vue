<template>
  <div class="checkout">
    <top-bar class="top-bar" />
    <div class="main">
      <cart />
    </div>
  </div>
</template>

<script>
import TopBar from "./components/TopBar.vue";
import Cart from "./components/Cart/Cart.vue";
export default {
  name: "Checkout",
  components: {
    TopBar,
    // Buttons,
    Cart,
  },
  computed: {
    totalPrice() {
      let total = 0;
      let cart = this.$store.state.cart;
      for (let [item] of Object.entries(cart)) {
        total += cart[item].price * cart[item].quantity;
      }
      return Math.round(total * 100) / 100;
    },
  },
};
</script>

<style>
@font-face {
  font-family: "Montserrat";
  src: local("Montserrat"),
    url("../../assets/fonts/Montserrat/Montserrat-Regular.ttf")
      format("truetype");
}

@font-face {
  font-family: "Lato";
  src: local("Lato"),
    url("../../assets/fonts/Lato/Lato-Regular.ttf") format("truetype");
}

body {
  margin: 0;
}

html {
  min-width: 120rem;
  font-size: 12px;
}

.checkout {
  display: flex;
  flex-flow: column;
}

.price {
  width: 75rem;
  text-align: left;
  font-family: "Lato";
  font-size: 1.5rem;
  margin: 0;
}

.main {
  padding-top: 7rem;
  display: flex;
  flex-flow: column;
  align-items: center;
  justify-content: center;
  gap: 2rem;
  margin-bottom: 5rem;
}

.top-bar {
  /* To make it stay on top */
  position: fixed;
}
</style>
