<template>
  <div class="product" v-on:click="toItemState">
    <img :src="product.imglocation" alt="product image" />
    <p>{{ product.name }}</p>
    <p>Category {{ product.category }}</p>
    <p>Stock: {{ product.quantity }}</p>
    <p>${{ product.price }}</p>
  </div>
</template>

<script>
export default {
  name: "Product",
  props: {
    product: Object,
  },
  data() {
    return {
      title: "",
    };
  },
  methods: {
    toItemState: function () {
      window.location.hash = "/item/" + this.product.id;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.product {
  width: 16rem;
  height: 16rem;
  text-align: center;
}

.product:hover {
  cursor: pointer;
}

p {
  padding: 0;
  margin: 0;
}

img {
  width: 12.5rem;
}

p {
  font-family: "Montserrat";
  font-size: 2rem;
  color: #5d5d81;
  padding: 0;
  margin: 0;
  margin-top: 1rem;
}

</style>
